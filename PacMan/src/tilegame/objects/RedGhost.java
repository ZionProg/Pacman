package tilegame.objects;

import tilegame.gfx.Assets;

public class RedGhost extends Ghost {
	public RedGhost(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getRedGhostUpOne();
	}

	@Override
	protected void changeSprite() {
		if (xMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getRedGhostRightOne()
						? Assets.getInstance().getRedGhostRightTwo()
						: Assets.getInstance().getRedGhostRightOne();
				numOfTicks = 0;
			}
		}

		else if (xMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getRedGhostLeftOne()
						? Assets.getInstance().getRedGhostLeftTwo()
						: Assets.getInstance().getRedGhostLeftOne();
				numOfTicks = 0;
			}
		}

		else if (yMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getRedGhostDownOne()
						? Assets.getInstance().getRedGhostDownTwo()
						: Assets.getInstance().getRedGhostDownOne();
				numOfTicks = 0;
			}
		}

		else if (yMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getRedGhostUpOne()
						? Assets.getInstance().getRedGhostUpTwo()
						: Assets.getInstance().getRedGhostUpOne();
				numOfTicks = 0;
			}
		}
	}

}
