package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.sound.sampled.Clip;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.interfaces.GameOverListiner;
import tilegame.interfaces.SoundListiner;
import tilegame.interfaces.TickAndRender;
import tilegame.keymanager.KeyManager;
import tilegame.states.LosingState;
import tilegame.states.State;
import tilegame.states.WinState;

public class Game implements Runnable, TickAndRender, GameOverListiner, SoundListiner {

	// Display variables (window+canvas)
	private Display display;
	public static final int frameWidth = 750;
	public static final int frameHeight = 800;
	private String title;
	///////////////////////////////////////////////////////////////

	// Thread variables
	private Thread thread;
	private boolean isRunning;
	///////////////////////////////////////////////////////////////

	// Graphics+Assests variables
	private BufferStrategy bufferStrategy;
	private int howManyBuffers;
	private Graphics graphics;
	/////////////////////////////////////////////////////////////////

	// Runnable+gameLoop variables
	private final int fps = 60; // how many ticks and render we want per one second
	private final double timePerTick = 1000000000 / fps; // one second in nanos divided by the fps
	private double delta;
	private long now;
	private long lastTime;
	private static int numOfTicks;
	////////////////////////////////////////////////////////////////

	// State
	private State currentState;
	////////////////////////////////////////////////////////////////

	// Indicators
	private boolean endGame;
	////////////////////////////////////////////////////////////////

	// keyManager
	private KeyManager keyListener;
	////////////////////////////////////////////////////////////////

	public Game(String t) {
		numOfTicks = 0;
		title = t;
		isRunning = false;
		howManyBuffers = 3; // 3 is the recommended and there is no need for more
	}

	private void init() { // initialize everything
		display = new Display(title, frameWidth, frameHeight);
		keyListener = new KeyManager(this);
		currentState = new State(keyListener, this, this);
		currentState.setWhichState(currentState.getMainMenu());
		keyListener.setReferenceToState(currentState);
		display.getFrame().addKeyListener(keyListener);
		Assets.getInstance().getWhilePlay().loop(20);

		try {
			Thread.sleep(1000); // sync with background sound
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		init();
		delta = 0;
		now = 0;
		lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (isRunning) { // Game loop
			if (endGame)
				break;

			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime; // for checking fps ,can delete
			lastTime = now;

			if (delta >= 1) {
				tick();
				++numOfTicks;
				render(graphics);
				ticks++; // for checking fps can delete
				delta--;
			}

			// for checking fps, can delete
			if (timer >= 1000000000) {
				System.out.println("fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		display.closeFrame();
	}

	@Override
	public void tick() { // Updates everything
		if (currentState.getWhichState() != null) { // not neccesry to check
			if (currentState.getWhichState() instanceof LosingState
					|| currentState.getWhichState() instanceof WinState) {
				if (numOfTicks > 0 && numOfTicks % 240 == 0) {
					isRunning = false;
				}
			}
			currentState.getWhichState().tick();
		}
	}

	@Override
	public void render(Graphics g) { // Draw everything
		bufferStrategy = display.getCanvas().getBufferStrategy(); // on which buffer to paint
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(howManyBuffers);
			return; // only if there was no BS
		}
		g = bufferStrategy.getDrawGraphics(); // graphics gets his paint brash which allows it to paint
		// Clear before paint
		g.clearRect(0, 0, frameWidth, frameHeight); // clear the whole screen
		// Draw Here!
		// g.drawImage(res.getPacRight(), 10, 10, null);

		if (currentState.getWhichState() != null) {
			currentState.getWhichState().render(g);
			// MapLoader.readFile();
		}

		// End Drawing!
		bufferStrategy.show(); // this will draw everything on the buffers
		g.dispose(); // this will dispose of the graphics object
	}

	public synchronized void start() {
		if (isRunning) // will check if game is already running so to not mess up the thread
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!isRunning) // same for not running
			return;
		isRunning = false;
		try {
			thread.join(); // closes the thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getNumOfTicks() {
		return numOfTicks;
	}

	public static void resetNumOfTicks() {
		numOfTicks = 0;
	}

	public State getState() {
		return currentState;
	}

	public void setEndGame() {
		endGame = true;
	}

	public void switchToState(String state) {
		resetNumOfTicks();

		switch (state) {
		case "Main":
			currentState.setWhichState(currentState.getMainMenu());
			break;

		case "Game":
			currentState.setWhichState(currentState.getGameState());
			break;

		case "Pause":
			currentState.setWhichState(currentState.getPauseState());
			break;

		case "Lose":
			currentState.setWhichState(currentState.getLosingState());
			break;
		case "Win":
			currentState.setWhichState(currentState.getWinState());
			break;
		}
	}

	@Override
	public void gameOver() {
		resetNumOfTicks();
		stopClip(Assets.getInstance().getWhilePlay());
		switchToState("Lose");
	}

	@Override
	public void gameWon() {
		resetNumOfTicks();
		stopClip(Assets.getInstance().getWhilePlay());
		switchToState("Win");
	}

	@Override
	public void playClip(Clip clip) {
		clip.start();
		if (!clip.isRunning())
			clip.setFramePosition(0);
	}

	@Override
	public void stopClip(Clip clip) {
		clip.stop();
	}

	@Override
	public void oneTime(Clip clip) {
		clip.start();
	}
}
