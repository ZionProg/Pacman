package tilegame.objects;

import tilegame.gfx.Assets;

public class PurpleGhost extends Ghost {

	public PurpleGhost(float xPos, float yPos) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getPurpleGhostDownOne();
	}

	@Override
	protected void changeSprite() {
		if (xMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getPurpleGhostRightOne()
						? Assets.getInstance().getPurpleGhostRightTwo()
						: Assets.getInstance().getPurpleGhostRightOne();
				numOfTicks = 0;
			}
		}

		else if (xMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getPurpleGhostLeftOne()
						? Assets.getInstance().getPurpleGhostLeftTwo()
						: Assets.getInstance().getPurpleGhostLeftOne();
				numOfTicks = 0;
			}
		}

		else if (yMove > 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getPurpleGhostDownOne()
						? Assets.getInstance().getPurpleGhostDownTwo()
						: Assets.getInstance().getPurpleGhostDownOne();
				numOfTicks = 0;
			}
		}

		else if (yMove < 0) {
			if (numOfTicks > changeSpriteAtTick) {
				spriteFacing = spriteFacing == Assets.getInstance().getPurpleGhostUpOne()
						? Assets.getInstance().getPurpleGhostUpTwo()
						: Assets.getInstance().getPurpleGhostUpOne();
				numOfTicks = 0;
			}
		}
	}

}
