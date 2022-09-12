package com.mycompany.a3;

import com.codename1.ui.Graphics;

/**
 * Represents an abstract movable game object in the GameWorld
 * MovGameObject extends GameObject
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObject
 * @since   1.0
 */
public abstract class MovGameObject extends GameObject{
	
	/**
	 * The fields that MovGameObject must have on top of GameObject fields
	 */
	private int objHeading;
	private int objSpeed;
	
	/**
	 * Creates a new instance of MovGameObject with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 * @param heading
	 * @param speed
	 */
	public MovGameObject(int size, float locationX, float locationY, int heading, int speed) {
		super(size, locationX, locationY);
		objHeading = heading;
		objSpeed = speed;
	}
	
	/**
	 * Method that gets the heading of this MovGameObject
	 * @return the heading of this MovGameObject
	 */
	public int getObjHeading() {
		return objHeading;
	}
	
	/**
	 * Method that sets the heading of this MovGameObject
	 * Method makes sure that this MovGameObject's heading is between 0 and 360 degrees
	 * @param h the integer to set the heading of this MovGameObject
	 */
	public void setObjHeading(int h) {
		objHeading = h;
		if(objHeading > 360) {
			objHeading = objHeading - 360;
		}
		if(objHeading < 0) {
			objHeading = 360 + objHeading;
		}
	}
	
	/**
	 * Method that gets this MovGameObject's speed
	 * @return this MovGameObject's speed
	 */
	public int getObjSpeed() {
		return objSpeed;
	}
	
	/**
	 * Method the sets this MovGameObject's speed
	 * @param s the integer to set the speed of this MovGameObject
	 */
	public void setObjSpeed(int s) {
		objSpeed = s;
	}
	
	/**
	 * Method that updates this MovGameObject's location based on
	 * this MovGameObject's speed and heading,
	 * if this MovGameObject is out of bounds the heading is adjusted
	 */
	public void move(double height, double width, int elapsedTime) {
		float x = super.getObjLocationX();
		float y = super.getObjLocationY();
		double deltaX = Math.cos(Math.toRadians(90-getObjHeading())) * getObjSpeed() * elapsedTime/1000;
		double deltaY = Math.sin(Math.toRadians(90-getObjHeading())) * getObjSpeed() * elapsedTime/1000;
		x = (float) (x + deltaX);
		y = (float) (y + deltaY);
		super.setObjLocation(x,y);
		if(x > width) {
			setObjHeading(270);
		}else if(x < 0){
			setObjHeading(90);
		}
		if(y > height) {
			setObjHeading(180);
		}else if(y < 0){
			setObjHeading(0);
		}
	}
	
	/**
	 * Method that converts this MovGameObject's fields to a string
	 * @return this MovGameObject's fields as a string
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " heading=" + getObjHeading() + " speed=" + getObjSpeed();
		return parentDesc + myDesc;
	}
	
	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt){
		super.draw(g, pCmpRelPrnt);
	}
}
