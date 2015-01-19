package it.seat.helicoptergame.actions;

import it.seat.game.actions.Action;
import it.seat.game.entities.Entity;
import it.seat.helicoptergame.states.DirectionState;
import it.seat.helicoptergame.states.SpeedState;
import it.seat.helicoptergame.states.StateConstants;

public class MoveAction extends Action {
	public MoveAction(Entity entity) {
		super(entity);
	}

	public void run() {
		DirectionState directionState = (DirectionState) userEntity
				.getState(StateConstants.DIRECTION);
		SpeedState speedState = (SpeedState) userEntity
				.getState(StateConstants.SPEED);

		userEntity.getForm().move(
				directionState.getHorizontal() * speedState.getHorizontal(),
				directionState.getVertical() * speedState.getVertical());
	}

	public void dispose() {
		DirectionState directionState = (DirectionState) userEntity
				.getState(StateConstants.DIRECTION);
		directionState.setHorizontal(0);
		directionState.setVertical(0);
	}
}
