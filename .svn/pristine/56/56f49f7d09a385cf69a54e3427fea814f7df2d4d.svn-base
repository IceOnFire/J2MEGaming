package it.seat.helicoptergame.forms;

import it.seat.game.forms.Form;
import it.seat.helicoptergame.sounds.SoundConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

public class GameForm extends Form {
	private final static boolean soundEnabled = false;

	private Hashtable images;
	private LayerManager layerManager;
	private Hashtable players;
	private int x, y;
	private int width, height;

	public GameForm() {
		super();
		images = new Hashtable();
		layerManager = new LayerManager();
		players = new Hashtable();
	}

	public void createGame() throws IOException, MediaException {
		Hashtable imagePaths = new Hashtable();
		imagePaths.put(FormConstants.HELICOPTER, FormConstants.HELICOPTER_PATH);
		imagePaths.put(FormConstants.FIRE, FormConstants.FIRE_PATH);
		imagePaths.put(FormConstants.BOX, FormConstants.BOX_PATH);
		imagePaths.put(FormConstants.GROUND, FormConstants.GROUND_PATH);
		imagePaths.put(FormConstants.EXPLOSION, FormConstants.EXPLOSION_PATH);
		loadImages(imagePaths);

		if (soundEnabled) {
			Hashtable soundPaths = new Hashtable();
			soundPaths.put(SoundConstants.SHOOT, SoundConstants.SHOOT_PATH);
			soundPaths.put(SoundConstants.EXPLOSION,
					SoundConstants.EXPLOSION_PATH);
			loadSounds(soundPaths);
			// }
			Hashtable musicPaths = new Hashtable();
			musicPaths.put(SoundConstants.MUSIC, SoundConstants.MUSIC_PATH);
			loadMusic(musicPaths);

			play(SoundConstants.MUSIC);
		}
	}

	public void addImage(String imageId, Image imagePath) {
		images.put(imageId, imagePath);
	}

	public Image getImage(String imageId) {
		return (Image) images.get(imageId);
	}

	public LayerManager getLayerManager() {
		return layerManager;
	}

	public void addPlayer(String soundId, Player player) {
		players.put(soundId, player);
	}

	public Player getPlayer(String soundId) {
		return (Player) players.get(soundId);
	}

	public Object getAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		layerManager.setViewWindow(x, y, width, height);
	}

	public void loadImages(Hashtable paths) throws IOException {
		Enumeration keys = paths.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) paths.get(key);
			Image image = null;
			image = Image.createImage(value);
			addImage(key, image);
		}
	}

	public void loadMusic(Hashtable paths) throws IOException, MediaException {
		Enumeration keys = paths.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) paths.get(key);
			InputStream is = getClass().getResourceAsStream(value);
			Player player = Manager.createPlayer(is, "audio/midi");
			player.realize();
			player.setLoopCount(-1);
			player.prefetch();
			addPlayer(key, player);
		}
	}

	public void loadSounds(Hashtable paths) throws IOException, MediaException {
		Enumeration keys = paths.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) paths.get(key);
			InputStream is = getClass().getResourceAsStream(value);
			Player player = Manager.createPlayer(is, "audio/mpeg");
			player.realize();
			player.prefetch();
			addPlayer(key, player);
		}
	}

	public void move(int dx, int dy) {
		setBounds(dx, dy, width, height);
	}

	public void place(int x, int y) {
		setBounds(x, y, width, height);
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void play(final String sound) {
		if (soundEnabled) {
			new Thread() {
				public void run() {
					Player player = getPlayer(sound);
					try {
						player.stop();
						player.setMediaTime(0);
						player.start();
					} catch (MediaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	public Form createHelicopterForm() {
		MobileForm form = new MobileForm(FormConstants.HELICOPTER, 70, 83);
		form.place(getWidth() / 2, getHeight() - form.getHeight() / 2 - 1);
		layerManager.insert((Layer) form.getAvatar(), 0);

		return form;
	}

	public Form createFireForm() {
		MobileForm form = new MobileForm(FormConstants.FIRE, 3, 3);
		layerManager.insert((Layer) form.getAvatar(), 1);
		return form;
	}

	public Form createBoxForm() {
		Form form = new MobileForm(FormConstants.BOX, 20, 20);
		layerManager.insert((Layer) form.getAvatar(), 1);
		return form;
	}

	public Form createExplosionForm(Form explodingForm) {
		Form form = new MobileForm(FormConstants.EXPLOSION, 20, 20);
		Sprite newAvatar = (Sprite) form.getAvatar();
		newAvatar.setFrameSequence(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
				10, 11, 12, 13, 14, 15, 16, 17 });
		form.place(explodingForm.getX(), explodingForm.getY());
		layerManager.remove((Layer) explodingForm.getAvatar());
		layerManager.insert(newAvatar, 1);

		return form;
	}
}
