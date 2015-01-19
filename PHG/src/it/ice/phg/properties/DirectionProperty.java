package it.ice.phg.properties;

import it.ice.scrooge.Property;

public class DirectionProperty extends Property {
	private int[] direction;

	public DirectionProperty() {
		this(0, 0);
	}

	public DirectionProperty(int horizontal, int vertical) {
		super();
		direction = new int[2];
		direction[0] = horizontal;
		direction[1] = vertical;
	}

	public Object getValue() {
		return direction;
	}

	protected void update() {
		// TODO Auto-generated method stub

	}
}
