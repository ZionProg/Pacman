package tilegame.objects;

import java.awt.Dimension;

import tilegame.map.Map;

public abstract class Creature extends Object {

	protected final float DEFUALT_SPEED = 2.0f;
	protected final Dimension DEFUALT_POSTION;
	protected Map map;
	protected float speed;
	protected float xMove, yMove;
	protected int numOfTicks;
	protected boolean wallCollision;
	protected int changeSpriteAtTick;

	public Creature(float xPos, float yPos) {
		super(xPos, yPos);
		speed = DEFUALT_SPEED;
		numOfTicks = 0;
		DEFUALT_POSTION = new Dimension((int) xPos, (int) yPos);
		wallCollision = false;
	}

	protected abstract void direction();

	protected abstract void collisionHandler(float xPos, float yPos);

	protected abstract void changeSprite();

	@Override
	public void tick() {
		++numOfTicks;
		direction();
		changeSprite();
		move();
	}

	public void move() {
		xPosition += xMove;
		yPosition += yMove;
	}

	public void resetPosition() {
		xPosition = DEFUALT_POSTION.width;
		yPosition = DEFUALT_POSTION.height;
	}

	public void setMapRef(Map map) {
		this.map = map;
	}

	protected abstract void outOfBounds();
}
