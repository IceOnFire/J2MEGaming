package it.seat.game.actions;

import it.seat.game.entities.Entity;

import java.util.Vector;

public abstract class Action {
	protected Entity userEntity;
	protected Vector targetEntities;
	private boolean instant;

	public Action(Entity entity) {
		this.userEntity = entity;
		targetEntities = new Vector();
		instant = true;
	}

	public void addTargetEntity(Entity entity) {
		targetEntities.addElement(entity);
	}

	public boolean isInstant() {
		return instant;
	}

	public void setInstant(boolean instant) {
		this.instant = instant;
	}

	public abstract void run();
	public abstract void dispose();
}
