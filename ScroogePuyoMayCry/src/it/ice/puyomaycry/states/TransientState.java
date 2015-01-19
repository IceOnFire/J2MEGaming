package it.ice.puyomaycry.states;

import it.ice.scrooge.FormEvent;
import it.ice.scrooge.FormEvents;
import it.ice.scrooge.State;

public class TransientState extends State {
	private int repeats;
	private int count;

	public TransientState(int repeats) {
		this.repeats = repeats;
		count = 0;
	}

	public void onFormEvent(FormEvent event) {
		if (event.getId() == FormEvents.ANIMATION_END) {
			if (count == repeats) {
				node.setActiveState(States.AVANTI);
				count = 0;
			} else {
				count++;
			}
		}
	}
}
