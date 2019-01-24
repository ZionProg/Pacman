package tilegame.objects;

import tilegame.gfx.Assets;

public class Wall extends Object {

	public Wall(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getWall();
	}

	@Override
	public void tick() {
		// wall won't update
	}
}
