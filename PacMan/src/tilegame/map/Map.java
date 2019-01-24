package tilegame.map;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import tilegame.interfaces.GameOverListiner;
import tilegame.interfaces.SoundListiner;
import tilegame.keymanager.KeyManager;
import tilegame.objects.Cherry;
import tilegame.objects.Creature;
import tilegame.objects.Ghost;
import tilegame.objects.GreenGhost;
import tilegame.objects.Object;
import tilegame.objects.OrangeGhost;
import tilegame.objects.PacMan;
import tilegame.objects.PurpleGhost;
import tilegame.objects.RedGhost;
import tilegame.objects.Wall;
import tilegame.util.FileLoader;

public class Map {

	private KeyManager listener;
	private GameOverListiner gameOverListiner;
	private SoundListiner soundListiner;
	private Dimension arraySize;
	private final int objectWidth;
	private final int objectHeight;
	private Object[][] objects;
	private PacMan pacManRef;
	private Dimension pacManLocation;
	private ArrayList<Ghost> ghostsRederences;
	private ArrayList<Dimension> wallLocation;
	private ArrayList<Dimension> eatableObjects;
	private int numOfEatableObjects;

	public Map(KeyManager lis, GameOverListiner gOverListiner, SoundListiner sListiner) {
		listener = lis;
		gameOverListiner = gOverListiner;
		soundListiner = sListiner;
		arraySize = new Dimension();
		objectWidth = objectHeight = 30;
		objects = null;
		pacManRef = null;
		pacManLocation = new Dimension();
		ghostsRederences = new ArrayList<>();
		wallLocation = new ArrayList<>();
		eatableObjects = new ArrayList<>();
		numOfEatableObjects = 0;
		createObjects(FileLoader.loadFile("/map/map1.txt"));
	}

	public void createObjects(BufferedReader map) {
		try {
			String line = map.readLine();
			String[] splittedLine = line.split(" ");
			arraySize.setSize(Integer.parseInt(splittedLine[0]), Integer.parseInt(splittedLine[1]));
			objects = createArray();

			int i = 0;
			while ((line = map.readLine()) != null) {
				splittedLine = line.split(" ");

				for (int j = 0; j < splittedLine.length; ++j) {
					objects[i][j] = whichObject(j * objectWidth, i * objectHeight, Integer.parseInt(splittedLine[j]));
				}
				++i;
			}

			for (int j = 0; j < ghostsRederences.size(); ++j) {
				ghostsRederences.get(j).setPacRef(pacManRef);
			}

			numOfEatableObjects = eatableObjects.size();

			map.close();

		} catch (IOException e) {
			System.out.println("failed to load map");
			e.printStackTrace();
		}
	}

	public Object[][] createArray() {
		return new Object[arraySize.width][arraySize.height];
	}

	public Object whichObject(int xPos, int yPos, int num) {

		switch (num) {
		case 0:
			eatableObjects.add(new Dimension(xPos / objectWidth, yPos / objectHeight));
			return new Cherry(xPos, yPos);
		case 1:
			wallLocation.add(new Dimension(xPos / objectWidth, yPos / objectHeight));
			return new Wall(xPos, yPos);
		case 2:
			GreenGhost greenGhost = new GreenGhost(xPos, yPos);
			greenGhost.setMapRef(this);
			ghostsRederences.add(greenGhost);
			return greenGhost;
		case 3:
			PurpleGhost purpleGhost = new PurpleGhost(xPos, yPos);
			purpleGhost.setMapRef(this);
			ghostsRederences.add(purpleGhost);
			return purpleGhost;
		case 4:
			RedGhost redGhost = new RedGhost(xPos, yPos);
			redGhost.setMapRef(this);
			ghostsRederences.add(redGhost);
			return redGhost;
		case 5:
			OrangeGhost orangeGhost = new OrangeGhost(xPos, yPos);
			orangeGhost.setMapRef(this);
			ghostsRederences.add(orangeGhost);
			return orangeGhost;
		case 6:
			pacManLocation.setSize(xPos / objectWidth, yPos / objectHeight);
			PacMan pacMan = new PacMan(xPos, yPos, listener, gameOverListiner, soundListiner, this);
			listener.setReferenceToPac(pacMan);
			pacManRef = pacMan;
			return pacMan;
		default:
			return null;
		}
	}

	public void resetMap() {
		for (int i = 0; i < arraySize.getHeight(); i++) {
			for (int j = 0; j < arraySize.getWidth(); j++) {
				if (objects[i][j] instanceof Creature)
					((Creature) objects[i][j]).resetPosition();
			}
		}
	}

	public Object[][] getObjects() {
		return objects;
	}

	public Dimension getArraySize() {
		return arraySize;
	}

	public ArrayList<Ghost> getGhostsRef() {
		return ghostsRederences;
	}

	public ArrayList<Dimension> getWallLocation() {
		return wallLocation;
	}

	public ArrayList<Dimension> getEatableObjectsLocation() {
		return eatableObjects;
	}

	public void deleteObject(int yPos, int xPos) {
		objects[yPos][xPos] = null;
	}

	public int getNumOfEatableObjects() {
		return numOfEatableObjects;
	}

	public void setNumOfEatableObjects(int numOfEatableObjects) {
		this.numOfEatableObjects = numOfEatableObjects;
	}

	public void decNumOfEatableObjects() {
		--numOfEatableObjects;
	}

	public void outOfCharrys() {
		if (numOfEatableObjects == 0)
			gameOverListiner.gameWon();
	}

}
