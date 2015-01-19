package it.ice.puyomaycry;

import it.ice.puyomaycry.actions.Actions;
import it.ice.puyomaycry.actions.CoccolaAction;
import it.ice.puyomaycry.actions.MangiaAction;
import it.ice.puyomaycry.entities.Background;
import it.ice.puyomaycry.entities.Game;
import it.ice.puyomaycry.entities.Puyo;
import it.ice.puyomaycry.forms.Forms;
import it.ice.puyomaycry.forms.J2MEForm;
import it.ice.puyomaycry.properties.AffettoProperty;
import it.ice.puyomaycry.properties.CiboProperty;
import it.ice.puyomaycry.properties.Properties;
import it.ice.puyomaycry.states.AnimationState;
import it.ice.puyomaycry.states.AvantiState;
import it.ice.puyomaycry.states.States;
import it.ice.sooge.Action;
import it.ice.sooge.Entity;
import it.ice.sooge.Form;
import it.ice.sooge.Property;
import it.ice.sooge.State;

import java.io.IOException;

import javax.microedition.lcdui.game.Sprite;

public class PMCBuilder {
	private Entity game;

	public void buildGame() throws IOException {
		game = new Game();
	}

	public void buildBackground() throws IOException {
		Entity background = new Background();
		game.addChild(Game.BACKGROUND, background);

		State defaultState = new State();
		Form backgroundForm = new J2MEForm(getImagePath(Forms.BACKGROUND), 64,
				64);
		defaultState.setForm(backgroundForm);
		background.addState(States.DEFAULT, defaultState);
		background.setActiveState(States.DEFAULT);
	}

	public void buildPuyo() throws IOException {
		Entity puyo = new Puyo();
		game.addChild(Game.PUYO, puyo);

		/* proprietà */
		Property affetto = new AffettoProperty();
		affetto.addPropertyListener(puyo);
		puyo.addProperty(Properties.AFFETTO, affetto);

		Property cibo = new CiboProperty();
		cibo.addPropertyListener(puyo);
		puyo.addProperty(Properties.CIBO, cibo);

		/* stati */
		State nascente = new AnimationState();
		Form form = new J2MEForm(getImagePath(Forms.PUYO_NASCE), 64, 64);
		nascente.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.NASCENTE, nascente);

		State avanti = new AvantiState();
		form = new J2MEForm(getImagePath(Forms.PUYO_DEFAULT), 64, 64);
		avanti.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.AVANTI, avanti);

		State indietro = new AnimationState();
		form = new J2MEForm(getImagePath(Forms.PUYO_DEFAULT), 64, 64);
		Sprite avatar = (Sprite) form.getAvatar();
		avatar.setTransform(Sprite.TRANS_MIRROR);
		indietro.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.INDIETRO, indietro);

		State coccolante = new AnimationState();
		form = new J2MEForm(getImagePath(Forms.PUYO_COCCOLA), 64, 64);
		coccolante.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.COCCOLANTE, coccolante);

		State arrabbiato = new AnimationState();
		form = new J2MEForm(getImagePath(Forms.PUYO_ARRABBIATO), 64, 64);
		arrabbiato.setForm(form);
		puyo.addState(States.ARRABBIATO, arrabbiato);

		State mangiante = new AnimationState();
		form = new J2MEForm(getImagePath(Forms.PUYO_MANGIA), 64, 64);
		mangiante.setForm(form);
		form.addEventListener(puyo);
		puyo.addState(States.MANGIANTE, mangiante);

		State affamato = new AnimationState();
		form = new J2MEForm(getImagePath(Forms.PUYO_AFFAMATO), 64, 64);
		affamato.setForm(form);
		puyo.addState(States.AFFAMATO, affamato);

		puyo.setActiveState(States.NASCENTE);

		/* azioni */
		Action coccola = new CoccolaAction(puyo);
		puyo.addAction(Actions.COCCOLA, coccola);

		Action mangia = new MangiaAction(puyo);
		puyo.addAction(Actions.MANGIA, mangia);
	}

	public Entity getGame() {
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
