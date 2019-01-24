package tilegame.gfx;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import tilegame.util.ImageLoader;
import tilegame.util.SoundControl;
import tilegame.util.SoundLoader;

public class Assets {

	private static Assets assets = new Assets(); // singleton

	// Graphics-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private int width, height;

	// MainMenu + Pause Images
	// -------------------------------------------------------------------------------------------------------------------------

	private BufferedImage backGround;
	private BufferedImage sleeping;
	private BufferedImage pacManSmile;

	// -------------------------------------------------------------------------------------------------------------------------
	// Losing
	// Screen-----------------------------------------------------------------------------------------------------
	private BufferedImage gameOverBG;
	// ---------------------------------------------------------------------------------------------------------------------------
	// GameState Images
	// -------------------------------------------------------------------------------------------------------------------------

	// Wall
	private final BufferedImage wall;
	// Pacman
	private final BufferedImage pacRightOne, pacRightTwo;
	private final BufferedImage pacUpOne, pacUpTwo;
	private final BufferedImage pacDownOne, pacDownTwo;
	private final BufferedImage pacLeftOne, pacLeftTwo;
	// Ghosts - orange
	private final BufferedImage orangeGhostRightOne, orangeGhostRightTwo;
	private final BufferedImage orangeGhostUpOne, orangeGhostUpTwo;
	private final BufferedImage orangeGhostDownOne, orangeGhostDownTwo;
	private final BufferedImage orangeGhostLeftOne, orangeGhostLeftTwo;
	// Ghosts - red
	private final BufferedImage redGhostRightOne, redGhostRightTwo;
	private final BufferedImage redGhostUpOne, redGhostUpTwo;
	private final BufferedImage redGhostDownOne, redGhostDownTwo;
	private final BufferedImage redGhostLeftOne, redGhostLeftTwo;
	// Ghosts - purple
	private final BufferedImage purpleGhostRightOne, purpleGhostRightTwo;
	private final BufferedImage purpleGhostUpOne, purpleGhostUpTwo;
	private final BufferedImage purpleGhostDownOne, purpleGhostDownTwo;
	private final BufferedImage purpleGhostLeftOne, purpleGhostLeftTwo;
	// Ghosts - green
	private final BufferedImage greenGhostRightOne, greenGhostRightTwo;
	private final BufferedImage greenGhostUpOne, greenGhostUpTwo;
	private final BufferedImage greenGhostDownOne, greenGhostDownTwo;
	private final BufferedImage greenGhostLeftOne, greenGhostLeftTwo;
	// Fruits - cherry
	private final BufferedImage cherry;
	// ------------------------------------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// Sounds-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private final Clip whilePlay;
	private final Clip eatCharry;
	private final Clip youFailed;
	private final Clip youWin;

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private Assets() {

		// BackGround-------------------------------------------------------------------------------------------------------------------------

		backGround = ImageLoader.loadImage("/textures/BG.png");
		sleeping = ImageLoader.loadImage("/textures/SL.png");
		gameOverBG = ImageLoader.loadImage("/textures/GO.png");
		pacManSmile = ImageLoader.loadImage("/textures/pacManSmile.png");

		// ------------------------------------------------------------------------------------------------------------------------------------------

		// GameState
		// ------------------------------------------------------------------------------------------------------------------------

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/mysheet.png"));
		height = width = 30;

		// Wall
		wall = sheet.crop(width * 1, height * 0, width, height);
		// Pacman
		pacRightOne = sheet.crop(width * 0, height * 1, width, height);
		pacRightTwo = sheet.crop(width * 1, height * 1, width, height);
		pacDownOne = sheet.crop(width * 2, height * 1, width, height);
		pacDownTwo = sheet.crop(width * 3, height * 1, width, height);
		pacLeftOne = sheet.crop(width * 4, height * 1, width, height);
		pacLeftTwo = sheet.crop(width * 5, height * 1, width, height);
		pacUpOne = sheet.crop(width * 6, height * 1, width, height);
		pacUpTwo = sheet.crop(width * 7, height * 1, width, height);

		// Ghosts - orange
		orangeGhostRightOne = sheet.crop(width * 0, height * 2, width, height);
		orangeGhostRightTwo = sheet.crop(width * 1, height * 2, width, height);
		orangeGhostDownOne = sheet.crop(width * 2, height * 2, width, height);
		orangeGhostDownTwo = sheet.crop(width * 3, height * 2, width, height);
		orangeGhostLeftOne = sheet.crop(width * 4, height * 2, width, height);
		orangeGhostLeftTwo = sheet.crop(width * 5, height * 2, width, height);
		orangeGhostUpOne = sheet.crop(width * 6, height * 2, width, height);
		orangeGhostUpTwo = sheet.crop(width * 7, height * 2, width, height);
		// Ghosts - red
		redGhostRightOne = sheet.crop(width * 0, height * 3, width, height);
		redGhostRightTwo = sheet.crop(width * 1, height * 3, width, height);
		redGhostDownOne = sheet.crop(width * 2, height * 3, width, height);
		redGhostDownTwo = sheet.crop(width * 3, height * 3, width, height);
		redGhostLeftOne = sheet.crop(width * 4, height * 3, width, height);
		redGhostLeftTwo = sheet.crop(width * 5, height * 3, width, height);
		redGhostUpOne = sheet.crop(width * 6, height * 3, width, height);
		redGhostUpTwo = sheet.crop(width * 7, height * 3, width, height);
		// Ghosts - purple
		purpleGhostRightOne = sheet.crop(width * 0, height * 4, width, height);
		purpleGhostRightTwo = sheet.crop(width * 1, height * 4, width, height);
		purpleGhostDownOne = sheet.crop(width * 2, height * 4, width, height);
		purpleGhostDownTwo = sheet.crop(width * 3, height * 4, width, height);
		purpleGhostLeftOne = sheet.crop(width * 4, height * 4, width, height);
		purpleGhostLeftTwo = sheet.crop(width * 5, height * 4, width, height);
		purpleGhostUpOne = sheet.crop(width * 6, height * 4, width, height);
		purpleGhostUpTwo = sheet.crop(width * 7, height * 4, width, height);
		// Ghosts - green
		greenGhostRightOne = sheet.crop(width * 0, height * 5, width, height);
		greenGhostRightTwo = sheet.crop(width * 1, height * 5, width, height);
		greenGhostDownOne = sheet.crop(width * 2, height * 5, width, height);
		greenGhostDownTwo = sheet.crop(width * 3, height * 5, width, height);
		greenGhostLeftOne = sheet.crop(width * 4, height * 5, width, height);
		greenGhostLeftTwo = sheet.crop(width * 5, height * 5, width, height);
		greenGhostUpOne = sheet.crop(width * 6, height * 5, width, height);
		greenGhostUpTwo = sheet.crop(width * 7, height * 5, width, height);

		// Fruits - Cherry
		cherry = sheet.crop(width * 0, height * 6, width, height);

		// ------------------------------------------------------------------------------------------------------------------------

		// Sounds-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		whilePlay = SoundLoader.getSoundClip("/sounds/whilePlay.wav");
		SoundControl.tuneVolume(whilePlay, -5);
		eatCharry = SoundLoader.getSoundClip("/sounds/eatCharry.wav");
		youFailed = SoundLoader.getSoundClip("/sounds/youFailed.wav");
		youWin = SoundLoader.getSoundClip("/sounds/youWin.wav");

		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	}

