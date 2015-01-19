package it.ice.puyomaycry.states;

import it.ice.scrooge.FormEvent;
import it.ice.scrooge.FormEvents;
import it.ice.scrooge.State;

public class AvantiState extends State {
	public void onFormEvent(FormEvent event) {
		if (event.getId() == FormEvents.ANIMATION_END) {
			node.setActiveState(States.INDIETRO);
		}
	}
}
