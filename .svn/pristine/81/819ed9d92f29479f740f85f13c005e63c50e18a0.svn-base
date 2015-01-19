package it.ice.sooge;

import java.util.Vector;

public abstract class Form {
	private Vector listeners;

	protected Form() {
		listeners = new Vector();
	}

	public void addEventListener(EventListener listener) {
		listeners.addElement(listener);
	}

	protected void notifyEvent(Event event) {
		for (int i = 0; i < listeners.size(); i++) {
			EventListener listener = (EventListener) listeners.elementAt(i);
			listener.onEvent(event);
		}
	}

	protected abstract void activate();

	protected abstract void update();

	protected abstract void dispose();

	public abstract Object getAvatar();

	public abstract int getX();

	public abstract int getY();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void place(int x, int y);

	public abstract void move(int dx, int dy);
}
