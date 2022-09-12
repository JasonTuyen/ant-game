package com.mycompany.a3;

/**
 * Interface for GameWorld and GameWorldProxy to control access
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameWorld and GameWorldProxy
 * @since   2.0
 */
public interface IGameWorld {
	
	/**
	 * Method the gets the amount of lives left before this GameWorld resets
	 */
	public int getLivesLeft();
	
	/**
	 * Method that gets the clock time elapsed in this GameWorld
	 */
	public int getClockValue();
	
	/**
	 * Method that gets the last flag reached by theAnt
	 */
	public int getLastFlag();
	
	/**
	 * Method that gets the food level of theAnt
	 */
	public int getFoodLevel();
	
	/**
	 * Method that gets the health level of theAnt
	 */
	public int getHealthLevel();
	
	/**
	 * Method that gets the sound setting of this GameWorld
	 */
	public boolean getSoundSetting();
	
	/**
	 * Method that gets theCollection Vector of GameObjects in this GameWorld
	 */
	public GameObjectCollection getCollection();

	boolean getGameState();
}
