package it.ice.phg.forms;

import it.ice.phg.PHGController;
import it.ice.scrooge.Form;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class TiledLayerForm extends Form {
	public final static int TILE_SIZE = 20;

	private TiledLayer avatar;

	private int animatedTile;
	private int tileCounter;
	private int staticTile;

	public TiledLayerForm(String imagePath, int tileWidth, int tileHeight)
			throws IOException {
		super();
		Image image = Image.createImage(imagePath);
		PHGController controller = PHGController.getInstance();
		int screenWidth = controller.getCanvas().getWidth();
		// int screenHeight = controller.getCanvas().getHeight();
		int cols = screenWidth / tileHeight;
		avatar = new TiledLayer(cols + 2, 1, image, tileWidth, tileHeight);

		int lineCols = cols + 2;
		staticTile = 2;
		tileCounter = 0;
		animatedTile = avatar.createAnimatedTile(staticTile);

		avatar.fillCells(0, 0, lineCols, 1, 1);
		for (int col = 0; col < lineCols; col++) {
			int cell = (int) ((new Random().nextInt(10) + 1) * 0.1);
			if (cell > 0) {
				avatar.setCell(col, 0, animatedTile);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// move((screenWidth - getWidth()) / 2, -getHeight());
	}

	public Object getAvatar() {
		return avatar;
	}

	public int getHeight() {
		return avatar.getHeight();
	}

	public int getWidth() {
		return avatar.getWidth();
	}

	public int getX() {
		return avatar.getX();
	}

	public int getY() {
		return avatar.getY();
	}

	public void move(int dx, int dy) {
		avatar.move(dx, dy);
	}

	public void place(int x, int y) {
		avatar.setPosition(x, y);
	}

	public void activate() {
		PHGController.getInstance().append(avatar);
	}

	public void update() {
		tileCounter++;
		if (tileCounter > PHGController.MILLISECONDS_PER_TIMESTEP) {
			if (staticTile > 3)
				staticTile = 2;
			avatar.setAnimatedTile(animatedTile, (++staticTile) % 2 + 2);
			tileCounter = 0;
		}
	}

	public void dispose() {
		PHGController.getInstance().remove(avatar);
	}

	boolean isOutsideVertically() {
		return getY() > PHGController.getInstance().getCanvas().getHeight();
	}

	boolean isInsideHorizontally() {
		Canvas canvas = PHGController.getInstance().getCanvas();
		int left = 0;
		int right = canvas.getWidth();
		return getX() > left || getX() + getWidth() < right;
	}
}
