package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Ant;
import com.mycompany.a3.ISteerable;
import com.mycompany.a3.MovGameObject;

/**
 * Represents an Ant in the GameWorld
 * Only one ant can exist in GameWorld
 * Ant extends MovGameObject and implements ISteerable
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     MovGameObject and ISteerable
 * @since   1.0
 */
public class Ant extends MovGameObject implements ISteerable, ICollider{
	
	/**
	 * The fields that Ant must have on top of MovGameObject fields
	 */
	private static Ant theAnt;
	private int maxSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private boolean collisionFlag = false;
	private GameWorld gwAccess;
	
	/**
	 * Creates new private instance of Ant with the following parameters
	 * @param size
	 * @param locationX
	 * @param locationY
	 * @param heading
	 * @param speed
	 * @param maxSpeedStart
	 * @param foodLevelStart
	 * @param foodConsumptionRateStart
	 * @param healthLevelStart
	 * @param lastFlagReachedStart
	 */
	private Ant(int size, float locationX, float locationY, int heading, int speed, int maxSpeedStart, int foodLevelStart, int foodConsumptionRateStart, int healthLevelStart, int lastFlagReachedStart, GameWorld gw) {
		super(size, locationX, locationY, heading, speed);
		super.setColor(255, 0, 0);
		maxSpeed = maxSpeedStart;
		foodLevel = foodLevelStart;
		foodConsumptionRate = foodConsumptionRateStart;
		healthLevel = healthLevelStart;
		lastFlagReached = lastFlagReachedStart;
		gwAccess = gw;
	}
	
	/**
	 * Method that gets the single instance of Ant
	 * @return this single instance of ant
	 */
	public static Ant getAnt(GameWorld gw) {
		if(theAnt == null) {
			theAnt = new Ant(10,100,100,0,10,20,1000,1,10,1, gw);
		}
		return theAnt;
	}
	
	/**
	 * Method that resets the single instance of Ant when needed
	 * @return this new single instance of Ant
	 */
	public static Ant resetAnt(GameWorld gw) {
		theAnt = new Ant(10,100,100,0,10,20,1000,1,10,1, gw);
		return theAnt;
	}
	
	/**
	 * Method that updates this Ant's location
	 *
	public void move(double height, double width, int elapsedTime) {
		super.move(height, width, elapsedTime);
	}
	 *
	 */
	
	/**
	 * Method that updates this Ant's heading 5 degrees to the left/counter clockwise
	 */
	public void left() {
		super.setObjHeading(super.getObjHeading()-5);
	}
	
	/**
	 * Method that updates this Ant's heading 5 degrees to the right/clockwise
	 */
	public void right() {
		super.setObjHeading(super.getObjHeading()+5);
	}
	
	/**
	 * Method that gets this Ant's max speed
	 * @return this Ant's max speed
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	/**
	 * Method that sets the max speed of this Ant to a percentage of this Ant's healthLevel
	 * Method makes sure this Ant's speed cannot exceed it's maxSpeed
	 * @see Ant#healthLevel
	 * @see Ant#maxSpeed
	 */
	public void setMaxSpeed() {
		if(getHealthLevel() < 10) {
			maxSpeed = (getHealthLevel()%10);
		}else {
			maxSpeed = 10;
		}
	}
	
	/**
	 * Method that gets this Ant's food level
	 * @return this Ant's food level
	 */
	public int getFoodLevel() {
		return foodLevel;
	}
	
	/**
	 * Method that sets the foodLevel of this Ant
	 * @param f the integer to set this Ant's food level
	 */
	public void setFoodLevel(int f) {
		foodLevel = f;
	}
	
	/**
	 * Method that gets this Ant's food consumption rate
	 * @return this Ant's food consumption rate
	 */
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	
	/**
	 * Method that gets this Ant's health level
	 * @return this Ant's health level
	 */
	public int getHealthLevel() {
		return healthLevel;
	}
	
	/**
	 * Method that sets the health level of this Ant
	 * @param h the integer to set this Ant's health level
	 */
	public void setHealthLevel(int h) {
		healthLevel = h;
	}

	/**
	 * Method that gets this Ant's last reached flag
	 * @return this Ant's last reached flag
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * Method that sets this Ant's last reached flag
	 * @param x the integer to set this Ant's last reached flag
	 */
	public void setLastFlagReached(int x) {
		lastFlagReached = x;
	}
	
	/**
	 * Method that converts this Ant's fields to a string
	 * @return this Ant's fields as a string
	 */
	@Override
	public String toString() {
		String myClass = "Ant: ";
		String parentDesc = super.toString();
		String myDesc = " foodConsumptionRate=" + getFoodConsumptionRate() + " maxSpeed=" + getMaxSpeed();
		return myClass + parentDesc + myDesc;
	}
	
	/**
	 * Method that draws the game object
	 */
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt){
		super.draw(g, pCmpRelPrnt);
		int xDraw = (int) (getObjLocationX() + pCmpRelPrnt.getX() - getObjSize()/2);
		int yDraw = (int) (getObjLocationY() + pCmpRelPrnt.getY()- getObjSize()/2);
		g.fillArc(xDraw, yDraw, 2*getObjSize(), 2*getObjSize(), 0, 360);
	}

	@Override
	public boolean collidesWith(ICollider other) {
		boolean result = false;
		double thisCenterX = this.getLocation().getX();
		double thisCenterY = this.getLocation().getY();
		
		double otherCenterX = ((GameObject)other).getLocation().getX();
		double otherCenterY = ((GameObject)other).getLocation().getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCenters = (dx * dx + dy * dy);
		
		int thisRadius = this.getObjSize()/2;
		int otherRadius = ((GameObject)other).getObjSize()/2;
		
		int radiusSq = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if(distBetweenCenters <= radiusSq) {
			result = true;
		}
		return result;
	}

	@Override
	public void handleCollision(ICollider other) {
		setCollisionFlag();
		other.setCollisionFlag();
		if(other instanceof Spider) {
			gwAccess.gottenSpiderCollision();
		}
		if(other instanceof Flag) {
			gwAccess.flagCollision(getLastFlagReached());
		}
		if(other instanceof FoodStation) {
			FoodStation fs = (FoodStation) other;
			gwAccess.foodCollision(fs);
		}
	}

	@Override
	public void setCollisionFlag() {
		collisionFlag = true;
		
	}

	@Override
	public boolean getCollisionFlag() {
		return collisionFlag;
		
	}
	
}
