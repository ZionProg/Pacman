package tilegame.objects;

import tilegame.gfx.Assets;

public class OrangeGhost extends Ghost {
	public OrangeGhost(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getOrangeGhostUpOne();
	}

	@Override
	protected void changeSprite() {
		if (xMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getOrangeGhostRightOne()
						? Assets.getInstance().getOrangeGhostRightTwo()
						: Assets.getInstance().getOrangeGhostRightOne();
				numOfTicks = 0;
			}
		}

		else if (xMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getOrangeGhostLeftOne()
						? Assets.getInstance().getOrangeGhostLeftTwo()
						: Assets.getInstance().getOrangeGhostLeftOne();
				numOfTicks = 0;
			}
		}

		else if (yMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getOrangeGhostDownOne()
						? Assets.getInstance().getOrangeGhostDownTwo()
						: Assets.getInstance().getOrangeGhostDownOne();
				numOfTicks = 0;
			}
		}

		else if (yMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getOrangeGhostUpOne()
						? Assets.getInstance().getOrangeGhostUpTwo()
						: Assets.getInstance().getOrangeGhostUpOne();
				numOfTicks = 0;
			}
		}
	}
}
