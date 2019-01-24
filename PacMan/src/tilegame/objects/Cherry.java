package tilegame.objects;

import tilegame.gfx.Assets;

public class Cherry extends Object {

	public Cherry(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getCherry();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
