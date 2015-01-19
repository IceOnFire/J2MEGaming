package it.seat.helicoptergame.actions;

import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.forms.GameForm;
import it.seat.helicoptergame.sounds.SoundConstants;
import it.seat.helicoptergame.spaces.SpaceConstants;

import java.util.Vector;

import javax.microedition.lcdui.game.Sprite;

public class FireMoveAction extends MoveAction {
	public FireMoveAction(Entity entity) {
		super(entity);
		setInstant(false);
	}

	public void run() {
		super.run();

		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();

		/* destroy the fire if it exits from the screen */
		if (isOutOfBounds(userEntity.getForm())) {
			gameForm.removeFromSpace(SpaceConstants.FIRES, userEntity);
			userEntity.destroy();
			return;
		}

		/* find a collision with a destroyable entity */
		Entity ground = (Entity) gameForm.getSpace(SpaceConstants.GROUNDS)
				.firstElement();
		Form groundForm = ground.getForm();
		Vector destroyableEntities = groundForm.getSpace(
				SpaceConstants.RESTING_ENTITIES);

		Entity entityToDestroy = null;
		for (int i = 0; i < destroyableEntities.size()
				&& entityToDestroy == null; i++) {
			Entity destroyableEntity = (Entity) destroyableEntities
					.elementAt(i);
			if (collide(userEntity.getForm(), destroyableEntity.getForm())) {
				entityToDestroy = destroyableEntity;
			}
		}

		/* an entity must be destroyed */
		if (entityToDestroy != null) {
			/* plays the explosion sound */
			gameForm.play(SoundConstants.EXPLOSION);

			/* sets the explosion animation on the entity to destroy */
			entityToDestroy.setForm(gameForm
					.createExplosionForm(entityToDestroy.getForm()));

			/* starts a thread that will destroy the entity after the animation */
			new EntityDestroyer(entityToDestroy).start();

			/* removes the fire entity */
			gameForm.removeFromSpace(SpaceConstants.FIRES, userEntity);
			userEntity.destroy();
		}
	}

	public boolean isOutOfBounds(Form form) {
		Form gameForm = GameEntity.getInstance().getForm();
		int left = gameForm.getX();
		int right = gameForm.getWidth();
		int top = gameForm.getY();
		int bottom = gameForm.getHeight();
		return form.getX() - form.getWidth() / 2 < left
				|| form.getX() + form.getWidth() / 2 > right
				|| form.getY() - form.getHeight() / 2 < top
				|| form.getY() + form.getHeight() / 2 > bottom;
	}

	public boolean collide(Form form1, Form form2) {
		return ((Sprite) form1.getAvatar()).collidesWith((Sprite) form2
				.getAvatar(), false);
	}

	private class EntityDestroyer extends Thread {
		private Entity entityToDestroy;

		public EntityDestroyer(Entity destroyableEntity) {
			this.entityToDestroy = destroyableEntity;
		}

		public void run() {
			Form gameForm = GameEntity.getInstance().getForm();
			Entity ground = (Entity) gameForm.getSpace(SpaceConstants.GROUNDS)
					.firstElement();
			Form groundForm = ground.getForm();
			Vector destroyableEntities = groundForm
					.getSpace(SpaceConstants.RESTING_ENTITIES);

			/* makes the object no more destroyable */
			destroyableEntities.removeElement(entityToDestroy);

			/* waits for the explosion animation to complete */
			try {
				Sprite avatar = (Sprite) entityToDestroy.getForm().getAvatar();
				while (avatar.getFrame() < avatar.getFrameSequenceLength() - 1) {
					sleep(10);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/* removes the box from the game */
			Vector lines = groundForm.getSpace(SpaceConstants.LINES);
			for (int i = 0; i < lines.size(); i++) {
				Entity line = (Entity) lines.elementAt(i);
				Vector composingEntities = line.getForm().getSpace(
						SpaceConstants.COMPOSING_ENTITIES);
				composingEntities.removeElement(entityToDestroy);
			}
			entityToDestroy.destroy();
		}
	}
}
