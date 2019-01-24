package tilegame.states;

import java.awt.Font;
import java.awt.Graphics;

import tilegame.interfaces.GameOverListiner;
import tilegame.interfaces.SoundListiner;
import tilegame.interfaces.TickAndRender;
import tilegame.keymanager.KeyManager;

public class State implements TickAndRender {
	private State whichState;
	private MainMenu mainMenu;
	private GameState gameState;
	private PauseState pauseState;
	private LosingState losingState;
	private WinState winState;
	// private OtherState otherState
	protected int frameWidth, frameHeight;
	protected Font pacManFont;
	protected SoundListiner soundListiner;

	State() {
		// this constructor will prevent stack overflow.
		// this constructor will only be called from classes that are in the same
		// package.
		// classes from outside the package will only be able to use the public
		// constractor
		frameWidth = 750;
		frameHeight = 800;
		pacManFont = new Font("Verdana", Font.PLAIN, 60);
	}

	public State(KeyManager key, GameOverListiner gOverListiner, SoundListiner sListiner) {
		whichState = null;
		mainMenu = new MainMenu();
		gameState = new GameState(key, gOverListiner, sListiner);
		pauseState = new PauseState(sListiner);
		losingState = new LosingState(sListiner);
		winState = new WinState(sListiner);
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public GameState getGameState() {
		return gameState;
	}

	public PauseState getPauseState() {
		return pauseState;
	}

	public LosingState getLosingState() {
		return losingState;
	}

	public WinState getWinState() {
		return winState;
	}

	public State getWhichState() {
		return whichState;
	}

	public void setWhichState(State whichState) {
		this.whichState = whichState;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
	}
}
