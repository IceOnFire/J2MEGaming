package it.ice.sooge;

public class State {
	protected Form form;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public void activate() {
		if (form != null) {
			form.activate();
		}
	}

	public void update() {
		if (form != null) {
			form.update();
		}
	}

	public void dispose() {
		if (form != null) {
			form.dispose();
		}
	}

	public void change(Entity entity) {
		// does nothing
	}
}
