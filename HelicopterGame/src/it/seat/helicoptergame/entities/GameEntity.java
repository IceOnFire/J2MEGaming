package it.seat.helicoptergame.entities;

import it.seat.game.actions.Action;
import it.seat.game.entities.Entity;
import it.seat.helicoptergame.actions.ActionConstants;
import it.seat.helicoptergame.actions.BoundedMoveAction;
import it.seat.helicoptergame.actions.FireMoveAction;
import it.seat.helicoptergame.actions.MoveAction;
import it.seat.helicoptergame.actions.ShootAction;
import it.seat.helicoptergame.forms.GameForm;
import it.seat.helicoptergame.forms.ScrollingGroundForm;
import it.seat.helicoptergame.spaces.SpaceConstants;
import it.seat.helicoptergame.states.DirectionState;
import it.seat.helicoptergame.states.SpeedState;
import it.seat.helicoptergame.states.StateConstants;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.MediaException;

public class GameEntity extends Entity {
	private final static int PLAYER_DEFAULT_SPEED = 4;
	private final static int FIRE_DEFAULT_SPEED = 8;

	private static GameEntity singleton;

	public static GameEntity getInstance() {
		if (singleton == null) {
			singleton = new GameEntity();
		}
		return singleton;
	}

	public GameEntity() {
		super();
		form = new GameForm();
	}

	public void createGame() throws IOException, MediaException {
		((GameForm) form).createGame();

		Vector fires = new Vector();
		form.addSpace(SpaceConstants.FIRES, fires);

		Entity player = createPlayer();
		Vector players = new Vector();
		players.addElement(player);
		form.addSpace(SpaceConstants.PLAYERS, players);

		Entity ground = createGround();
		Vector grounds = new Vector();
		grounds.addElement(ground);
		form.addSpace(SpaceConstants.GROUNDS, grounds);
		((ScrollingGroundEntity) ground).createLines();
	}

	public Entity createPlayer() {
		Entity player = new Entity();

		player.addState(StateConstants.DIRECTION, new DirectionState());
		player.addState(StateConstants.SPEED, new SpeedState(
				PLAYER_DEFAULT_SPEED, PLAYER_DEFAULT_SPEED));
		player.addAction(ActionConstants.BOUNDED_MOVE, new BoundedMoveAction(
				player));
		player.addAction(ActionConstants.SHOOT, new ShootAction(player));

		player.setForm(((GameForm) form).createHelicopterForm());

		return player;
	}

	public Entity createFire() {
		Entity fire = new Entity();

		fire.addState(StateConstants.DIRECTION, new DirectionState(0, -1));
		fire.addState(StateConstants.SPEED, new SpeedState(0,
				FIRE_DEFAULT_SPEED));

		Action fireMoveAction = new FireMoveAction(fire);
		fire.addAction(ActionConstants.FIRE_MOVE, fireMoveAction);
		fire.enableAction(ActionConstants.FIRE_MOVE);

		fire.setForm(((GameForm) form).createFireForm());

		form.addToSpace(SpaceConstants.FIRES, fire);

		return fire;
	}

	public Entity createBox() {
		Entity box = new Entity();

		box.setForm(((GameForm) form).createBoxForm());
		((Sprite) box.getForm().getAvatar()).defineReferencePixel(0, 0);

		return box;
	}

	public Entity createGround() {
		Entity ground = new ScrollingGroundEntity();

		ground.addState(StateConstants.DIRECTION, new DirectionState(0, 1));
		ground.addState(StateConstants.SPEED, new SpeedState(0, 1));

		Action scrollAction = new MoveAction(ground);
		scrollAction.setInstant(false);
		ground.addAction(ActionConstants.MOVE, scrollAction);
		ground.enableAction(ActionConstants.MOVE);

		ground.setForm(new ScrollingGroundForm());

		return ground;
	}

	/*
	 * Contiene le istruzioni di aggiornamento del modello
	 */
	public void update() {
		Enumeration elements = form.getSpaces().elements();
		while (elements.hasMoreElements()) {
			Vector element = (Vector) elements.nextElement();
			for (int i = 0; i < element.size(); i++) {
				Entity entity = (Entity) element.elementAt(i);
				entity.update();
				entity.runActions();
			}
		}
		super.update();
	}

	public void manageEvent(int keyStates) {
		Entity player = (Entity) ((Vector) form.getSpaces().get(
				SpaceConstants.PLAYERS)).firstElement();
		DirectionState directionState = (DirectionState) player
				.getState(StateConstants.DIRECTION);
		// Non utilizziamo uno switch in quanto è possibile che siano attivi
		// più bit relativi alla pressione di tasti diversi
		if ((keyStates & GameCanvas.LEFT_PRESSED) != 0) {
			directionState.setHorizontal(-1);
		}
		if ((keyStates & GameCanvas.RIGHT_PRESSED) != 0) {
			directionState.setHorizontal(1);
		}
		if ((keyStates & GameCanvas.UP_PRESSED) != 0) {
			directionState.setVertical(-1);
		}
		if ((keyStates & GameCanvas.DOWN_PRESSED) != 0) {
			directionState.setVertical(1);
		}
		player.enableAction(ActionConstants.BOUNDED_MOVE);

		if ((keyStates & GameCanvas.FIRE_PRESSED) != 0) {
			player.enableAction(ActionConstants.SHOOT);
		}
	}
}
