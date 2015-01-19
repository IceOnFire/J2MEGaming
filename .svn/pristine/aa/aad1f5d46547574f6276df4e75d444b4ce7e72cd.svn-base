package it.ice.scrooge;

import java.util.Vector;

public abstract class Property {
	protected Vector listeners;
	protected Node node;

	protected Property() {
		listeners = new Vector();
	}

	public void setNode(Node node) {
		this.node = node;
	}

	protected abstract void update();

	public abstract Object getValue();

	public void addPropertyListener(PropertyListener listener) {
		listeners.addElement(listener);
	}

	public void notifyPropertyChanged() {
		for (int i = 0; i < listeners.size(); i++) {
			PropertyListener listener = (PropertyListener) listeners
					.elementAt(i);
			listener.onPropertyChange(this);
		}
	}
}
