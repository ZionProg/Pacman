package tilegame.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class FileLoader {
	public static BufferedReader loadFile(String path) {
		try {
			InputStream fis = FileLoader.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			return br;
		} catch (Exception e) {
			System.out.println("Failed to load file");
		}
		return null;
	}
}
