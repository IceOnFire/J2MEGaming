package it.ice.puyomaycry;

import it.ice.puyomaycry.actions.Actions;
import it.ice.puyomaycry.actions.CoccolaAction;
import it.ice.puyomaycry.actions.MangiaAction;
import it.ice.puyomaycry.forms.Forms;
import it.ice.puyomaycry.forms.SpriteForm;
import it.ice.puyomaycry.nodes.Puyo;
import it.ice.puyomaycry.properties.AffettoProperty;
import it.ice.puyomaycry.properties.CiboProperty;
import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.states.AvantiState;
import it.ice.puyomaycry.states.States;
import it.ice.puyomaycry.states.TransientState;
import it.ice.scrooge.Action;
import it.ice.scrooge.Form;
import it.ice.scrooge.Node;
import it.ice.scrooge.Property;
import it.ice.scrooge.State;

import java.io.IOException;

import javax.microedition.lcdui.game.Sprite;

public class PMCBuilder {
	private Node game;

	public void buildGame() throws IOException {
		game = new Node();
	}

	public void buildBackground() throws IOException {
		Node background = new Node();
		game.addChild(background);

		State defaultState = new State();
		Form backgroundForm = new SpriteForm(getImagePath(Forms.BACKGROUND),
				64, 64);
		background.setForm(States.DEFAULT, backgroundForm);
		background.addState(States.DEFAULT, defaultState);

		background.setActiveState(States.DEFAULT);
	}

	public void buildPuyo() throws IOException {
		Node puyo = new Puyo();
		game.addChild(puyo);

		/* proprietà */
		Property affetto = new AffettoProperty();
		puyo.addProperty(Properties.AFFETTO, affetto);

		Property cibo = new CiboProperty();
		puyo.addProperty(Properties.CIBO, cibo);

		/* stati */
		State nascente = new TransientState(1);
		Form form = new SpriteForm(getImagePath(Forms.PUYO_NASCE), 64, 64);
		puyo.setForm(States.NASCENTE, form);
		puyo.addState(States.NASCENTE, nascente);

		State avanti = new AvantiState();
		form = new SpriteForm(getImagePath(Forms.PUYO_DEFAULT), 64, 64);
		puyo.setForm(States.AVANTI, form);
		puyo.addState(States.AVANTI, avanti);

		State indietro = new TransientState(1);
		form = new SpriteForm(getImagePath(Forms.PUYO_DEFAULT), 64, 64);
		Sprite avatar = (Sprite) form.getAvatar();
		avatar.setTransform(Sprite.TRANS_MIRROR);
		puyo.setForm(States.INDIETRO, form);
		puyo.addState(States.INDIETRO, indietro);

		State coccolante = new TransientState(2);
		form = new SpriteForm(getImagePath(Forms.PUYO_COCCOLA), 64, 64);
		puyo.setForm(States.COCCOLANTE, form);
		puyo.addState(States.COCCOLANTE, coccolante);

		State arrabbiato = new State();
		form = new SpriteForm(getImagePath(Forms.PUYO_ARRABBIATO), 64, 64);
		puyo.setForm(States.ARRABBIATO, form);
		puyo.addState(States.ARRABBIATO, arrabbiato);

		State mangiante = new TransientState(2);
		form = new SpriteForm(getImagePath(Forms.PUYO_MANGIA), 64, 64);
		puyo.setForm(States.MANGIANTE, form);
		puyo.addState(States.MANGIANTE, mangiante);

		State affamato = new State();
		form = new SpriteForm(getImagePath(Forms.PUYO_AFFAMATO), 64, 64);
		puyo.setForm(States.AFFAMATO, form);
		puyo.addState(States.AFFAMATO, affamato);

		puyo.setActiveState(States.NASCENTE);

		/* azioni */
		Action coccola = new CoccolaAction(puyo);
		puyo.addAction(Actions.COCCOLA, coccola);

		Action mangia = new MangiaAction(puyo);
		puyo.addAction(Actions.MANGIA, mangia);
	}

	public Node getGame() {
		return game;
	}

	public String getImagePath(int id) throws IOException {
		String path = null;
		switch (id) {
		case Forms.BACKGROUND:
			path = Forms.BACKGROUND_PATH;
			break;
		case Forms.PUYO_NASCE:
			path = Forms.PUYO_NASCE_PATH;
			break;
		case Forms.PUYO_DEFAULT:
			path = Forms.PUYO_DEFAULT_PATH;
			break;
		case Forms.PUYO_COCCOLA:
			path = Forms.PUYO_COCCOLA_PATH;
			break;
		case Forms.PUYO_ARRABBIATO:
			path = Forms.PUYO_ARRABBIATO_PATH;
			break;
		case Forms.PUYO_MANGIA:
			path = Forms.PUYO_MANGIA_PATH;
			break;
		case Forms.PUYO_AFFAMATO:
			path = Forms.PUYO_AFFAMATO_PATH;
			break;
		}
		return path;
	}
}
