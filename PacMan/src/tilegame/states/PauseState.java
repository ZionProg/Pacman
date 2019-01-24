package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;
import tilegame.interfaces.SoundListiner;

public class PauseState extends State {

	private String sleeping;

	public PauseState(SoundListiner sListiner) {
		super();
		soundListiner = sListiner;
		sleeping = "Sleeping";
	}

	@Override
	public void tick() {
		if (Game.getNumOfTicks() % 30 == 0) {
			sleeping += ".";
		}

		if (Game.getNumOfTicks() % 120 == 0) {
			sleeping = "Sleeping";
			Game.resetNumOfTicks();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.getInstance().getSleepingPac(), frameWidth / 3, frameHeight / 2, null);
		g.setFont(pacManFont);
		g.setColor(Color.YELLOW);
		g.drawString(sleeping, (frameWidth / 4) + 40, frameHeight / 3 + 50);
	}
}
