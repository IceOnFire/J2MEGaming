package it.seat.helicoptergame.forms;

import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.helicoptergame.entities.GameEntity;
import it.seat.helicoptergame.entities.GroundLineEntity;
import it.seat.helicoptergame.entities.ScrollingGroundEntity;
import it.seat.helicoptergame.spaces.SpaceConstants;

import java.util.Vector;

public class ScrollingGroundForm extends Form {
	public ScrollingGroundForm() {
		super();

		Vector lines = new Vector();
		addSpace(SpaceConstants.LINES, lines);

		Vector restingEntities = new Vector();
		addSpace(SpaceConstants.RESTING_ENTITIES, restingEntities);
	}

	public void place(int x, int y) {
		Vector lines = getSpace(SpaceConstants.LINES);
		for (int i = 0; i < lines.size(); i++) {
			Entity line = (Entity) lines.elementAt(i);
			line.getForm().place(x, y);
		}
	}

	public void move(int dx, int dy) {
		Vector lines = getSpace(SpaceConstants.LINES);
		for (int i = 0; i < lines.size(); i++) {
			Entity line = (Entity) lines.elementAt(i);
			GroundLineForm form = (GroundLineForm) line.getForm();
			// sposta il viewport al contrario rispetto all'elicottero
			int oldGx = form.getX();
			int oldGy = form.getY();
			form.move(dx, dy);
			int newGx = form.getX();
			int newGy = form.getY();

			// se il viewport esce dai margini dello sfondo
			if (form.isInsideOfViewport()) {
				form.move(oldGx - newGx, oldGy - newGy);
			}
		}
	}

	public int getX() {
		Vector lines = (Vector) spaces.get(SpaceConstants.LINES);
		return ((Entity) lines.firstElement()).getForm().getX();
	}

	public boolean isOutsideVertically() {
		Vector lines = (Vector) spaces.get(SpaceConstants.LINES);
		return ((GroundLineForm) ((Entity) lines.firstElement()).getForm())
				.isOutsideVertically();
	}

	public boolean isInsideOfViewport() {
		Vector lines = (Vector) spaces.get(SpaceConstants.LINES);
		boolean inside = false;
		for (int i = 0; i < lines.size(); i++) {
			if (((GroundLineForm) ((GroundLineEntity) lines.elementAt(i))
					.getForm()).isInsideOfViewport()) {
				inside = true;
			}
		}
		return inside;
	}

	public void update() {
		if (isOutsideVertically()) {
			final ScrollingGroundEntity entity = (ScrollingGroundEntity) GameEntity
					.getInstance().getForm().getSpace(SpaceConstants.GROUNDS)
					.firstElement();

			entity.removeLastLine();
			// the creation of a new line needs some time, so let's do it in a
			// separate thread
			new Thread() {
				public void run() {
					entity.appendNewLine();
				}
			}.start();
		}
	}

	public Object getAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void render() {
		// TODO Auto-generated method stub

	}
}
