package it.seat.helicoptergame.forms;

import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.helicoptergame.spaces.SpaceConstants;

import java.util.Vector;

public class GroundLineForm extends Form {
	public GroundLineForm() {
		super();
	}

	public void place(int x, int y) {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();

		Form groundStripeForm = groundStripeEntity.getForm();

		int oldX = groundStripeForm.getX();
		int oldY = groundStripeForm.getY();
		groundStripeForm.place(x, y);

		Vector composingEntities = (Vector) spaces
				.get(SpaceConstants.COMPOSING_ENTITIES);
		for (int i = 0; i < composingEntities.size(); i++) {
			Entity element = (Entity) composingEntities.elementAt(i);
			if (element.getForm() != groundStripeForm) {
				element.getForm().move(x - oldX, y - oldY);
			}
		}
	}

	public void move(int dx, int dy) {
		Vector composingEntities = getSpace(SpaceConstants.COMPOSING_ENTITIES);
		for (int i = 0; i < composingEntities.size(); i++) {
			Entity composingEntity = (Entity) composingEntities.elementAt(i);
			composingEntity.getForm().move(dx, dy);
		}
	}

	public boolean isOutsideVertically() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return ((GroundStripeForm) groundStripeEntity.getForm())
				.isOutsideVertically();
	}

	public boolean isInsideOfViewport() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return ((GroundStripeForm) groundStripeEntity.getForm())
				.isInsideHorizontally();
	}

	public Object getAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getX() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return groundStripeEntity.getForm().getX();
	}

	public int getY() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return groundStripeEntity.getForm().getY();
	}

	public int getWidth() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return groundStripeEntity.getForm().getWidth();
	}

	public int getHeight() {
		Entity groundStripeEntity = (Entity) getSpace(
				SpaceConstants.GROUND_STRIPE_ENTITIES).firstElement();
		return groundStripeEntity.getForm().getHeight();
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}
}
