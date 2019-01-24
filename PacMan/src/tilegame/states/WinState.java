package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.gfx.Assets;
import tilegame.interfaces.SoundListiner;

public class WinState extends State {

	public WinState(SoundListiner sListiner) {
		super();
		soundListiner = sListiner;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		soundListiner.oneTime(Assets.getInstance().getYouWin());
		g.drawImage(Assets.getInstance().getPacManSmile(), frameWidth / 3, frameHeight / 2, null);
		g.setFont(pacManFont);
		g.setColor(Color.YELLOW);
		g.drawString("Congratulations", (frameWidth / 4) - 50, frameHeight / 4);
		g.drawString("You Win!", (frameWidth / 3) - 10, (frameHeight / 3) + 30);
	}

}
