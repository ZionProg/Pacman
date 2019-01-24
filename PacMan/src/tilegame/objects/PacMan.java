package tilegame.objects;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import tilegame.Game;
import tilegame.gfx.Assets;
import tilegame.interfaces.GameOverListiner;
import tilegame.interfaces.SoundListiner;
import tilegame.keymanager.KeyManager;
import tilegame.map.Map;

public class PacMan extends Creature {

	private KeyManager keyListener;
	private GameOverListiner gameOverListiner;
	private SoundListiner soundListiner;
	private final int DEFUALT_HEALTH = 3;
	private static int health;
	private boolean pacNotMoving;
	private int before, now;

	public PacMan(float xPos, float yPos, KeyManager listener, GameOverListiner gOverListiner, SoundListiner sListiner,
			Map refMap) {
		super(xPos, yPos);
		spriteFacing = Assets.getInstance().getPacRightOne(); // starts facing right by default
		keyListener = listener;
		gameOverListiner = gOverListiner;
		soundListiner = sListiner;
		map = refMap;
		health = DEFUALT_HEALTH;
		speed = DEFUALT_SPEED + 1;
		now = before = KeyEvent.VK_SPACE; // Default
		changeSpriteAtTick = 4;
		pacNotMoving = false;
	}

	protected void collisionHandler(float xPos, float yPos) {
		Rectangle pacMan = new Rectangle((int) xPos, (int) yPos, objectsWidth, objectsHeight); // pacMan
		Rectangle other = new Rectangle(); // cookie || ghost || wall

		// Wall Collision
		for (int i = 0; i < map.getWallLocation().size(); ++i) {
			int x = map.getWallLocation().get(i).width;
			int y = map.getWallLocation().get(i).height;
			other.setBounds(x * objectsWidth, y * objectsHeight, objectsWidth, objectsHeight);
			if (pacMan.intersects(other)) {
				wallCollision = true;
				return;
			}
		}

		// Charry Collision
		for (int i = 0; i < map.getEatableObjectsLocation().size(); ++i) {
			if (map.getEatableObjectsLocation().get(i) != null) {
				int x = map.getEatableObjectsLocation().get(i).width;
				int y = map.getEatableObjectsLocation().get(i).height;
				other.setBounds(x * objectsWidth, y * objectsHeight, objectsWidth, objectsHeight);
				if (pacMan.intersects(other)) {
					soundListiner.playClip(Assets.getInstance().getEatCharry());
					map.deleteObject(y, x);
					map.getEatableObjectsLocation().set(i, null);
					map.decNumOfEatableObjects();
					return;
				}
			}
		}

		// Ghosts collision
		for (int i = 0; i < map.getGhostsRef().size(); ++i) {
			int x = (int) map.getGhostsRef().get(i).getxPosition();
			int y = (int) map.getGhostsRef().get(i).getyPosition();
			other.setBounds(x, y, objectsWidth, objectsHeight);
			if (pacMan.intersects(other)) {
				System.out.println("Collision!!");
				--health;

				if (health == 0) {
					notifyGameOver();
					break;
				}

				map.resetMap();
				// Because map will be reset and all objects will be in there starting position
				// there is no need to return true here
			}
		}
	}

