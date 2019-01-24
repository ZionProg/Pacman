package tilegame.objects;

import tilegame.gfx.Assets;

public class GreenGhost extends Ghost {

	public GreenGhost(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getGreenGhostDownOne();
	}

	@Override
	protected void changeSprite() {
		if (xMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getGreenGhostRightOne()
						? Assets.getInstance().getGreenGhostRightTwo()
						: Assets.getInstance().getGreenGhostRightOne();
				numOfTicks = 0;
			}
		}

		else if (xMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getGreenGhostLeftOne()
						? Assets.getInstance().getGreenGhostLeftTwo()
						: Assets.getInstance().getGreenGhostLeftOne();
				numOfTicks = 0;
			}
		}

		else if (yMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getGreenGhostDownOne()
						? Assets.getInstance().getGreenGhostDownTwo()
						: Assets.getInstance().getGreenGhostDownOne();
				numOfTicks = 0;
			}
		}

		else if (yMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getGreenGhostUpOne()
						? Assets.getInstance().getGreenGhostUpTwo()
						: Assets.getInstance().getGreenGhostUpOne();
				numOfTicks = 0;
			}
		}
	}

}
