package it.ice.phg;

import it.ice.phg.actions.Actions;
import it.ice.phg.actions.MoveAction;
import it.ice.phg.entities.Helicopter;
import it.ice.phg.forms.Forms;
import it.ice.phg.forms.GameForm;
import it.ice.phg.forms.GroundForm;
import it.ice.phg.forms.SpriteForm;
import it.ice.phg.forms.TiledLayerForm;
import it.ice.phg.properties.DirectionProperty;
import it.ice.phg.properties.Properties;
import it.ice.phg.properties.SpeedProperty;
import it.ice.phg.states.FlyingState;
import it.ice.phg.states.States;
import it.ice.scrooge.Action;
import it.ice.scrooge.Form;
import it.ice.scrooge.Node;
import it.ice.scrooge.Property;
import it.ice.scrooge.State;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.game.Sprite;

public class PHGBuilder {
	private Node game;

	public void buildGame() throws IOException {
		game = new Node();

		State runningState = new State();
		Form gameForm = new GameForm(0, 0, PHGController.getInstance()
				.getCanvas().getWidth(), PHGController.getInstance()
				.getCanvas().getHeight());
		game.setForm(States.RUNNING, gameForm);
		game.addState(States.RUNNING, runningState);

		game.setActiveState(States.RUNNING);
	}

	public void buildGround() throws IOException {
		Node ground = new Node();
		game.addChild(ground);

		/* properties */
		Property direction = new DirectionProperty(0, 1);
		ground.addProperty(Properties.DIRECTION, direction);

		Property speed = new SpeedProperty(0, 1);
		ground.addProperty(Properties.SPEED, speed);

		/* children */
		int rows = game.getActiveForm().getHeight() / TiledLayerForm.TILE_SIZE;
		for (int row = 0; row < rows; row++) {
			buildGroundStripe(ground, row);
		}

		/* states */
		State scrolling = new State();
		Form form = new GroundForm(0, 0, game.getActiveForm().getWidth(), game
				.getActiveForm().getHeight());
		ground.setForm(States.SCROLLING, form);
		ground.addState(States.SCROLLING, scrolling);

		ground.setActiveState(States.SCROLLING);

		/* actions */
		Action scrollAction = new MoveAction(ground);
		scrollAction.setPersistent(true);
		ground.addAction(Actions.SCROLL, scrollAction);

		ground.enableAction(Actions.SCROLL);
	}

	public void buildGroundStripe(Node parent, int row) throws IOException {
		Node groundStripe = new Node();
		parent.addChild(groundStripe);

		int cols = game.getActiveForm().getWidth() / TiledLayerForm.TILE_SIZE;
		for (int col = 0; col < cols + 2; col++) {
			int cell = (int) ((new Random().nextInt(20) + 1) * 0.05);
			if (cell > 0) {
				buildBox(groundStripe, row, col);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		State scrolling = new State();
		Form form = new TiledLayerForm(getImagePath(Forms.GROUND), 20, 20);
		form.place(0, row * TiledLayerForm.TILE_SIZE);
		groundStripe.setForm(States.SCROLLING, form);
		groundStripe.addState(States.SCROLLING, scrolling);

		groundStripe.setActiveState(States.SCROLLING);
	}

	public void buildBox(Node parent, int row, int col) throws IOException {
		Node box = new Node();
		parent.addChild(box);

		State idleState = new State();
		Form form = new SpriteForm(getImagePath(Forms.BOX), 20, 20);
		Sprite avatar = (Sprite) form.getAvatar();
		avatar.defineReferencePixel(0, 0);
		form.place((col - 1) * form.getWidth(), row * form.getWidth());
		box.setForm(States.IDLE, form);
		box.addState(States.IDLE, idleState);

		box.setActiveState(States.IDLE);
	}

	public void buildHelicopter() throws IOException {
		Node helicopter = new Helicopter();
		game.addChild(helicopter);

		/* properties */
		Property direction = new DirectionProperty();
		helicopter.addProperty(Properties.DIRECTION, direction);

		Property speed = new SpeedProperty(Helicopter.DEFAULT_SPEED,
				Helicopter.DEFAULT_SPEED);
		helicopter.addProperty(Properties.SPEED, speed);

		/* states */
		State flying = new FlyingState();
		Form form = new SpriteForm(getImagePath(Forms.HELICOPTER), 70, 83);
		helicopter.setForm(States.FLYING, form);
		helicopter.addState(States.FLYING, flying);

		helicopter.setActiveState(States.FLYING);

		/* actions */
		Action boundedMoveAciton = new MoveAction(helicopter);
		helicopter.addAction(Actions.FLY, boundedMoveAciton);

		int screenWidth = PHGController.getInstance().getCanvas().getWidth();
		int screenHeight = PHGController.getInstance().getCanvas().getHeight();
		helicopter.getActiveForm().place(screenWidth / 2,
				screenHeight - form.getHeight() / 2 - 1);
	}

	public void buildFire(Node parent) throws IOException {
		Node fire = new Node();
		parent.addChild(fire);
	}

	public Node getGame() {
		return game;
	}

	public String getImagePath(int id) throws IOException {
		String path = null;
		switch (id) {
		case Forms.GROUND:
			path = Forms.GROUND_PATH;
			break;
		case Forms.BOX:
			path = Forms.BOX_PATH;
			break;
		case Forms.HELICOPTER:
			path = Forms.HELICOPTER_PATH;
			break;
		}
		return path;
	}
}
