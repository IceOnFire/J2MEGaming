package it.ice.puyomaycry.forms;

import it.ice.puyomaycry.PMCController;
import it.ice.sooge.Event;
import it.ice.sooge.Events;
import it.ice.sooge.Form;

import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class J2MEForm extends Form {
	protected Sprite avatar;

	public J2MEForm(String imagePath, int frameWidth, int frameHeight)
			throws IOException {
		super();
		Image image = Image.createImage(imagePath);
		avatar = new Sprite(image, frameWidth, frameHeight);
		avatar.defineReferencePixel(frameWidth / 2, frameHeight / 2);
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

	public void activate() {
		avatar.setFrame(0);
		PMCController.getInstance().insert(avatar, 0);
	}

	public void update() {
		avatar.nextFrame();
		if (avatar.getFrame() <= 0
				|| avatar.getFrame() >= avatar.getFrameSequenceLength() - 1) {
			notifyEvent(new Event(Events.ANIMATION_END));
		}
	}

	public void dispose() {
		PMCController.getInstance().remove(avatar);
	}
}
