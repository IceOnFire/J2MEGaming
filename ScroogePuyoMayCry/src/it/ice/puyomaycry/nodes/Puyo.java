package it.ice.puyomaycry.nodes;

import it.ice.puyomaycry.properties.PuyoProperty;
import it.ice.scrooge.ActionEvent;
import it.ice.scrooge.Node;
import it.ice.scrooge.Property;

public class Puyo extends Node {
	public void onPropertyChange(Property property) {
		((PuyoProperty)property).updateNode();
	}

	public void onActionEvent(ActionEvent event) {
		enableAction(event.getId());
	}
}
