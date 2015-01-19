package it.seat.helicoptergame.forms;

import it.seat.game.forms.Form;
import it.seat.helicoptergame.entities.GameEntity;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class MobileForm extends Form {
	private String id;
	private Sprite avatar;

	public MobileForm(String id, int frameWidth, int frameHeight) {
		super();
		this.id = id;
		GameForm gameForm = (GameForm)GameEntity.getInstance().getForm();
		Image image = gameForm.getImage(id);
		avatar = new Sprite(image, frameWidth, frameHeight);
		avatar.defineReferencePixel(frameWidth / 2, frameHeight / 2);
	}

	public String getId() {
		return id;
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
		return avatar.getRefPixelX();
	}

	public int getY() {
		return avatar.getRefPixelY();
	}

	public void move(int dx, int dy) {
		avatar.move(dx, dy);
	}

	public void place(int x, int y) {
		avatar.setRefPixelPosition(x, y);
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public void update() {
		avatar.nextFrame();
	}

	public void destroy() {
		GameForm gameForm = (GameForm) GameEntity.getInstance().getForm();
		gameForm.getLayerManager().remove(avatar);
		super.destroy();
	}
}
