package com.mycompany.a3;

import com.codename1.ui.Graphics;

/**
 * Represents an abstract fixed game object in the GameWorld
 * A fixed game object cannot move therefore it cannot update it's location
 * FixGameObject extends GameObject
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObject
 * @since   1.0
 */
public abstract class FixGameObject extends GameObject implements ISelectable{
	
	/**
	 * Creates a new instance of FixGameObject with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 */
	public FixGameObject(int size, float locationX, float locationY){
		super(size, locationX, locationY);
	}
	
	/**
	 * Method that overrides GameObject's setObjectLocation method
	 * Method is empty because FixGameObject cannot update location
	 * @see GameObject#setObjLocation(float, float)
	 */
	@Override
	public void setObjLocation(float x, float y) {
		super.setObjLocation(x, y);
	}
	
	/**
	 * Method that converts this FixGameObject's fields to a string
	 * @return this FixGameObject's fields as a string
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt){
		super.draw(g, pCmpRelPrnt);
	}
}
