package it.ice.phg.actions;

import it.ice.phg.properties.Properties;
import it.ice.scrooge.Action;
import it.ice.scrooge.Form;
import it.ice.scrooge.Node;

import java.util.Vector;

public class MoveAction extends Action {
	public MoveAction(Node node) {
		super(node);
	}

	protected void run() {
		int[] direction = (int[]) node.getProperty(Properties.DIRECTION)
				.getValue();
		int[] speed = (int[]) node.getProperty(Properties.SPEED).getValue();

		move(node, direction[0] * speed[0], direction[1] * speed[1]);
	}

	private void move(Node node, int dx,  int dy) {
		Form form = node.getActiveForm();
		form.move(dx, dy);

		Vector children = node.getChildren();
		for (int i=0; i<children.size(); i++) {
			Node child = (Node) children.elementAt(i);
			move(child, dx, dy);
		}
	}

	protected void dispose() {
		int[] direction = (int[]) node.getProperty(Properties.DIRECTION)
				.getValue();
		direction[0] = 0;
		direction[1] = 0;
	}
}
