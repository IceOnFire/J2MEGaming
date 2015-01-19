package it.ice.puyomaycry.entities;

import it.ice.sooge.Entity;
import it.ice.sooge.Event;
import it.ice.sooge.Events;
import it.ice.sooge.Property;

public class Puyo extends Entity {
	public void onPropertyChange(Property property) {
		Integer value = (Integer) property.getValue();
		if (value.intValue() <= 0) {
			property.updateState(this);
		}
	}

	public void onEvent(Event event) {
		if (event.getId() == Events.ANIMATION_END) {
			activeState.change(this);
		}
	}
}
