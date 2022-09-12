package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

/**
 * Represents a GameObject in the GameWorld
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   1.0
 */
public class GameObject implements IDrawable{
	
	/**
	 * The fields that GameObjects must have
	 */
	private int objSize;
	private Point objLocation;
	private Point2D location;
	private int objColor;
	
	/**
	 * Create a new instance of GameObject with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 * @see   Point of CodeName1 built-in class
	 */
	public GameObject(int size, float locationX, float locationY){
		objSize = size;
		objLocation = new Point (locationX, locationY);
	}
	
	/**
	 * Method that gets this GameObject's size
	 * @return this GameObject's size
	 */
	public int getObjSize(){
		return objSize;
	}
	
	/**
	 * Method that gets this GameObject's location on the x-axis
	 * @return this GameObject's location on the x-axis
	 */
	public float getObjLocationX() {
		return objLocation.getX();
	}
	
	/**
	 * Method that gets this GameObject's location on the y-axis
	 * @return this GameObject's location on the y-axis
	 */
	public float getObjLocationY() {
		return objLocation.getY();
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	/**
	 * Method that sets this GameObject's location
	 * @param x the float to set the GameObject's location on the x-axis
	 * @param y the float to set the GameObject's location on the y-axis
	 */
	public void setObjLocation(float x, float y) {
		objLocation.setX(x);
		objLocation.setY(y);
	}
	
	/**
	 * Method that gets this GameObject's color
	 * @return this GameObject's color
	 */
	public int getColor() {
		return objColor;
	}
	
	/**
	 * Method that sets this GameObjet's color
	 * @param r the integer of red color in rgb color type
	 * @param g the integer of green color in rgb color type
	 * @param b the integer of blue color in rgb color type
	 * @see   ColorUtil of CodeName1 built-in class
	 */
	public void setColor(int r, int g, int b) {
		objColor = ColorUtil.rgb(r, g, b);
	}
	
	/**
	 * Method that converts this GameObject's fields to a string
	 * @return this GameObject's fields as a string
	 */
	public String toString() {
		return "size=" + getObjSize() + " loc=" + Math.round(getObjLocationX()*10.0)/10.0 + "," + Math.round(getObjLocationY()*10.0)/10.0
				+ " color=" + "[" + ColorUtil.red(objColor) + "," + ColorUtil.green(objColor) + "," + ColorUtil.blue(objColor) + "]";
	}

	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt) {
		g.setColor(getColor());
	}
}
