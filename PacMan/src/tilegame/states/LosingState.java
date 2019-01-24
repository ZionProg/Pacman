package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;
import tilegame.interfaces.SoundListiner;

public class LosingState extends State {
	private String youLose;
	private boolean now;

	public LosingState(SoundListiner sListiner) {
		super();
		soundListiner = sListiner;
		youLose = "You Lose";
	}

	@Override
	public void tick() {
		if (!now)
			if (Game.getNumOfTicks() % 40 == 0) {
				youLose += ".";
			}

		if (youLose.equals("You Lose..."))
			now = true;
	}

	@Override
	public void render(Graphics g) {
		soundListiner.oneTime(Assets.getInstance().getYouFailed());
		g.drawImage(Assets.getInstance().getGameOver(), 0 + 70, 0, null);
		g.setFont(pacManFont);
		g.setColor(Color.RED);
		g.drawString(youLose, frameWidth / 3 - 30, frameHeight / 2 + 150);
		if (now)
			g.drawString("Try again :D", frameWidth / 3 - 30, frameHeight / 2 + 250);
	}
}
