package it.ice.scrooge;

import java.util.Vector;

public abstract class Form {
	protected Vector listeners;
	protected int x, y;
	protected int width, height;

	protected Form() {
		listeners = new Vector();
	}

	protected abstract void activate();

	protected abstract void update();

	protected abstract void dispose();

	public abstract Object getAvatar();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void place(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void addEventListener(FormEventListener listener) {
		listeners.addElement(listener);
	}

	public void notifyEvent(FormEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			FormEventListener listener = (FormEventListener) listeners.elementAt(i);
			listener.onFormEvent(event);
		}
	}
}
