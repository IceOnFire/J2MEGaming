package it.seat.helicoptergame.states;

import it.seat.game.states.State;

public class SpeedState implements State {
	private int horizontal;
	private int vertical;

	public SpeedState() {
		horizontal = 0;
		vertical = 0;
	}

	public SpeedState(int horizontal, int vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}
}
