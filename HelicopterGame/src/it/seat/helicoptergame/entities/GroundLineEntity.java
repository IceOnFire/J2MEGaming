package it.seat.helicoptergame.entities;

import it.seat.game.entities.Entity;
import it.seat.helicoptergame.forms.GameForm;
import it.seat.helicoptergame.forms.GroundLineForm;
import it.seat.helicoptergame.forms.GroundStripeForm;
import it.seat.helicoptergame.spaces.SpaceConstants;

import java.util.Random;
import java.util.Vector;

public class GroundLineEntity extends Entity {
	public GroundLineEntity() {
		form = new GroundLineForm();

		Vector composingEntities = new Vector();
		form.addSpace(SpaceConstants.COMPOSING_ENTITIES, composingEntities);

		Vector groundStripeEntities = new Vector();
		form.addSpace(SpaceConstants.GROUND_STRIPE_ENTITIES,
				groundStripeEntities);

		createBoxEntities();
		createGroundStripeEntity();
	}

	public void update() {
		Vector composingEntities = form
				.getSpace(SpaceConstants.COMPOSING_ENTITIES);
		for (int i = 0; i < composingEntities.size(); i++) {
			Entity composingEntity = (Entity) composingEntities.elementAt(i);
			composingEntity.update();
		}
	}

	private void createBoxEntities() {
		GameEntity game = GameEntity.getInstance();
		GameForm gameForm = (GameForm) game.getForm();

		Entity ground = (Entity) gameForm.getSpace(SpaceConstants.GROUNDS)
				.firstElement();
		Vector restingEntities = ground.getForm().getSpace(
				SpaceConstants.RESTING_ENTITIES);
		Vector composingEntities = form
				.getSpace(SpaceConstants.COMPOSING_ENTITIES);

		int cols = gameForm.getWidth() / GroundStripeForm.TILE_HEIGHT;
		for (int col = 0; col < cols + 2; col++) {
			int cell = (int) ((new Random().nextInt(20) + 1) * 0.05);
			if (cell > 0) {
				Entity box = game.createBox();
				box.getForm().place((col - 1) * box.getForm().getWidth(),
						-box.getForm().getHeight());

				composingEntities.addElement(box);
				restingEntities.addElement(box);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createGroundStripeEntity() {
		Vector composingEntities = form
				.getSpace(SpaceConstants.COMPOSING_ENTITIES);
		Vector groundStripeEntities = form
				.getSpace(SpaceConstants.GROUND_STRIPE_ENTITIES);

		Entity groundStripeEntity = new Entity();
		groundStripeEntity.setForm(new GroundStripeForm());

		composingEntities.addElement(groundStripeEntity);
		groundStripeEntities.addElement(groundStripeEntity);
	}

	public void destroy() {
		Vector composingEntities = form
				.getSpace(SpaceConstants.COMPOSING_ENTITIES);
		Vector groundStripeEntities = form
				.getSpace(SpaceConstants.GROUND_STRIPE_ENTITIES);

		for (int i = 0; i < composingEntities.size(); i++) {
			Entity composingEntity = (Entity) composingEntities.elementAt(i);
			composingEntity.destroy();
		}
		composingEntities.removeAllElements();
		groundStripeEntities.removeAllElements();
	}
}
