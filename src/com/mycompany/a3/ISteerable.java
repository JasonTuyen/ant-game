package com.mycompany.a3;

import com.mycompany.a3.Ant;

/**
 * Interface for MovGameObjects to make MovGameObjects "steerable"
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     Ant
 * @since   1.0
 */
public interface ISteerable {
	
	/**
	 * Method that updates this Ant's heading 5 degrees to the left/counter clockwise
	 */
	public void left();
	
	/**
	 * Method that updates this Ant's heading 5 degrees to the right/clockwise
	 */
	public void right();
}
