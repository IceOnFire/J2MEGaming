package it.ice.sooge;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public abstract class Entity implements PropertyListener, EventListener {
	protected Hashtable properties;
	protected Hashtable children;
	protected Hashtable states;
	protected State activeState;
	protected Hashtable availableActions;
	protected Hashtable enabledActions;

	public Entity() {
		properties = new Hashtable();
		children = new Hashtable();
		states = new Hashtable();
		availableActions = new Hashtable();
		enabledActions = new Hashtable();
	}

	public void addProperty(int propertyId, Property property) {
		properties.put(new Integer(propertyId), property);
	}

	public void removeProperty(int propertyId) {
		properties.remove(new Integer(propertyId));
	}

	public Property getProperty(int propertyId) {
		return (Property) properties.get(new Integer(propertyId));
	}

	public Entity getChild(int childId) {
		return (Entity) children.get(new Integer(childId));
	}

	public Vector getChildren(int childrenId) {
		return (Vector) children.get(new Integer(childrenId));
	}

	public Vector getAllChildren() {
		Vector allChildren = new Vector();
		Enumeration elements = this.children.elements();
		while (elements.hasMoreElements()) {
			Object element = elements.nextElement();
			if (element instanceof Entity) {
				allChildren.addElement(element);
			} else if (element instanceof Vector) {
				Vector children = (Vector) element;
				for (int i=0; i<children.size(); i++) {
					allChildren.addElement(children.elementAt(i));
				}
			}
		}
		return allChildren;
	}

	public void addChild(int childId, Entity child) {
		children.put(new Integer(childId), child);
	}

	public void addToChildren(int childrenId, Entity child) {
		Integer id = new Integer(childrenId);
		Object value = children.get(id);
		if (value == null) {
			value = new Vector();
			children.put(id, value);
		}
		((Vector) value).addElement(child);
	}

	public void removeChild(Entity child) {
		Vector allChildren = getAllChildren();
		allChildren.removeElement(child);
	}

	public void removeFromChildren(int childrenId, Entity child) {
		Integer id = new Integer(childrenId);
		Vector value = (Vector) children.get(id);
		value.removeElement(child);
	}

	public void addState(int stateId, State state) {
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
		if (activeState != null) {
			activeState.dispose();
		}
		activeState = (State) states.get(new Integer(stateId));
		activeState.activate();
	}

	public void addAction(int actionId, Action action) {
		availableActions.put(new Integer(actionId), action);
	}

	public void enableAction(int actionId) {
		Integer id = new Integer(actionId);
		Object action = availableActions.get(id);
		enabledActions.put(id, action);
	}

	public void disableAction(int actionId) {
		Integer id = new Integer(actionId);
		enabledActions.remove(id);
	}

	public void runAction(int actionId) {
		Action action = (Action) enabledActions.get(new Integer(actionId));
		action.run();
		if (!action.isPersistent()) {
			action.dispose();
			disableAction(actionId);
		}
	}

	private void runActions() {
		Enumeration actionIds = enabledActions.keys();
		while (actionIds.hasMoreElements()) {
			Integer actionId = (Integer) actionIds.nextElement();
			runAction(actionId.intValue());
		}
	}

	public void update() {
		Enumeration properties = this.properties.elements();
		while (properties.hasMoreElements()) {
			Property property = (Property) properties.nextElement();
			property.update();
		}

		if (activeState != null) {
			activeState.update();
		}

		runActions();

		Vector children = getAllChildren();
		for (int i = 0; i < children.size(); i++) {
			Entity child = (Entity) children.elementAt(i);
			child.update();
		}
	}
}
