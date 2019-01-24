package tilegame.objects;

import java.awt.Rectangle;

import tilegame.Game;

public abstract class Ghost extends Creature {

	private PacMan pacRef;

	public Ghost(float xPos, float yPos) {
		super(xPos, yPos);
		pacRef = null;
		changeSpriteAtTick = 5;
	}

	@Override
	protected void direction() {
		float pacXPos, pacYpos;
		int xDistance, yDistance;

		xMove = 0; // this is necessary
		yMove = 0;
		wallCollision = false;

		if (pacRef.getPacNotMoving()) {
			return;
		}

		pacXPos = pacRef.getxPosition();
		pacYpos = pacRef.getyPosition();

		// int distance = Math.abs(x1-x0) + Math.abs(y1-y0);

		xDistance = (int) Math.abs(xPosition - pacXPos);
		yDistance = (int) Math.abs(yPosition - pacYpos);

		if (xDistance < yDistance) {
			if (xPosition < pacXPos) {
				collisionHandler(xPosition + speed, yPosition + yMove);
				if (!wallCollision) {
					xMove = speed;
					return;
				}

			} else if (xPosition > pacXPos) {
				collisionHandler(xPosition - speed, yPosition + yMove);
				if (!wallCollision) {
					xMove = -speed;
					return;
				}
			}

			// if got here then there was a collision and ghost will wanna move verticaly

			wallCollision = false;

			if (yPosition < pacYpos) {
				collisionHandler(xPosition + xMove, yPosition + speed);
				if (!wallCollision) {
					yMove = speed;
				}

			} else if (yPosition > pacYpos) {
				collisionHandler(xPosition + xMove, yPosition - speed);
				if (!wallCollision) {
					yMove = -speed;
				}
			}
		}

		else if (yDistance < xDistance) {
			if (yPosition < pacYpos) {
				collisionHandler(xPosition + xMove, yPosition + speed);
				if (!wallCollision) {
					yMove = speed;
					return;
				}

			} else if (yPosition > pacYpos) {
				collisionHandler(xPosition + xMove, yPosition - speed);
				if (!wallCollision) {
					yMove = -speed;
					return;
				}
			}

			// if got here there was a collision and ghost will wanna move horizontally
			wallCollision = false;

			if (xPosition < pacXPos) {
				collisionHandler(xPosition + speed, yPosition + yMove);
				if (!wallCollision) {
					xMove = speed;
					return;
				}
			} else if (xPosition > pacXPos) {
				collisionHandler(xPosition - speed, yPosition + yMove);
				if (!wallCollision) {
					xMove = -speed;
					return;
				}
			}
		}
	}

	@Override
	protected void collisionHandler(float xPos, float yPos) {
		Rectangle ghost = new Rectangle((int) xPos, (int) yPos, objectsWidth, objectsHeight); // pacMan
		Rectangle other = new Rectangle(); // cookie || ghost || wall

		// Wall Collision
		for (int i = 0; i < map.getWallLocation().size(); ++i) {
			int x = map.getWallLocation().get(i).width;
			int y = map.getWallLocation().get(i).height;
			other.setBounds(x * objectsWidth, y * objectsHeight, objectsWidth, objectsHeight);
			if (ghost.intersects(other)) {
				wallCollision = true;
				return;
			}
		}

		for (int i = 0; i < map.getGhostsRef().size(); ++i) {
			int x = (int) map.getGhostsRef().get(i).getxPosition();
			int y = (int) map.getGhostsRef().get(i).getyPosition();
			other.setBounds(x, y, objectsWidth, objectsHeight);
			if (ghost.intersects(other)) {
				if (!map.getGhostsRef().get(i).equals(this)) {
					wallCollision = true;
				}
			}
		}
	}

	@Override
	protected void outOfBounds() {
		if (xPosition >= Game.frameWidth) {
			xPosition = 0 - 30;
		}

		else if (xPosition + 30 <= 0) {
			xPosition = Game.frameWidth;
		}
	}

	public void setPacRef(PacMan pac) {
		pacRef = pac;
	}
}
