package it.ice.phg.forms;

import it.ice.scrooge.Form;

public class GroundForm extends Form {
	private int x, y;
	private int width, height;

	public GroundForm(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	protected void activate() {
		// TODO Auto-generated method stub

	}

	protected void update() {
		// TODO Auto-generated method stub

	}

	protected void dispose() {
		// TODO Auto-generated method stub

	}

	public Object getAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

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

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void place(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
