package it.ice.phg;

import java.io.IOException;

import javax.microedition.midlet.MIDlet;

public class PHGMIDlet extends MIDlet {
	public PHGMIDlet() {
		// TODO Auto-generated constructor stub
	}

	protected void startApp() {
		PHGController.init(this);
		try {
			PHGController.getInstance().run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void destroyApp(boolean unconditional) {
		// TODO Auto-generated method stub

	}
}
