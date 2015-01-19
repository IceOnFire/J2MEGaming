package it.ice.scrooge;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Node implements PropertyListener, FormEventListener, ActionEventListener {
	protected Node parent;
	protected Vector children;

	protected Hashtable properties;

	protected Hashtable states;
	protected State activeState;

	protected Hashtable forms;
	protected Form activeForm;

	protected Hashtable availableActions;
	protected Vector enabledActions;

	public Node() {
		children = new Vector();

		properties = new Hashtable();

		states = new Hashtable();

		forms = new Hashtable();

		availableActions = new Hashtable();
		enabledActions = new Vector();
	}

	public void setParent(Node node) {
		parent = node;
	}

	public void addChild(Node child) {
		children.addElement(child);
		child.setParent(this);
	}

	public void removeChild(Node child) {
		children.removeElement(child);
	}

	public Vector getChildren() {
		return children;
	}

	public Vector getSiblings() {
		Vector siblings = new Vector();
		Vector us = parent.getChildren();
		for (int i=0; i<us.size(); i++) {
			if (us.elementAt(i) != this) {
				siblings.addElement(us.elementAt(i));
			}
		}
		return siblings;
	}

	public void addProperty(int propertyId, Property property) {
		properties.put(new Integer(propertyId), property);
		property.setNode(this);
		property.addPropertyListener(this);
	}

	public void removeProperty(int propertyId) {
		properties.remove(new Integer(propertyId));
	}

	public Property getProperty(int propertyId) {
		return (Property) properties.get(new Integer(propertyId));
	}

	public void addState(int stateId, State state) {
		state.setNode(this);
		states.put(new Integer(stateId), state);
	}

	public void removeState(int stateId) {
		states.remove(new Integer(stateId));
	}

	public State getState(int stateId) {
		return (State) states.get(new Integer(stateId));
	}

	public State getActiveState() {
		return activeState;
	}

	public void setActiveState(int stateId) {
		Integer id = new Integer(stateId);
		activeState = (State) states.get(id);
		if (activeForm != null) {
			activeForm.dispose();
		}
		activeForm = (Form) forms.get(id);
		activeForm.activate();
	}

	public Form getActiveForm() {
		return activeForm;
	}

	public void setForm(int stateId, Form form) {
		form.addEventListener(this);
		forms.put(new Integer(stateId), form);
	}

	public void addAction(int actionId, Action action) {
		availableActions.put(new Integer(actionId), action);
	}

	public void removeAction(int actionId) {
		availableActions.remove(new Integer(actionId));
	}

	public void enableAction(int actionId) {
		Integer id = new Integer(actionId);
		if (availableActions.get(id) != null) {
			Object action = availableActions.get(id);
			enabledActions.addElement(action);
		} else if (parent != null) {
			parent.enableAction(actionId);
		}
	}

	public void disableAction(Action action) {
		action.dispose();
		enabledActions.removeElement(action);
	}

	public void update() {
		for (int i = 0; i < children.size(); i++) {
			Node child = (Node) children.elementAt(i);
			child.update();
		}

		Enumeration props = properties.elements();
		while (props.hasMoreElements()) {
			Property property = (Property) props.nextElement();
			property.update();
		}

		if (activeForm != null) {
			activeForm.update();
		}

		for (int i = 0; i < enabledActions.size(); i++) {
			Action action = (Action) enabledActions.elementAt(i);
			action.run();
			if (!action.isPersistent()) {
				disableAction(action);
			}
		}
	}

	public void onFormEvent(FormEvent event) {
		if (children.isEmpty()) {
			activeState.onFormEvent(event);
		} else {
			for (int i = 0; i < children.size(); i++) {
				Node child = (Node) children.elementAt(i);
				child.onFormEvent(event);
			}
		}
	}

	public void onActionEvent(ActionEvent event) {
		if (children.isEmpty()) {
			activeState.onActionEvent(event);
		} else {
			for (int i = 0; i < children.size(); i++) {
				Node child = (Node) children.elementAt(i);
				child.onActionEvent(event);
			}
		}
	}

	public void onPropertyChange(Property property) {
		// do nothing
	}
}
