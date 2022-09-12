package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.MovGameObject;

/**
 * Represents a spider in the GameWorld
 * Spider extends MovGameObject
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     MovGameObject
 * @since   1.0
 */
public class Spider extends MovGameObject implements ICollider{
	private boolean collisionFlag;
	
	/**
	 * Creates a new instance of spider with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 * @param heading
	 * @param speed
	 */
	public Spider(int size, float locationX, float locationY, int heading, int speed) {
		super(size, locationX, locationY, heading, speed);
		super.setColor(0, 0, 0);
	}
	
	/**
	 * Method that updates this Spiders' location
	 *
	public void move(double height, double width, int elapsedTime) {
		super.move(height, width, elapsedTime);
	}
	 *
	 */
	
	/**
	 * Method that overrides GameObject's setColor method
	 * Method is empty because Spider cannot update color after creation
	 * @see GameObject#setColor(int, int, int)
	 */
	public void setColor() {
		//Method is empty because Spider cannot update its color after creation
	}
	
	/**
	 * Method that converts this Spiders' fields to a string
	 * @return this Spiders' fields as a string
	 */
	public String toString() {
		String myClass = "Spider: ";
		String parentDesc = super.toString();
		return myClass + parentDesc;
	}
	
	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt){
		super.draw(g, pCmpRelPrnt);
		int xDraw = (int) (getObjLocationX() + pCmpRelPrnt.getX() - getObjSize()/2);
		int yDraw = (int) (getObjLocationY() + pCmpRelPrnt.getY()- getObjSize()/2);
		int[] xPoints = {xDraw, xDraw-getObjSize(), xDraw+getObjSize()};
		int[] yPoints = {yDraw+getObjSize(), yDraw-getObjSize(), yDraw-getObjSize()};
		int nPoints = 3;
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public boolean collidesWith(ICollider other) {
		boolean result = false;
		double thisCenterX = this.getLocation().getX();
		double thisCenterY = this.getLocation().getY();
		
		double otherCenterX = ((GameObject)other).getLocation().getX();
		double otherCenterY = ((GameObject)other).getLocation().getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCenters = (dx * dx + dy * dy);
		
		int thisRadius = this.getObjSize()/2;
		int otherRadius = ((GameObject)other).getObjSize()/2;
		
		int radiusSq = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if(distBetweenCenters <= radiusSq) {
			result = true;
		}
		return result;
	}

	@Override
	public void handleCollision(ICollider other) {
		setCollisionFlag();
		other.setCollisionFlag();
	}

	@Override
	public void setCollisionFlag() {
		collisionFlag = true;
	}

	@Override
	public boolean getCollisionFlag() {
		return collisionFlag;
	}
}
