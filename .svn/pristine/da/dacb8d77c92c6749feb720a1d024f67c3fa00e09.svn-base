package it.ice.sooge;

import java.util.Vector;

public abstract class Action {
	protected Entity agent;
	protected Vector targets;
	private boolean persistent;

	public Action(Entity agent) {
		this.agent = agent;
		targets = new Vector();
	}

	public void addTarget(Entity target) {
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
