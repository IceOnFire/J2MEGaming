package it.ice.puyomaycry.actions;

import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;
import it.ice.sooge.Action;
import it.ice.sooge.Entity;

public class CoccolaAction extends Action {
	public CoccolaAction(Entity agent) {
		super(agent);
	}

	protected void run() {
		PuyoProperty affetto = (PuyoProperty) agent
				.getProperty(Properties.AFFETTO);
		affetto.increase();
		agent.setActiveState(States.COCCOLANTE);
	}
}
