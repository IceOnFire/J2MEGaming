package it.ice.phg.states;

import it.ice.phg.actions.Actions;
import it.ice.phg.properties.Properties;
import it.ice.scrooge.ActionEvent;
import it.ice.scrooge.State;

public class FlyingState extends State {
	public void onActionEvent(ActionEvent event) {
		int[] delta = (int[]) event.getParameters();
		int[] direction = (int[]) node.getProperty(Properties.DIRECTION)
				.getValue();
		direction[0] += delta[0];
		direction[1] += delta[1];
		node.enableAction(Actions.FLY);
	}
}
