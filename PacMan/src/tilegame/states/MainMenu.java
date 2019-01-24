package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;

public class MainMenu extends State {

	private boolean hidden;

	public MainMenu() {
		super();
		soundListiner = null;
	}

	@Override
	public void tick() {
		if (Game.getNumOfTicks() > 35) {
			hidden = !hidden;
			Game.resetNumOfTicks();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.getInstance().getBackGround(), 0, 0, null);
		g.setColor(Color.YELLOW);
		g.setFont(pacManFont);
		g.drawString("Pac-Man!", (frameWidth / 3) - 20, frameHeight / 3);
		if (!hidden)
			g.drawString("Press enter to start!", (frameWidth / 9), frameHeight / 3 + (60 * 2));
	}

}
