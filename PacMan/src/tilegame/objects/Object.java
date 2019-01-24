package tilegame.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tilegame.interfaces.TickAndRender;

public abstract class Object implements TickAndRender {

	protected final int objectsWidth;
	protected final int objectsHeight;
	protected float xPosition, yPosition;
	protected BufferedImage spriteFacing;

	public Object(float xPos, float yPos) {
		xPosition = xPos;
		yPosition = yPos;
		objectsWidth = 30; // in my game the size of all objects will be 30x30
		objectsHeight = 30;
	}

	@Override
	public abstract void tick();

	public void render(Graphics g) {
		g.drawImage(spriteFacing, (int) xPosition, (int) yPosition, objectsWidth, objectsHeight, null);
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public int getWidth() {
		return objectsWidth;
	}

	public int getHeight() {
		return objectsHeight;
	}
}
