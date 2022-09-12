package com.mycompany.a3;

import java.util.Vector;

/**
 * Represents the collection of GameObjects in the GameWorld
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     ICollection
 * @since   2.0
 */
public class GameObjectCollection implements ICollection{
	
	/**
	 * The Vector that will hold all GameObjects of the GameWorld
	 */
	private Vector<GameObject> theCollection;
	
	/**
	 * Creates new instance of GameObjectCollection
	 */
	public GameObjectCollection() {
		theCollection = new Vector<GameObject>();
	}
	
	/**
	 * Method that adds a GameObject to theCollection Vector
	 * Method implements and overrides the add method in ICollection
	 * @see ICollection
	 */
	@Override
	public void add(GameObject newObject) {
		theCollection.addElement(newObject);
	}
	
	/**
	 * Method that gets the size of theCollection Vector
	 * @return the size of theCollection Vector
	 */
	public int getSize() {
		return theCollection.size();
	}
	
	/**
	 * Method that clears theCollection Vector
	 */
	public void clearCollection() {
		theCollection.clear();
	}
	
	/**
	 * Method that gets the GameObjectIterator
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	/**
	 * Method that gets the element at a certain index in theCollection Vector
	 * @param i the integer of the certain index in theCollection Vector
	 * @return the GameObject at the parameter's index
	 */
	public GameObject getCollectionAt(int i) {
		return theCollection.get(i);
	}
	
	/**
	 * Represents an Iterator that can traverse
	 * theCollection Vector of GameObjects
	 * @see IIterator
	 */
	private class GameObjectIterator implements IIterator{
		
		/**
		 * The field that tracks the current index of the iterator
		 */
		private int currElementIndex;
		
		/**
		 * Creates a new GameObjectIterator to traverse theCollection Vector
		 */
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		
		/**
		 * Method that checks if theCollection has a GameObject following the current index
		 * Method overrides and implements hasNext method in IIterator
		 * @see IIterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if(theCollection.size() <= 0) {
				return false;
			}
			if(currElementIndex == theCollection.size()-1) {
				return false;
			}
			return true;
		}
		
		/**
		 * Method that gets the next GameObject in theCollection Vector
		 * Method overrides and implements getNext method in IIterator
		 * @see IIterator#getNext()
		 */
		@Override
		public GameObject getNext() {
			currElementIndex++;
			return(theCollection.elementAt(currElementIndex));
		}
		
		@Override
		public void remove(GameObject gameObj) {
			theCollection.remove(gameObj);
			currElementIndex--;
		}
	}
}
