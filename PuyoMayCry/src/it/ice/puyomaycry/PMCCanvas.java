package it.ice.puyomaycry;

import it.ice.sooge.Entity;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class PMCCanvas extends GameCanvas implements Runnable {
	private long lastIntegrationTime = 0;
	private long lastFrameTime = 0;

	private boolean running;

	public PMCCanvas() {
		super(true);
	}

	public void run() {
		running = true;

		while (running) {
			updateGame();
			manageEvents();
			render();
			flushGraphics();
			synchronizeFrameRate();
		}
	}

	private void updateGame() {
		Entity game = PMCController.getInstance().getGame();
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastIntegrationTime;
		if (elapsedTime > PMCController.MILLISECONDS_PER_TIMESTEP) {
			game.update();
			lastIntegrationTime = System.currentTimeMillis();
		}
	}

	private void render() {
		Graphics g = getGraphics();
		PMCController.getInstance().paint(g, 0, 0);
	}

	private void manageEvents() {
		int keyStates = getKeyStates();
		PMCController.getInstance().manageEvent(keyStates);
	}

	private void synchronizeFrameRate() {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		if (elapsedTime < PMCController.MILLISECONDS_PER_FRAME) {
			try {
				Thread.sleep(PMCController.MILLISECONDS_PER_FRAME - elapsedTime);
			} catch (InterruptedException e) {
			}
		} else {
			Thread.yield();
		}
	}

	public void stop() {
		running = false;
	}
}
