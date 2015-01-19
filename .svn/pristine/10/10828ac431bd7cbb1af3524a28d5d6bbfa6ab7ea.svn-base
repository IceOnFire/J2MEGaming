package it.ice.puyomaycry.actions;

import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.puyomaycry.states.States;
import it.ice.sooge.Action;
import it.ice.sooge.Entity;

public class MangiaAction extends Action {
	public MangiaAction(Entity agent) {
		super(agent);
	}

	protected void run() {
		PuyoProperty cibo = (PuyoProperty) agent
				.getProperty(Properties.CIBO);
		cibo.increase();
		agent.setActiveState(States.MANGIANTE);
	}
}
