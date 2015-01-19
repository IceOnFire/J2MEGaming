package it.ice.scrooge;

import java.util.Vector;

public abstract class Action {
	protected Node node;
	protected Vector targets;
	private boolean persistent;

	public Action(Node agent) {
		this.node = agent;
		targets = new Vector();
	}

	public void addTarget(Node target) {
		targets.addElement(target);
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	protected void dispose() {
		// does nothing
	}

	protected abstract void run();
}
