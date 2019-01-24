package tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sh) {
		sheet = sh;
	}

	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height); // this will return a NEW bufferedImage
	}

	public void setSheet(BufferedImage sh) {
		sheet = sh;
	}
}
