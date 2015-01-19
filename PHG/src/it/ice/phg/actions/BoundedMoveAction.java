package it.ice.phg.actions;

import it.ice.phg.PHGController;
import it.ice.phg.entities.Game;
import it.ice.phg.properties.Properties;
import it.ice.scrooge.Form;
import it.ice.scrooge.Node;

public class BoundedMoveAction extends MoveAction {
	public BoundedMoveAction(Node node) {
		super(node);
	}

	protected void run() {
		int[] direction = (int[]) node.getProperty(Properties.DIRECTION)
				.getValue();
		int[] speed = (int[]) node.getProperty(Properties.SPEED).getValue();

		Form form = node.getActiveForm();

		// muove l'elicottero
		int oldX = form.getX();
		int oldY = form.getY();

		form.move(direction[0] * speed[0], direction[1] * speed[1]);

		int newX = form.getX();
		int newY = form.getY();

		// se l'elicottero esce fuori dal viewport
		if (isOutsideVertically(form)) {
			form.place(oldX, oldY);
		} else if (isOutsideHorizontally(form)) {
//			Form backgroundForm = node.getSiblings().getActiveForm();
			backgroundForm.move(oldX - newX, 0);
			form.place(oldX, newY);
		}
	}

	public boolean isOutsideHorizontally(Form form) {
		Form backgroundForm = PHGController.getInstance().getGame().getChild(
				Game.GROUND).getActiveForm();
		int left = backgroundForm.getX();
		int right = backgroundForm.getWidth();
		return form.getX() - form.getWidth() / 2 < left
				|| form.getX() + form.getWidth() / 2 > right;
	}

	public boolean isOutsideVertically(Form form) {
		Form backgroundForm = PHGController.getInstance().getGame().getChild(
				Game.GROUND).getActiveForm();
		int top = backgroundForm.getY();
		int bottom = backgroundForm.getHeight();
		return form.getY() - form.getHeight() / 2 < top
				|| form.getY() + form.getHeight() / 2 > bottom;
	}
}