	protected void direction() {
		xMove = 0; // need to reset move before applying a new move
		yMove = 0;

		if (pacNotMoving)
			return;

		outOfBounds(); // teleportal handler

		if (keyListener.keyIsUp(now)) {
			collisionHandler(xPosition + xMove, yPosition - speed);
			if (wallCollision) {
				checkAgainWithBeforeMove();
				yMove = 0;
			} else
				yMove = -speed;
			if (numOfTicks > changeSpriteAtTick && yMove != 0) // i need move != 0 so it won't change the sprite if
																// pacman is not moving
				spriteFacing = spriteFacing == Assets.getInstance().getPacUpOne() ? Assets.getInstance().getPacUpTwo()
						: Assets.getInstance().getPacUpOne();
		}

		else if (keyListener.keyIsDown(now)) {
			collisionHandler(xPosition + xMove, yPosition + speed);
			if (wallCollision) {
				checkAgainWithBeforeMove();
				yMove = 0;
			} else
				yMove = speed;
			if (numOfTicks > changeSpriteAtTick && yMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacDownOne()
						? Assets.getInstance().getPacDownTwo()
						: Assets.getInstance().getPacDownOne();
		}

		else if (keyListener.keyIsLeft(now)) {
			collisionHandler(xPosition - speed, yPosition + yMove);
			if (wallCollision) {
				checkAgainWithBeforeMove();
				xMove = 0;
			} else
				xMove = -speed;
			if (numOfTicks > changeSpriteAtTick && xMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacLeftOne()
						? Assets.getInstance().getPacLeftTwo()
						: Assets.getInstance().getPacLeftOne();
		}

		else if (keyListener.keyIsRight(now)) {

			collisionHandler(xPosition + speed, yPosition + yMove);
			if (wallCollision) {
				checkAgainWithBeforeMove();
				xMove = 0;
			} else
				xMove = speed;
			if (numOfTicks > changeSpriteAtTick && xMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacRightOne()
						? Assets.getInstance().getPacRightTwo()
						: Assets.getInstance().getPacRightOne();
		}

		numOfTicks = numOfTicks > changeSpriteAtTick ? 0 : numOfTicks;
		wallCollision = false;
	}

	private void checkAgainWithBeforeMove() {
		wallCollision = false;

		if (keyListener.keyIsSpace(before))
			return;

		if (keyListener.keyIsUp(before)) {
			collisionHandler(xPosition + xMove, yPosition - speed);
			yMove = wallCollision ? 0 : -speed;
			if (numOfTicks > changeSpriteAtTick && yMove != 0) // i need move != 0 so it won't change the sprite if
																// pacman is not moving
				spriteFacing = spriteFacing == Assets.getInstance().getPacUpOne() ? Assets.getInstance().getPacUpTwo()
						: Assets.getInstance().getPacUpOne();
		}

		else if (keyListener.keyIsDown(before)) {
			collisionHandler(xPosition + xMove, yPosition + speed);
			yMove = wallCollision ? 0 : speed;
			if (numOfTicks > changeSpriteAtTick && yMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacDownOne()
						? Assets.getInstance().getPacDownTwo()
						: Assets.getInstance().getPacDownOne();
		}

		else if (keyListener.keyIsLeft(before)) {
			collisionHandler(xPosition - speed, yPosition + yMove);
			xMove = wallCollision ? 0 : -speed;
			if (numOfTicks > changeSpriteAtTick && xMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacLeftOne()
						? Assets.getInstance().getPacLeftTwo()
						: Assets.getInstance().getPacLeftOne();
		}

		else if (keyListener.keyIsRight(before)) {
			collisionHandler(xPosition + speed, yPosition + yMove);
			xMove = wallCollision ? 0 : speed;
			if (numOfTicks > changeSpriteAtTick && xMove != 0)
				spriteFacing = spriteFacing == Assets.getInstance().getPacRightOne()
						? Assets.getInstance().getPacRightTwo()
						: Assets.getInstance().getPacRightOne();
		}
	}

	@Override
	protected void outOfBounds() {
		if (xPosition >= Game.frameWidth) {
			xPosition = 0 - 30;
			now = KeyEvent.VK_RIGHT;
			before = now;
		}

		else if (xPosition + 30 <= 0) {
			xPosition = Game.frameWidth;
			now = KeyEvent.VK_LEFT;
			before = now;
		}
	}

	private void notifyGameOver() {
		gameOverListiner.gameOver();
	}

	public static int getHealth() {
		return health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setNowKey(int now) {
		this.now = now;
	}

	public void setBeforeKey(int before) {
		this.before = before;
	}

	public int getNowKey() {
		return now;
	}

	public int getBeforeKey() {
		return before;
	}

	public boolean getPacNotMoving() {
		return pacNotMoving;
	}

	public void setPacNotMoving(boolean pacNotMoving) {
		this.pacNotMoving = pacNotMoving;
	}

	@Override
	protected void changeSprite() {
		// TODO Auto-generated method stub
	}
}
