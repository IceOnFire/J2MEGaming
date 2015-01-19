package it.ice.puyomaycry.actions;

import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;
import it.ice.scrooge.Action;
import it.ice.scrooge.Node;

public class MangiaAction extends Action {
	public MangiaAction(Node agent) {
		super(agent);
	}

	protected void run() {
		PuyoProperty cibo = (PuyoProperty) node
				.getProperty(Properties.CIBO);
		cibo.increase();
		node.setActiveState(States.MANGIANTE);
	}
}
