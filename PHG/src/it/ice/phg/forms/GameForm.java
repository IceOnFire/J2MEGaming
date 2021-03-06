package it.ice.phg.forms;

import it.ice.phg.PHGController;
import it.ice.scrooge.Form;

public class GameForm extends Form {
	private int x, y;
	private int width, height;

	public GameForm(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
	}

	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		PHGController.getInstance().setViewWindow(x, y, width, height);
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
		setBounds(dx, dy, width, height);
	}

	public void place(int x, int y) {
		setBounds(x, y, width, height);
	}
}
