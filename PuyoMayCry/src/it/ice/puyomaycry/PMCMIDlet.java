package it.ice.puyomaycry;

import java.io.IOException;

import javax.microedition.midlet.MIDlet;

public class PMCMIDlet extends MIDlet {

	public PMCMIDlet() {
		// TODO Auto-generated constructor stub
	}

	protected void startApp() {
		PMCController.init(this);
		try {
			PMCController.getInstance().run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	public void destroyApp(boolean unconditional) {
		// TODO Auto-generated method stub

	}
}
