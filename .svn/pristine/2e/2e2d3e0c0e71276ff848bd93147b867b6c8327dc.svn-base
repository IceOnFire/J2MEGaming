package it.ice.puyomaycry;

import it.ice.puyomaycry.actions.Actions;
import it.ice.scrooge.ActionEvent;
import it.ice.scrooge.Node;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.midlet.MIDlet;

public class PMCController extends LayerManager implements CommandListener {
	public final static int WORLD_INTEGRATION_FREQUENCY = 5;
	public final static int MILLISECONDS_PER_TIMESTEP = 1000 / WORLD_INTEGRATION_FREQUENCY;
	public final static int MILLISECONDS_PER_FRAME = 40;

	private static PMCController singleton;

	private MIDlet midlet;
	private PMCCanvas canvas;
	private Node game;
	private Command exitCommand;
	private Command coccolaCommand;
	private Command mangiaCommand;

	public static void init(MIDlet midlet) {
		if (singleton == null) {
			singleton = new PMCController(midlet);
		}
	}

	public static PMCController getInstance() {
		return singleton;
	}

	private PMCController(MIDlet midlet) {
		super();
		this.midlet = midlet;
		canvas = new PMCCanvas();

		exitCommand = new Command("Esci", Command.EXIT, 1);
		canvas.addCommand(exitCommand);

		coccolaCommand = new Command("Coccola", Command.ITEM, 1);
		canvas.addCommand(coccolaCommand);

		mangiaCommand = new Command("Mangia", Command.ITEM, 1);
		canvas.addCommand(mangiaCommand);

		canvas.setCommandListener(this);
	}

	public void paint(Graphics g, int x, int y) {
		super.paint(g, (canvas.getWidth() >> 1) - 32,
				(canvas.getHeight() >> 1) - 32);
	}

	public void run() throws IOException {
		PMCBuilder builder = new PMCBuilder();
		createGame(builder);
		game = builder.getGame();

		Display.getDisplay(midlet).setCurrent(canvas);
		Thread animation = new Thread(canvas);
		animation.start();
	}

	private void createGame(PMCBuilder builder) throws IOException {
		builder.buildGame();
		builder.buildBackground();
		builder.buildPuyo();
	}

	public Node getGame() {
		return game;
	}

	public void manageEvent(int keyStates) {
		// TODO Auto-generated method stub

	}

	public void commandAction(Command command, Displayable displayable) {
		if (command == exitCommand) {
			midlet.notifyDestroyed();
		} else if (command == coccolaCommand) {
			game.onActionEvent(new ActionEvent(Actions.COCCOLA));
		} else if (command == mangiaCommand) {
			game.onActionEvent(new ActionEvent(Actions.MANGIA));
		}
	}
}
