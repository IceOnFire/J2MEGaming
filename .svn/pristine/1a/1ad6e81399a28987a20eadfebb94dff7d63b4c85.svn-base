/**
 * Questa classe descrive una MIDlet che permette di verificare il funzionamento
 * delle collisioni tra Sprite, TiledLayer ed immagini
 *
 *@author Massimo Carli
 */
package it.seat.helicoptergame;

import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.forms.GameForm;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.media.MediaException;
import javax.microedition.midlet.MIDlet;

public class HelicopterGameMIDlet extends MIDlet implements CommandListener {

	/*
	 * Comando di exit
	 */
	private Command exitCommand = new Command("Esci", Command.EXIT, 1);

	/*
	 * Il riferimento all'animazione
	 */
	private HelicopterGameCanvas helicopterGameCanvas;

	/**
	 * Costruttore di default
	 */
	public HelicopterGameMIDlet() {
	}// fine costruttore

	/**
	 * Metodo che viene invocato all'avvio della Midlet
	 */
	public void startApp() {
		GameEntity engine = GameEntity.getInstance();

		// Creiamo una istanza del Canvas con l'animazione dello Sprite
		helicopterGameCanvas = new HelicopterGameCanvas();
		// Aggiungiamo la gestione del command di uscita
		helicopterGameCanvas.addCommand(exitCommand);
		helicopterGameCanvas.setCommandListener(this);

		((GameForm)engine.getForm()).setBounds(0, 0, helicopterGameCanvas.getWidth(),
				helicopterGameCanvas.getHeight());
		try {
			engine.createGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Visualizziamo il GameCanvas nel display
		Display.getDisplay(this).setCurrent(helicopterGameCanvas);
		// La facciamo partire
		Thread animation = new Thread(helicopterGameCanvas);
		animation.start();
	}// fine

	/**
	 * Metodo invocato nel caso di chiusura della Midlet
	 */
	public void destroyApp(boolean unconditional) {
	}// fine

	/**
	 * Metodo invocato nel caso in cui la Midlet venga messa in pausa
	 */
	public void pauseApp() {
	}// fine

	/**
	 * Metodo che viene invocato alla pressione del tasto exit
	 */
	public void commandAction(Command c, Displayable d) {
		// Se viene premuto il pulsante di uscita si esce dall'applicazione
		if (c == exitCommand) {
			// Fermiamo il Thread
			helicopterGameCanvas.stop();
			destroyApp(true);
			notifyDestroyed();
		}// fine if
	}// fine
}// fine Midlet

