package com.mycompany.a3;

/**
 * Interface for GameObjectIterator in GameObjectCollection to iterate through collection
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObjectIterator in GameObjectCollection
 * @since   2.0
 */
public interface IIterator {
	
	/**
	 * Method that checks if GameObject exists following the current index
	 */
	public boolean hasNext();
	
	/**
	 * Method that gets the next GameObject
	 */
	public GameObject getNext();

	public void remove(GameObject obj);
}
