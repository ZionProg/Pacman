package tilegame.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ImageLoader {
	public static BufferedImage loadImage(String path) {

		try {
			return ImageIO.read(ImageLoader.class.getResource(path)); // path will be (/texture/test.png)
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
