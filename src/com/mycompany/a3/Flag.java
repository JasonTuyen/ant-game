package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.FixGameObject;
import com.mycompany.a3.GameObject;

/**
 * Represents a Flag in the GameWorld
 * GameWorld can have 1-9 flags
 * Flag extends FixGameObject
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     FixGameObject
 * @since   1.0
 */
public class Flag extends FixGameObject implements ISelectable, ICollider{
	
	/**
	 * The fields that Flag must have on top of FixGameObject fields
	 */
	private int seqNumber = 0;
	private boolean selected;
	private boolean collisionFlag = false;
	
	/**
	 * Create a new instance of Flag with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 */
	public Flag(int size, float locationX, float locationY, int seq) {
		super(size, locationX, locationY);
		super.setColor(0, 0, 255);
		seqNumber = seq;
	}
	
	/**
	 * Method that overrides GameObject's setColor method
	 * Method is empty because Flag cannot update color after creation
	 * @see GameObject#setColor(int, int, int)
	 */
	public void setColor() {
		//Method is empty because Flag cannot update its color after creation
	}
	
	public int getSeqNum() {
		return seqNumber;
	}
	
	/**
	 * Method that converts this Flag's fields to a string
	 * @return this Flag's fields as a string
	 */
	@Override
	public String toString() {
		String myClass = "Flag: ";
		String parentDesc = super.toString();
		String myDesc = " seqNumber=" + seqNumber;
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
			int[] xPoints = {xDraw, xDraw-getObjSize(), xDraw+getObjSize()};
			int[] yPoints = {yDraw+getObjSize(), yDraw-getObjSize(), yDraw-getObjSize()};
			int nPoints = 3;
			g.drawPolygon(xPoints, yPoints, nPoints);
		}else {
			super.draw(g, pCmpRelPrnt);
			int xDraw = (int) (getObjLocationX() + pCmpRelPrnt.getX() - getObjSize()/2);
			int yDraw = (int) (getObjLocationY() + pCmpRelPrnt.getY()- getObjSize()/2);
			int[] xPoints = {xDraw, xDraw-getObjSize(), xDraw+getObjSize()};
			int[] yPoints = {yDraw+getObjSize(), yDraw-getObjSize(), yDraw-getObjSize()};
			int nPoints = 3;
			g.drawPolygon(xPoints, yPoints, nPoints);
			g.fillPolygon(xPoints, yPoints, nPoints);
			String flagNumString = "" + getSeqNum();
			g.setColor(0);
			g.drawString(flagNumString, xDraw, yDraw);
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