	public static Assets getInstance() {
		return assets;
	}

	public BufferedImage getPacManSmile() {
		return pacManSmile;
	}

	public BufferedImage getGameOver() {
		return gameOverBG;
	}

	public BufferedImage getBackGround() {
		return backGround;
	}

	public BufferedImage getSleepingPac() {
		return sleeping;
	}

	public BufferedImage getCherry() {
		return cherry;
	}

	public BufferedImage getWall() {
		return wall;
	}

	public BufferedImage getPacRightOne() {
		return pacRightOne;
	}

	public BufferedImage getPacRightTwo() {
		return pacRightTwo;
	}

	public BufferedImage getPacUpOne() {
		return pacUpOne;
	}

	public BufferedImage getPacUpTwo() {
		return pacUpTwo;
	}

	public BufferedImage getPacDownOne() {
		return pacDownOne;
	}

	public BufferedImage getPacDownTwo() {
		return pacDownTwo;
	}

	public BufferedImage getPacLeftOne() {
		return pacLeftOne;
	}

	public BufferedImage getPacLeftTwo() {
		return pacLeftTwo;
	}

	public BufferedImage getOrangeGhostRightOne() {
		return orangeGhostRightOne;
	}

	public BufferedImage getOrangeGhostRightTwo() {
		return orangeGhostRightTwo;
	}

