package tilegame.keymanager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tilegame.Game;
import tilegame.objects.PacMan;
import tilegame.states.GameState;
import tilegame.states.MainMenu;
import tilegame.states.PauseState;
import tilegame.states.State;

public class KeyManager implements KeyListener {
	private Game gameReference;
	private PacMan pacReference;
	private State stateReference;
	private int before;
	private int now;

	public KeyManager(Game game) {
		before = KeyEvent.VK_SPACE; // Default
		now = before;
		pacReference = null;
		gameReference = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			gameReference.setEndGame();

		else if (e.getKeyCode() == KeyEvent.VK_L)
			gameReference.gameOver();

		else if (e.getKeyCode() == KeyEvent.VK_W)
			gameReference.gameWon();

		else if (stateReference.getWhichState() instanceof GameState) {
			now = e.getKeyCode();
			if (now == KeyEvent.VK_UP || now == KeyEvent.VK_DOWN || now == KeyEvent.VK_LEFT || now == KeyEvent.VK_RIGHT
					|| now == KeyEvent.VK_SPACE) {

				if (now == KeyEvent.VK_SPACE && !pacReference.getPacNotMoving()) {
					pacReference.setPacNotMoving(true);
				} else
					pacReference.setPacNotMoving(false);

				if (now != pacReference.getNowKey()) {
					if (pacReference != null) {
						pacReference.setBeforeKey(before);
						pacReference.setNowKey(now);
						before = now;
					}
				}
			}

			else if (now == KeyEvent.VK_SHIFT) {
				gameReference.switchToState("Pause");
				pacReference.setPacNotMoving(true);
			}

		}

		else if (stateReference.getWhichState() instanceof PauseState) {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				gameReference.switchToState("Game");
				pacReference.setBeforeKey(before);
				pacReference.setNowKey(now);
				before = now;
			}
		}

		else if (stateReference.getWhichState() instanceof MainMenu)
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gameReference.switchToState("Game");

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyIsUp(int keyCode) {
		return keyCode == KeyEvent.VK_UP ? true : false;
	}

	public boolean keyIsDown(int keyCode) {
		return keyCode == KeyEvent.VK_DOWN ? true : false;
	}

	public boolean keyIsRight(int keyCode) {
		return keyCode == KeyEvent.VK_RIGHT ? true : false;
	}

	public boolean keyIsLeft(int keyCode) {
		return keyCode == KeyEvent.VK_LEFT ? true : false;
	}

	public boolean keyIsSpace(int keyCode) {
		return keyCode == KeyEvent.VK_SPACE ? true : false;
	}

	public int getBefore() {
		return before;
	}

	public int getnow() {
		return now;
	}

	public void setBefore(int key) {
		before = key;
	}

	public void setNow(int key) {
		before = key;
	}

	public void setReferenceToPac(PacMan pacMan) {
		pacReference = pacMan;
	}

	public void setReferenceToState(State state) {
		stateReference = state;
	}
}
