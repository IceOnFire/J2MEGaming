package it.seat.game.forms;

import it.seat.game.entities.Entity;

import java.util.Hashtable;
import java.util.Vector;

public abstract class Form {
	protected Hashtable spaces;

	protected Form() {
		spaces = new Hashtable();
	}

	public abstract Object getAvatar();

	public abstract int getX();

	public abstract int getY();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void place(int x, int y);

	public abstract void move(int dx, int dy);

	public abstract void update();

	public abstract void render();

	public Hashtable getSpaces() {
		return spaces;
	}

	public Vector getSpace(String spaceId) {
		return (Vector)spaces.get(spaceId);
	}

	public void addSpace(String spaceId, Vector space) {
		spaces.put(spaceId, space);
	}

	public void removeSpace(String spaceId) {
		spaces.remove(spaceId);
	}

	public void addToSpace(String spaceId, Entity entity) {
		((Vector) spaces.get(spaceId)).addElement(entity);
	}

	public void removeFromSpace(String spaceId, Entity entity) {
		((Vector) spaces.get(spaceId)).removeElement(entity);
	}

	public void destroy() {
		spaces.clear();
	}
}
