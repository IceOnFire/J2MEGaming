package it.seat.helicoptergame.forms;

import it.seat.game.forms.Form;
import it.seat.helicoptergame.entities.GameEntity;

import java.util.Random;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class GroundStripeForm extends Form {
	private final static int TILE_WIDTH = 20;
	public final static int TILE_HEIGHT = 20;
	private final static int ANIM_INTERVAL = 12;

	private TiledLayer avatar;

	private int animatedTile;
	private int tileCounter;
	private int staticTile;

	public GroundStripeForm() {
		super();

		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		int cols = gameForm.getWidth() / TILE_HEIGHT;
		Image image = gameForm.getImage(FormConstants.GROUND);
		avatar = new TiledLayer(cols + 2, 1, image, TILE_WIDTH, TILE_HEIGHT);

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
		move((gameForm.getWidth() - getWidth()) / 2, -getHeight());
		gameForm.getLayerManager().append(avatar);
	}

	public void update() {
		tileCounter++;
		if (tileCounter > ANIM_INTERVAL) {
			if (staticTile > 3)
				staticTile = 2;
			avatar.setAnimatedTile(animatedTile, (++staticTile) % 2 + 2);
			tileCounter = 0;
		}
	}

	public void place(int x, int y) {
		avatar.setPosition(x, y);
	}

	public void move(int dx, int dy) {
		avatar.move(dx, dy);
	}

	public boolean isOutsideVertically() {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		int bottom = gameForm.getHeight();
		return getY() > bottom;
	}

	public boolean isInsideHorizontally() {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		int left = gameForm.getX();
		int right = gameForm.getWidth();
		return getX() > left || getX() + getWidth() < right;
	}

	public Object getAvatar() {
		return avatar;
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public int getX() {
		return avatar.getX();
	}

	public int getY() {
		return avatar.getY();
	}

	public int getWidth() {
		return avatar.getWidth();
	}

	public int getHeight() {
		return avatar.getHeight();
	}

	public void destroy() {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		gameForm.getLayerManager().remove(avatar);
		super.destroy();
	}
}
