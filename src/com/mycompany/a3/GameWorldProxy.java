package com.mycompany.a3;

import java.util.Observable;

import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IGameWorld;

/**
 * Represents a protection proxy that controls access to the actual GameWorld
 * GameWorldProxy extends Observable and implements IGameWorld
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     Observable and IGameWorld
 * @since   2.0
 */
public class GameWorldProxy extends Observable implements IGameWorld{
	
	/**
	 * A proxy of the GameWorld
	 */
	private GameWorld proxy;
	
	/**
	 * Creates an instance of GameWorldProxy that acts as a proxy
	 * @param gw the GameWorld that our proxy will be controlling access of
	 */
	public GameWorldProxy(GameWorld gw) {
		proxy = gw;
	}
	
	/**
	 * Method that gets the livesLeft from the GameWorld
	 * @see GameWorld#getLivesLeft()
	 */
	@Override
	public int getLivesLeft() {
		return proxy.getLivesLeft();
	}
	
	/**
	 * Method that gets the clock values from the GameWorld
	 * @see GameWorld#getClockValue()
	 */
	@Override
	public int getClockValue() {
		return proxy.getClockValue();
	}
	
	/**
	 * Method that gets the last flag reached from the GameWorld
	 * @see GameWorld#getLastFlag()
	 */
	@Override
	public int getLastFlag() {
		return proxy.getLastFlag();
	}
	
	/**
	 * Method that gets the food level of theAnt from the GameWorld
	 * @see GameWorld#getFoodLevel()
	 */
	@Override
	public int getFoodLevel() {
		return proxy.getFoodLevel();
	}
	
	/**
	 * Method that gets the health level of theAnt from the GameWorld
	 * @see GameWorld#getHealthLevel()
	 */
	@Override
	public int getHealthLevel() {
		return proxy.getHealthLevel();
	}
	
	/**
	 * Method that gets the sound setting from the GameWorld
	 * @see GameWorld#getSoundSetting()
	 */
	@Override
	public boolean getSoundSetting() {
		return proxy.getSoundSetting();
	}
	
	/**
	 * Method that gets theColection Vector from the GameWorld
	 * @see GameObjectCollection#getCollection()
	 */
	@Override
	public GameObjectCollection getCollection() {
		return proxy.getCollection();
	}
	
	@Override
	public boolean getGameState() {
		return proxy.getGameState();
	}
}
