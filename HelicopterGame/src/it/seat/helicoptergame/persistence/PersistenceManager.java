package it.seat.helicoptergame.persistence;

import it.seat.game.actions.Action;
import it.seat.game.entities.Entity;
import it.seat.game.forms.Form;
import it.seat.game.states.State;
import it.seat.helicoptergame.forms.MobileForm;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class PersistenceManager {
	private static PersistenceManager singleton;

	public static PersistenceManager getInstance() {
		if (singleton == null) {
			singleton = new PersistenceManager();
		}
		return singleton;
	}

	public Entity loadEntity(String entityString)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Entity entity = new Entity();

		String[] entityParams = split(entityString, "|");

		String statesString = entityParams[0];
		String[] stateParams = split(statesString, "@");
		for (int i = 0; i < stateParams.length; i++) {
			State state = (State) Class.forName((String) stateParams[i])
					.newInstance();
			entity.addState(stateParams[i], state);
		}

		String availableActionsString = entityParams[1];
		String[] availableActionKeys = split(availableActionsString, "@");
		for (int i = 0; i < availableActionKeys.length; i++) {
			Action action = (Action) Class.forName(
					(String) availableActionKeys[i]).newInstance();
			entity.addAction(availableActionKeys[i], action);
		}

		String enabledActionsString = entityParams[2];
		String[] enabledActionKeys = split(enabledActionsString, "@");
		for (int i = 0; i < enabledActionKeys.length; i++) {
			entity.enableAction(enabledActionKeys[i]);
		}

		String formString = entityParams[3];
		Form form = loadForm(formString);

		String spacesString = entityParams[4];
		String[] spaceParams = split(spacesString, "@");
		for (int i = 0; i < spaceParams.length; i++) {
			String[] spaceEntities = split(spaceParams[i], "#");
			String id = spaceEntities[0];
			Vector space = new Vector();
			for (int j = 1; j < spaceEntities.length; j++) {

			}
			form.addSpace(id, space);
		}
		return entity;
	}

	public void saveEntity(Entity entity) throws RecordStoreFullException,
			RecordStoreNotFoundException, RecordStoreException {
		String entityString = "";

		Enumeration availableActions = entity.getAvailableActions().keys();
		while (availableActions.hasMoreElements()) {
			String availableAction = (String) availableActions.nextElement();
			entityString += availableAction + "@";
		}
		entityString += "|";

		Enumeration states = entity.getStates().keys();
		while (states.hasMoreElements()) {
			String state = (String) states.nextElement();
			entityString += state + "@";
		}
		entityString += "|";

		Enumeration enabledActions = entity.getEnabledActions().keys();
		while (enabledActions.hasMoreElements()) {
			String enabledAction = (String) enabledActions.nextElement();
			entityString += enabledAction + "@";
		}
		entityString += "|";

		MobileForm form = (MobileForm) entity.getForm();
		entityString += form.getId() + "@" + form.getWidth() + "@"
				+ form.getHeight() + "@" + form.getX() + "@" + form.getY()
				+ "@|";

		Enumeration spaces = form.getSpaces().keys();
		while (spaces.hasMoreElements()) {
			String space = (String) spaces.nextElement();
			entityString += space + "@";
		}
		entityString += "|";

		RecordStore rs = RecordStore.openRecordStore("gamedata.dat", true);
		byte bytes[] = entityString.getBytes();
		rs.addRecord(bytes, 0, bytes.length);

		rs.closeRecordStore();
	}

	public Form loadForm(String formString) {
		String[] formParams = split(formString, "@");
		String formId = formParams[0];
		int frameWidth = Integer.parseInt(formParams[1]);
		int frameHeight = Integer.parseInt(formParams[2]);
		int x = Integer.parseInt(formParams[3]);
		int y = Integer.parseInt(formParams[4]);
		Form form = new MobileForm(formId, frameWidth, frameHeight);
		form.place(x, y);

		return form;
	}

	/**
	 * Split string into multiple strings
	 * 
	 * @param original
	 *            Original string
	 * @param separator
	 *            Separator string in original string
	 * @return Splitted string array
	 */
	private String[] split(String original, String separator) {
		Vector nodes = new Vector();

		// Parse nodes into vector
		int index = original.indexOf(separator);
		while (index >= 0) {
			nodes.addElement(original.substring(0, index));
			original = original.substring(index + separator.length());
			index = original.indexOf(separator);
		}
		// Get the last node
		nodes.addElement(original);

		// Create splitted string array
		String[] result = new String[nodes.size()];
		if (nodes.size() > 0) {
			for (int loop = 0; loop < nodes.size(); loop++)
				result[loop] = (String) nodes.elementAt(loop);
		}
		return result;
	}
}
