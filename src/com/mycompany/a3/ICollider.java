package com.mycompany.a3;

/**
 * Interface for GameObject for methods that deal with object collision
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObject
 * @since   3.0
 */
public interface ICollider {
	
	/**
	 * Method that tracks if 2 game objects have collided
	 */
	public boolean collidesWith(ICollider other);
	
	/**
	 * Method that determines what to do after a collision occurs
	 */
	public void handleCollision(ICollider other);
	
	public void setCollisionFlag();
	
	public boolean getCollisionFlag();
}
