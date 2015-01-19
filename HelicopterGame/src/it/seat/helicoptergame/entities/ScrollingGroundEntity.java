package it.seat.helicoptergame.entities;

import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.helicoptergame.forms.GroundStripeForm;
import it.seat.helicoptergame.spaces.SpaceConstants;

import java.util.Vector;

public class ScrollingGroundEntity extends Entity {
	public ScrollingGroundEntity() {
		super();
	}

	public void createLines() {
		Form gameForm = GameEntity.getInstance().getForm();
		int rows = gameForm.getHeight() / GroundStripeForm.TILE_HEIGHT;
		for (int i = 0; i < rows + 2; i++) {
			Entity line = createNewLine();
			Form form = line.getForm();
			form.move(0, (rows + 1 - i) * GroundStripeForm.TILE_HEIGHT);
		}
	}

	public Entity createNewLine() {
		Entity newLine = new GroundLineEntity();
		Vector lines = form.getSpace(SpaceConstants.LINES);
		lines.addElement(newLine);

		return newLine;
	}

	public void update() {
		Vector lines = form.getSpace(SpaceConstants.LINES);
		for (int i = 0; i < lines.size(); i++) {
			Entity line = (Entity) lines.elementAt(i);
			line.update();
		}
		super.update();
	}

	public void removeLastLine() {
		Vector restingEntities = form.getSpace(SpaceConstants.RESTING_ENTITIES);
		Vector lines = form.getSpace(SpaceConstants.LINES);

		Entity lastLine = (Entity) lines.firstElement();
		Vector composingEntities = lastLine.getForm().getSpace(
				SpaceConstants.COMPOSING_ENTITIES);

		for (int i = 0; i < composingEntities.size(); i++) {
			Entity restingEntity = (Entity) composingEntities.elementAt(i);
			restingEntities.removeElement(restingEntity);
		}
		lines.removeElement(lastLine);
		lastLine.destroy();
	}

	public void appendNewLine() {
		Vector lines = form.getSpace(SpaceConstants.LINES);
		Entity firstLine = (Entity) lines.lastElement();
		Form firstLineForm = firstLine.getForm();

		Entity newLine = createNewLine();
		Form form = newLine.getForm();

		form.place(firstLineForm.getX(), firstLineForm.getY()
				- firstLineForm.getHeight());
	}

	public void destroy() {
		Vector restingEntities = form.getSpace(SpaceConstants.RESTING_ENTITIES);
		for (int i = 0; i < restingEntities.size(); i++) {
			Entity restingEntity = (Entity) restingEntities.elementAt(i);
			restingEntity.destroy();
		}
		restingEntities.removeAllElements();

		Vector lines = form.getSpace(SpaceConstants.LINES);
		for (int i = 0; i < lines.size(); i++) {
			Entity line = (Entity) lines.elementAt(i);
			line.destroy();
		}
		lines.removeAllElements();
		super.destroy();
	}
}
