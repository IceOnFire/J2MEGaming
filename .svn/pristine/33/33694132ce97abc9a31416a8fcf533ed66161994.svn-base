package it.ice.puyomaycry.actions;

import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;
import it.ice.scrooge.Action;
import it.ice.scrooge.Node;

public class CoccolaAction extends Action {
	public CoccolaAction(Node agent) {
		super(agent);
	}

	protected void run() {
		PuyoProperty affetto = (PuyoProperty) node
				.getProperty(Properties.AFFETTO);
		affetto.increase();
		node.setActiveState(States.COCCOLANTE);
	}
}
