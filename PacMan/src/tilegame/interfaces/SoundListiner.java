package tilegame.interfaces;

import javax.sound.sampled.Clip;

public interface SoundListiner {

	public void playClip(Clip clip);

	public void oneTime(Clip clip);

	public void stopClip(Clip clip);

}
