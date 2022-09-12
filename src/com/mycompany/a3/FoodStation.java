package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.FixGameObject;

/**
 * Represents an Ant in the GameWorld
 * Only one ant can exist in GameWorld
 * Ant extends MovGameObject and implements ISteerable
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     FixGameObject
 * @since   1.0
 */
public class FoodStation extends FixGameObject implements ISelectable, ICollider{
	
	/**
	 * The fields that FoodStation must have on top of FixGameObject fields
	 */
	private int foodCapacity;
	private boolean selected;
	private boolean collisionFlag;

	/**
	 * Creates a new instance of FoodStation with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 * @param capacity
	 */
	public FoodStation(int size, float locationX, float locationY, int capacity) {
		super(size, locationX, locationY);
		super.setColor(0, 255, 0);
		foodCapacity = capacity*size;
	}
	
	/**
	 * Method that gets this FoodStation's food capacity
	 * @return this FoodStation's food capacity
	 */
	public int getCapacity() {
		return foodCapacity;
	}
	
	/**
	 * Method that sets this FoodStation's food capacity
	 * @param c the integer to set this FoodStation's food capacity
	 */
	public void setCapacity(int c) {
		foodCapacity = c;
	}
	
	/**
	 * Method that converts this FoodStation's fields to a string
	 * @return this FoodStation's fields as a string
	 */
	@Override
	public String toString() {
		String myClass = "FoodStation: ";
		String parentDesc = super.toString();
		String myDesc = " capacity=" + getCapacity();
		return myClass + parentDesc + myDesc;
	}
	
	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt){
		if(isSelected()) {
			super.draw(g, pCmpRelPrnt);
			int xDraw = (int) (getObjLocationX() + pCmpRelPrnt.getX() - getObjSize()/2);
			int yDraw = (int) (getObjLocationY() + pCmpRelPrnt.getY()- getObjSize()/2);
			g.drawRect(xDraw, yDraw, getObjSize(), getObjSize());
		}else {
			super.draw(g, pCmpRelPrnt);
			int xDraw = (int) (getObjLocationX() + pCmpRelPrnt.getX() - getObjSize()/2);
			int yDraw = (int) (getObjLocationY() + pCmpRelPrnt.getY()- getObjSize()/2);
			g.drawRect(xDraw, yDraw, getObjSize(), getObjSize());
			g.fillRect(xDraw, yDraw, getObjSize(), getObjSize());
			String foodCapacityString = "" + getCapacity();
			g.setColor(0);
			g.drawString(foodCapacityString, xDraw, yDraw);
		}
	}

	@Override
	public void setSelected(boolean y) {
		selected = y;
		
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		
		int xLoc = (int) this.getObjLocationX() + pCmpRelPrnt.getX();
		int yLoc = (int) this.getObjLocationY() + pCmpRelPrnt.getY();
		
		if( ((px >= xLoc - getObjSize()/2) && (px <= xLoc + getObjSize()/2)) && ((py >= yLoc - getObjSize()/2 ) && (py <= yLoc + getObjSize()/2))) {
			return true;
		}
		return false;
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
