package it.seat.helicoptergame;

import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.forms.GameForm;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class HelicopterGameCanvas extends GameCanvas implements Runnable {
	/** Simulation rate per second (Hz). */
	private final static int WORLD_INTEGRATION_FREQUENCY = 100;
	private final static int MILLISECONDS_PER_TIMESTEP = 1000 / WORLD_INTEGRATION_FREQUENCY;
	/** Frame rate. */
	private final static int MILLISECONDS_PER_FRAME = 40;

	private long lastIntegrationTime = 0;
	private long lastFrameTime = 0;

	private boolean running;

	/**
	 * Costruttore di default
	 */
	public HelicopterGameCanvas() {
		super(true);
	}

	/**
	 * Corpo del Thread
	 */
	public void run() {
		// Inizialmente è in esecuzione
		running = true;

		// Facciamo un ciclo infinito
		while (running) {
			integrateWorld();
			manageEvents();
			render();
			flushGraphics();
			synchronizeFrameRate();
		}
	}

	private void integrateWorld() {
		GameEntity game = GameEntity.getInstance();
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastIntegrationTime;
		if (elapsedTime > MILLISECONDS_PER_TIMESTEP) {
			game.update();
			lastIntegrationTime = System.currentTimeMillis();
		}
	}

	private void render() {
		GameForm gameForm = (GameForm)GameEntity.getInstance().getForm();
		Graphics g = getGraphics();
		gameForm.getLayerManager().paint(g, 0, 0);
	}

	/**
	 * Questo metodo permette di incapsulare le funzioni di interrogazione dello
	 * stato relativo alla pressione dei tasti
	 */
	protected void manageEvents() {
		int keyStates = getKeyStates();
		GameEntity game = GameEntity.getInstance();
		game.manageEvent(keyStates);
	}

	private void synchronizeFrameRate() {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		if (elapsedTime < MILLISECONDS_PER_FRAME) {
			try {
				Thread.sleep(MILLISECONDS_PER_FRAME - elapsedTime);
			} catch (InterruptedException e) {
			}
		} else {
			Thread.yield();
		}
	}

	/**
	 * Questo metodo permette di fermare il Thread
	 */
	public void stop() {
		running = false;
	}
}
