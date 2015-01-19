package it.ice.phg;

import it.ice.phg.actions.Actions;
import it.ice.scrooge.ActionEvent;
import it.ice.scrooge.Node;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.midlet.MIDlet;

public class PHGController extends LayerManager implements CommandListener {
	public final static int WORLD_INTEGRATION_FREQUENCY = 100;
	public final static int MILLISECONDS_PER_TIMESTEP = 1000 / WORLD_INTEGRATION_FREQUENCY;
	public final static int MILLISECONDS_PER_FRAME = 40;

	private static PHGController singleton;

	private MIDlet midlet;
	private PHGCanvas canvas;
	private Node game;
	private Command exitCommand;

	public static void init(MIDlet midlet) {
		if (singleton == null) {
			singleton = new PHGController(midlet);
		}
	}

	public static PHGController getInstance() {
		return singleton;
	}

	private PHGController(MIDlet midlet) {
		super();
		this.midlet = midlet;
		canvas = new PHGCanvas();

		exitCommand = new Command("Esci", Command.EXIT, 1);
		canvas.addCommand(exitCommand);

		canvas.setCommandListener(this);
	}

	public void run() throws IOException {
		PHGBuilder builder = new PHGBuilder();

		builder.buildGame();
		builder.buildHelicopter();
		builder.buildGround();

		game = builder.getGame();

		Display.getDisplay(midlet).setCurrent(canvas);
		Thread animation = new Thread(canvas);
		animation.start();
	}

	public Node getGame() {
		return game;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void manageEvent(int keyStates) {
		if ((keyStates & GameCanvas.UP_PRESSED) != 0) {
			game.onActionEvent(new ActionEvent(Actions.FLY,
					new int[] { 0, -1 }));
		}
		if ((keyStates & GameCanvas.LEFT_PRESSED) != 0) {
			game.onActionEvent(new ActionEvent(Actions.FLY,
					new int[] { -1, 0 }));
		}
		if ((keyStates & GameCanvas.RIGHT_PRESSED) != 0) {
			game.onActionEvent(new ActionEvent(Actions.FLY,
					new int[] { 1, 0 }));
		}
		if ((keyStates & GameCanvas.DOWN_PRESSED) != 0) {
			game.onActionEvent(new ActionEvent(Actions.FLY,
					new int[] { 0, 1 }));
		}
		if ((keyStates & GameCanvas.FIRE_PRESSED) != 0) {
			game.onActionEvent(new ActionEvent(Actions.SHOOT));
		}
	}

	public void commandAction(Command command, Displayable displayable) {
		if (command == exitCommand) {
			midlet.notifyDestroyed();
		}
	}
}
