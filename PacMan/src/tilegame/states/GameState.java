package tilegame.states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import tilegame.gfx.Assets;
import tilegame.interfaces.GameOverListiner;
import tilegame.interfaces.SoundListiner;
import tilegame.keymanager.KeyManager;
import tilegame.map.Map;
import tilegame.objects.Ghost;
import tilegame.objects.Object;
import tilegame.objects.PacMan;

public class GameState extends State {

	private Map currentMap;
	private Dimension dimension;
	private KeyManager listener;
	private GameOverListiner gameOverlistener;

	public GameState(KeyManager lis, GameOverListiner gOverListiner, SoundListiner sListiner) {
		super();
		currentMap = null;
		dimension = null;
		listener = lis;
		gameOverlistener = gOverListiner;
		soundListiner = sListiner;
		setMap();
		pacManFont = new Font("Verdana", Font.PLAIN, 30);
	}

	@Override
	public void tick() {
		dimension = currentMap.getArraySize();
		Object[][] objects = currentMap.getObjects();

		for (int i = 0; i < dimension.getHeight(); ++i) {
			for (int j = 0; j < dimension.width; ++j) {
				if (objects[i][j] != null)
					objects[i][j].tick();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		dimension = currentMap.getArraySize();
		Object[][] objects = currentMap.getObjects();
		ArrayList<Ghost> ghosts = currentMap.getGhostsRef();

		for (int i = 0; i < dimension.getHeight(); ++i) {
			for (int j = 0; j < dimension.width; ++j) {
				if (objects[i][j] != null && !(objects[i][j] instanceof Ghost))
					objects[i][j].render(g);
			}
		}

		for (int i = 0; i < ghosts.size(); ++i) { // this is needed to that the ghosts will be rendered on top of the
													// charrys
			ghosts.get(i).render(g);
		}

		for (int nextPos = 0, i = PacMan.getHealth(); i > 0; --i) {
			g.drawImage(Assets.getInstance().getPacRightOne(), 10 + nextPos, frameHeight - 40, null);
			nextPos += 40;
		}

		g.drawImage(Assets.getInstance().getCherry(), frameWidth - 115, frameHeight - 35, null);
		g.setFont(pacManFont);
		g.setColor(Color.YELLOW);
		g.drawString("x" + currentMap.getNumOfEatableObjects(), frameWidth - 80, frameHeight - 10);
	}

	public void setMap() {
		currentMap = new Map(listener, gameOverlistener, soundListiner);
	}

}
