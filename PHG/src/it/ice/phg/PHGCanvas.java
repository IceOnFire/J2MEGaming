package it.ice.phg;

import it.ice.scrooge.Node;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class PHGCanvas extends GameCanvas implements Runnable {
	private long lastIntegrationTime = 0;
	private long lastFrameTime = 0;

	private boolean running;

	public PHGCanvas() {
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
		Node game = PHGController.getInstance().getGame();
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastIntegrationTime;
		if (elapsedTime > PHGController.MILLISECONDS_PER_TIMESTEP) {
			game.update();
			lastIntegrationTime = System.currentTimeMillis();
		}
	}

	private void render() {
		Graphics g = getGraphics();
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());
		PHGController.getInstance().paint(g, 0, 0);
	}

	private void manageEvents() {
		int keyStates = getKeyStates();
		PHGController.getInstance().manageEvent(keyStates);
	}

	private void synchronizeFrameRate() {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		if (elapsedTime < PHGController.MILLISECONDS_PER_FRAME) {
			try {
				Thread
						.sleep(PHGController.MILLISECONDS_PER_FRAME
								- elapsedTime);
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
