package tilegame.util;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class SoundControl {

	public static void tuneVolume(Clip clip, float volume) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
	}
}
