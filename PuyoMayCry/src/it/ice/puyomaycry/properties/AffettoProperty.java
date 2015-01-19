package it.ice.puyomaycry.properties;

import it.ice.puyomaycry.states.States;
import it.ice.sooge.Entity;

public class AffettoProperty extends PuyoProperty {
	public void updateState(Entity entity) {
		entity.setActiveState(States.ARRABBIATO);
	}
}
