package tilegame.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class SoundLoader {

	public static Clip getSoundClip(String path) {
		AudioInputStream stream;
		try {
			stream = AudioSystem.getAudioInputStream(SoundLoader.class.getResource(path));
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			return clip;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
			return null;
		}
	}

}
