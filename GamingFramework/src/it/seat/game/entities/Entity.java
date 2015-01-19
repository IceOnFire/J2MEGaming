package it.seat.game.entities;

import it.seat.game.actions.Action;
import it.seat.game.forms.Form;
import it.seat.game.states.State;

import java.util.Enumeration;
import java.util.Hashtable;

public class Entity {
	protected Hashtable availableActions;
	protected Hashtable enabledActions;
	protected Hashtable states;
	protected Form form;

	public Entity() {
		availableActions = new Hashtable();
		enabledActions = new Hashtable();
		states = new Hashtable();
	}

	public void update() {
		form.update();
	}

	public Hashtable getAvailableActions() {
		return availableActions;
	}

	public Hashtable getEnabledActions() {
		return enabledActions;
	}

	public Hashtable getStates() {
		return states;
	}

	public State getState(String stateId) {
		return (State) states.get(stateId);
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public void addAction(String actionId, Action action) {
		availableActions.put(actionId, action);
	}

	public void enableAction(Object actionId) {
		Object action = availableActions.get(actionId);
		enabledActions.put(actionId, action);
		availableActions.remove(actionId);
	}

	public void disableAction(Object actionId) {
		Object action = enabledActions.get(actionId);
		availableActions.put(actionId, action);
		enabledActions.remove(actionId);
	}

	public void runAction(Object actionId) {
		Action action = (Action) enabledActions.get(actionId);
		action.run();
		if (action.isInstant()) {
			action.dispose();
			disableAction(actionId);
		}
	}

	public void enableActions() {
		Enumeration actionIds = availableActions.keys();
		while (actionIds.hasMoreElements()) {
			Object actionId = actionIds.nextElement();
			enableAction(actionId);
		}
	}

	public void disableActions() {
		Enumeration actionIds = enabledActions.keys();
		while (actionIds.hasMoreElements()) {
			Object actionId = actionIds.nextElement();
			disableAction(actionId);
		}
	}

	public void runActions() {
		Enumeration actionIds = enabledActions.keys();
		while (actionIds.hasMoreElements()) {
			Object actionId = actionIds.nextElement();
			runAction(actionId);
		}
	}

	public void addState(String stateId, State state) {
		states.put(stateId, state);
	}

	public void removeState(String stateId) {
		states.remove(stateId);
	}

	public void destroy() {
		availableActions.clear();
		enabledActions.clear();
		states.clear();
		form.destroy();
	}
}
