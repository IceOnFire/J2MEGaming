package it.ice.scrooge;

public class State {
	protected Node node;

	public void setNode(Node node) {
		this.node = node;
	}

	public void onFormEvent(FormEvent event) {
		// do nothing
	}

	public void onActionEvent(ActionEvent evt) {
		// do nothing
	}
}
