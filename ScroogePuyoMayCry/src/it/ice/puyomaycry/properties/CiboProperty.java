package it.ice.puyomaycry.properties;

import it.ice.puyomaycry.states.States;

public class CiboProperty extends PuyoProperty {
	public void updateNode() {
		node.setActiveState(States.AFFAMATO);
	}
}
