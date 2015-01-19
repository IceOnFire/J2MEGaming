package it.seat.helicoptergame.actions;

import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.forms.GameForm;
import it.seat.helicoptergame.spaces.SpaceConstants;
import it.seat.helicoptergame.states.DirectionState;
import it.seat.helicoptergame.states.SpeedState;
import it.seat.helicoptergame.states.StateConstants;

import java.util.Vector;

public class BoundedMoveAction extends MoveAction {
	public BoundedMoveAction(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		DirectionState directionState = (DirectionState) userEntity
				.getState(StateConstants.DIRECTION);

		SpeedState speedState = (SpeedState) userEntity
				.getState(StateConstants.SPEED);

		Form userForm = userEntity.getForm();

		// muove l'elicottero
		int oldX = userForm.getX();
		int oldY = userForm.getY();

		userForm.move(directionState.getHorizontal()
				* speedState.getHorizontal(), directionState.getVertical()
				* speedState.getVertical());

		int newX = userForm.getX();
		int newY = userForm.getY();

		// se l'elicottero esce fuori dal viewport
		if (isOutsideVertically(userForm)) {
			userForm.place(oldX, oldY);
		} else if (isOutsideHorizontally(userForm)) {
			Entity gameEntity = GameEntity.getInstance();
			Entity ground = (Entity) ((Vector) gameEntity.getForm().getSpaces()
					.get(SpaceConstants.GROUNDS)).firstElement();
			ground.getForm().move(oldX - newX, 0);
			userForm.place(oldX, newY);
		}
	}

	public boolean isOutsideHorizontally(Form form) {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		int left = gameForm.getX();
		int right = gameForm.getWidth();
		return form.getX() - form.getWidth() / 2 < left
				|| form.getX() + form.getWidth() / 2 > right;
	}

	public boolean isOutsideVertically(Form form) {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		int top = gameForm.getY();
		int bottom = gameForm.getHeight();
		return form.getY() - form.getHeight() / 2 < top
				|| form.getY() + form.getHeight() / 2 > bottom;
	}
}