	public BufferedImage getOrangeGhostUpOne() {
		return orangeGhostUpOne;
	}

	public BufferedImage getOrangeGhostUpTwo() {
		return orangeGhostUpTwo;
	}

	public BufferedImage getOrangeGhostDownOne() {
		return orangeGhostDownOne;
	}

	public BufferedImage getOrangeGhostDownTwo() {
		return orangeGhostDownTwo;
	}

	public BufferedImage getOrangeGhostLeftOne() {
		return orangeGhostLeftOne;
	}

	public BufferedImage getOrangeGhostLeftTwo() {
		return orangeGhostLeftTwo;
	}

	public BufferedImage getRedGhostRightOne() {
		return redGhostRightOne;
	}

	public BufferedImage getRedGhostRightTwo() {
		return redGhostRightTwo;
	}

	public BufferedImage getRedGhostUpOne() {
		return redGhostUpOne;
	}

	public BufferedImage getRedGhostUpTwo() {
		return redGhostUpTwo;
	}

	public BufferedImage getRedGhostDownOne() {
		return redGhostDownOne;
	}

	public BufferedImage getRedGhostDownTwo() {
		return redGhostDownTwo;
	}

	public BufferedImage getRedGhostLeftOne() {
		return redGhostLeftOne;
	}

	public BufferedImage getRedGhostLeftTwo() {
		return redGhostLeftTwo;
	}

	public BufferedImage getPurpleGhostRightOne() {
		return purpleGhostRightOne;
	}

	public BufferedImage getPurpleGhostRightTwo() {
		return purpleGhostRightTwo;
	}

	public BufferedImage getPurpleGhostUpOne() {
		return purpleGhostUpOne;
	}

	public BufferedImage getPurpleGhostUpTwo() {
		return purpleGhostUpTwo;
	}

	public BufferedImage getPurpleGhostDownOne() {
		return purpleGhostDownOne;
	}

	public BufferedImage getPurpleGhostDownTwo() {
		return purpleGhostDownTwo;
	}

	public BufferedImage getPurpleGhostLeftOne() {
		return purpleGhostLeftOne;
	}

	public BufferedImage getPurpleGhostLeftTwo() {
		return purpleGhostLeftTwo;
	}

	public BufferedImage getGreenGhostRightOne() {
		return greenGhostRightOne;
	}

	public BufferedImage getGreenGhostRightTwo() {
		return greenGhostRightTwo;
	}

	public BufferedImage getGreenGhostUpOne() {
		return greenGhostUpOne;
	}

	public BufferedImage getGreenGhostUpTwo() {
		return greenGhostUpTwo;
	}

	public BufferedImage getGreenGhostDownOne() {
		return greenGhostDownOne;
	}

	public BufferedImage getGreenGhostDownTwo() {
		return greenGhostDownTwo;
	}

	public BufferedImage getGreenGhostLeftOne() {
		return greenGhostLeftOne;
	}

	public BufferedImage getGreenGhostLeftTwo() {
		return greenGhostLeftTwo;
	}

	public Clip getWhilePlay() {
		return whilePlay;
	}

	public Clip getEatCharry() {
		return eatCharry;
	}

	public Clip getYouFailed() {
		return youFailed;
	}

	public Clip getYouWin() {
		return youWin;
	}

}
