package it.seat.helicoptergame.actions;

import it.seat.game.actions.Action;
import it.seat.game.entities.Entity;
import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.forms.GameForm;
import it.seat.helicoptergame.sounds.SoundConstants;

public class ShootAction extends Action {
	public ShootAction(Entity entity) {
		super(entity);
	}

	public void run() {
		GameEntity game = GameEntity.getInstance();

		Entity leftFire = game.createFire();
		leftFire.getForm().place(userEntity.getForm().getX() - 8,
				userEntity.getForm().getY() - 14);

		Entity rightFire = game.createFire();
		rightFire.getForm().place(userEntity.getForm().getX() + 7,
				userEntity.getForm().getY() - 14);

		((GameForm) game.getForm()).play(SoundConstants.SHOOT);
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}
}
