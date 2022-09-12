package com.mycompany.a3;

import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.IIterator;

/**
 * Interface for GameObjectCollection to interact with collection of GameObjects
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObjectCollection
 * @since   2.0
 */
public interface ICollection {
	
	/**
	 * Method that gets the GameObjectIterator
	 */
	public IIterator getIterator();
	
	/**
	 * Method that adds a GameObject to collection
	 */
	public void add(GameObject newObject);
}
