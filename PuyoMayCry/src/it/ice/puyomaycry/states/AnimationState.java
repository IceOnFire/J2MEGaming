package it.ice.puyomaycry.states;

import it.ice.sooge.Entity;
import it.ice.sooge.State;

public class AnimationState extends State {
	public void change(Entity entity) {
		entity.setActiveState(States.AVANTI);
	}
}
