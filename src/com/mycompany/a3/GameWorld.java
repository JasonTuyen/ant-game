package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point;

import java.util.Observable;
import java.util.Random;

/**
 * Serves as the "model" in the MVC architecture
 * Interacts with Game, MapView, and ScoreView to complete the MVC architecture
 * Game notifies it's Observers when a change occurs in the GameWorld model
 * GameWorld extends Observable and implement IGameWorld
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     Observable and IGameWorld and GameWorld and ScoreView and MapView and GameObjectCollection
 * @since   1.0
 */
public class GameWorld extends Observable implements IGameWorld{
	
	/**
	 * The fields that are used in the GameWorld to track game state information 
	 * and other game requirements such as sound and game world height and width
	 * theCollection tracks the GameObjects in the GameWorld
	 */
	private GameObjectCollection theCollection;
	private int livesLeft = 3;
	private int clockTime = 0;
	private boolean gameState = false;
	private boolean soundSetting;
	private double GWHeight;
	private double GWWidth;
	private Sound foodCollisionSound, spiderCollisionSound, flagCollisionSound, test;
	private BGSound backgroundSound;
	Random rand = new Random();
	GameWorld gw = this;
	
	/**
	 * Creates a new instance of GameWorld
	 */
	public GameWorld() {
		init();
	}
	
	/**
	 * Initialize a GameObjectCollection and adds starting GameObjects
	 * to theCollection Vector
	 */
	public void init() {
		theCollection = new GameObjectCollection();
		theCollection.add(new Flag(20, 100, 100, 1));
		theCollection.add(new Flag(20, 200, 300, 2));
		theCollection.add(new Flag(20, 300, 500, 3));
		theCollection.add(new Flag(20, 400, 700, 4));
		theCollection.add(new Spider(10+rand.nextInt(5),rand.nextFloat()*1000,rand.nextFloat()*1000,rand.nextInt(359),5+rand.nextInt(10)));
		theCollection.add(new Spider(10+rand.nextInt(5),rand.nextFloat()*1000,rand.nextFloat()*1000,rand.nextInt(359),5+rand.nextInt(10)));
		theCollection.add(new FoodStation((10+rand.nextInt(100)),rand.nextFloat() * 500, rand.nextFloat() * 500,1));
		theCollection.add(new FoodStation((10+rand.nextInt(100)),rand.nextFloat() * 500, rand.nextFloat() * 500,1));
		theCollection.add(Ant.getAnt(gw));
		proxyNotifyObserver();
	}
	
	/**
	 * Method that instantiates sound objects
	 */
	public void createSounds() {
		backgroundSound = new BGSound("background.wav");
		foodCollisionSound = new Sound("food.wav");
		spiderCollisionSound = new Sound("spider.wav");
		flagCollisionSound = new Sound("flag.wav");
		//test = new Sound("alarm.wav");
	}
	
	public boolean getGameState() {
		return gameState;
	}
	
	public void setGameState() {
		gameState = !gameState;
	}
	
	/**
	 * Method the gets the amount of lives left before this GameWorld resets
	 */
	public int getLivesLeft() {
		return this.livesLeft;
	}
	
	/**
	 * Method that gets the clock time elapsed in this GameWorld
	 */
	public int getClockValue() {
		return this.clockTime;
	}
	
	/**
	 * Method that gets the last flag reached by theAnt
	 * Method uses findAnt method to retrieve theAnt
	 * @see GameWorld#findAnt() and Ant#getLastFlagReached()
	 */
	public int getLastFlag() {
		Ant a = findAnt();
		return  a.getLastFlagReached();
	}
	
	/**
	 * Method that gets the food level of theAnt
	 * Method uses findAnt method to retrieve theAnt
	 * @see GameWorld#findAnt() and Ant#getFoodLevel()
	 */
	public int getFoodLevel() {
		Ant a = findAnt();
		return a.getFoodLevel();
	}
	
	/**
	 * Method that gets the health level of theAnt
	 * Method uses findAnt method to retrieve theAnt
	 * @see GameWorld#findAnt() and Ant#getHealthLevel()
	 */
	public int getHealthLevel() {
		Ant a = findAnt();
		return a.getHealthLevel();
	}
	
	/**
	 * Method that gets the sound setting of this GameWorld
	 */
	public boolean getSoundSetting() {
		return this.soundSetting;
	}
	
	/**
	 * Method that gets theCollection Vector of GameObjects in this GameWorld
	 */
	public GameObjectCollection getCollection() {
		return this.theCollection;
	}
	
	//Proxy Design for notify observer
	/**
	 * Method that notify observers through a proxy of GameWorld known as GameWorldProxy
	 * Method creates a GameWorldproxy and calls setChange method and notifyObserver method
	 * from built-in Observable class that notify observers when changes occur in GameWorld model
	 * 
	 */
	private void proxyNotifyObserver() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(proxy);
	}
	
	/**
	 * Method that sets sound of the GameWorld
	 */
	public void setSound(){
		soundSetting = !soundSetting;
		proxyNotifyObserver();
	}
	
	/**
	 * Method that sets height of the GameWorld
	 * @param h the double to set this GameWorld height
	 */
	public void setGWHeight(double h) {
		this.GWHeight = h;
	}
	
	/**
	 * Method that sets width of the GameWorld
	 * @param w the double to set this GameWorld width
	 */
	public void setGWWidth(double w) {
		this.GWWidth = w;
	}
	
	/**
	 * Method that iterates through theCollection Vector to find theAnt
	 * @return theAnt from our theCollection Vector
	 */
	public Ant findAnt() {
		for(int i=0; i<theCollection.getSize(); i++) {
			if(theCollection.getCollectionAt(i) instanceof Ant) {
				return (Ant) theCollection.getCollectionAt(i);
			}
		}
		return null;
	}
	
	/**
	 * Method to iterates through theCollection Vector to find food stations
	 * Method should return a random food station (WIP)
	 * @return the first non-empty food station in theCollection Vector
	 * @see GameObject#collidesWith()
	 * @deprecated As of version 3.0 since collision is now determined automatically
	 */
	public FoodStation findFoodStation() {
		for(int i=0; i<theCollection.getSize(); i++) {
			if(theCollection.getCollectionAt(i) instanceof FoodStation && ((FoodStation) theCollection.getCollectionAt(i)).getCapacity() !=0 ) {
				return (FoodStation) theCollection.getCollectionAt(i);
			}
		}
		return null;
	}
	
	/**
	 * Method to iterates through theCollection Vector to find spiders
	 * Method should return a certain spider (WIP)
	 * @return the first spider in theCollection Vector
	 * @see GameObject#collidesWith()
	 * @deprecated As of version 3.0 since collision is now determined automatically
	 */
	public Spider findRandSpider() {
		for(int i=0; i<theCollection.getSize(); i++) {
			if(theCollection.getCollectionAt(i) instanceof Spider) {
				return (Spider) theCollection.getCollectionAt(i);
			}
		}
		return null;
	}
	
	/**
	 * Method determines if a life is lost based on theAnt's health and food level
	 * Method adjusts lives left in GameWorld as needed
	 * Method clears theCollectionVector, resets theAnt, and reinitializes the GameWorld if needed
	 * @see Ant#getHealthLevel() and Ant#getFoodLevel() and Ant#resetAnt()
	 * @see GameObjectCollection#clearCollection()
	 * @see GameWorld#livesLeft and GameWorld#init()
	 */
	public void loseLivesLeft() {
		Ant a = findAnt();
		if(a.getFoodLevel() == 0 || a.getHealthLevel() == 0) {
			livesLeft--;
			if(livesLeft < 0) {
				System.out.println("Game over, you failed!");
				System.exit(0);
			}
			System.out.println("Ant's can no longer move, resetting world");
			theCollection.clearCollection();
			Ant.resetAnt(gw);
			init();
		}
		
	}
	
	/**
	 * Method that accelerates theAnt's speed in this GameWorld
	 * Method checks theAnt's various attributes to determine if theAnt can accelerate
	 * @see Ant#getMaxSpeed() and MovGameObj#getObjSpeed() and MovGameObj#setObjSpeed()
	 * @see Ant#getHealtLevel() and Ant#getFoodLevel()
	 */
	public void accelerate() {
		Ant a = findAnt();
		if(a.getMaxSpeed() > a.getObjSpeed() && a.getHealthLevel() > a.getObjSpeed() && a.getFoodLevel() != 0) {
			a.setObjSpeed(a.getObjSpeed()+1);
			System.out.println("Ant has been sped up");
		}else {
			System.out.println("Ant cannot be sped up further");
		}
		proxyNotifyObserver();
	}
	
	/**
	 * Method that slows down theAnt's speed in this GameWorld
	 * Method checks theAnt's various attributes to determine if theAnt can brake
	 * @see MovGameObject#getObjSpeed() and MoveGameObject#setObjSpeed()
	 */
	public void brake() {
		Ant a = findAnt();
		if(a.getObjSpeed() != 0) {
			a.setObjSpeed(a.getObjSpeed()-1);
			System.out.println("Ant has been slowed down");
		}else {
			System.out.println("Ant cannot be slowed down further");
		}
		proxyNotifyObserver();
	}
	
	/**
	 * Method that turns theAnt to the left in this GameWorld
	 * Also known as changing theAnt's heading 5 degrees counter-clockwise
	 * @see Ant#left()
	 */
	public void left() {
		Ant a = findAnt();
		a.left();
		proxyNotifyObserver();
	}
	
	/**
	 * Method that turns theAnt to the right in this GameWorld
	 * Also known as changing theAnt's heading 5 degrees clockwise
	 * @see Ant#right()
	 */
	public void right() {
		Ant a = findAnt();
		a.right();
		proxyNotifyObserver();
	}
	
	/**
	 * Method that takes a number and compares it to the last flag reached by theAnt
	 * Method updates theAnt's last flag reached if needed
	 * Method ends the game if the final flag is reached (currently the final flag is flag #4)
	 * @param x the integer to compare to the last flag reached by theAnt
	 */
	public void flagCollision(int x) {
		flagCollisionSound.play();
		Ant a = findAnt();
		if(x == a.getLastFlagReached() + 1) {
			a.setLastFlagReached(x);
			System.out.println("New Flag Reached");
			if(a.getLastFlagReached() == 4) {
				System.out.println("Game over, you win! Total time: " + clockTime);
				System.exit(0);
			}
		}
		proxyNotifyObserver();
	}
	
	/**
	 * Method that simulates theAnt colliding with a food station in the GameWorld
	 * When theAnt collides with a food station: the food stations empties and changes color
	 * Also theAnt food levels increases by the food station's capacity
	 * Also a new food station is created and added to theCollection Vector
	 * @see Ant#setFoodLevel(int) and Ant#getFoodLevel()
	 * @see FoodStation#setCapacity(int) and FoodStation#getCapacity() and FoodStation#setColor(int, int, int)
	 */
	public void foodCollision(FoodStation fs) {
		foodCollisionSound.play();
		Ant a = findAnt();
		FoodStation f = fs;
		a.setFoodLevel(a.getFoodLevel()+f.getCapacity());
		f.setCapacity(0);
		f.setColor(200, 255, 200);
		theCollection.add(new FoodStation((10+rand.nextInt(100)),rand.nextFloat() * 1000, rand.nextFloat() * 1000,1));
		System.out.println("Collided with Food Station");
		proxyNotifyObserver();
	}
	
	/**
	 * Method that simulates theAnt colliding with a spider in the GameWorld
	 * Method also calls loseLivesLeft method to checks to see if theAnt dies after the collision with the spider
	 * When theAnt collides with a spider: theAnt loses health, changes color, and loses max speed
	 * @see Ant#setHealthLevel(int) and Ant#getHealtLevel() and Ant#setColor(int, int, int)
	 * @see Ant#setMaxSpeed() and MovGameObject#getObjSpeed and MoveGameObject#getObjSpeed
	 */
	public void gottenSpiderCollision() {
		spiderCollisionSound.play();
		Ant a = findAnt();
		a.setHealthLevel(a.getHealthLevel()-1);
		a.setColor(255, ColorUtil.green(a.getColor())+10, ColorUtil.green(a.getColor())+10);
		a.setMaxSpeed();
		if(a.getObjSpeed() > a.getMaxSpeed()) {
			a.setObjSpeed(a.getMaxSpeed());
		}
		System.out.println("Collided with Spider");
		loseLivesLeft();
		proxyNotifyObserver();
	}
	
	/**
	 * Method that simulates a second passing in this GameWorld
	 * When the clock is ticked: 
	 * Spiders update their heading
	 * All movable objects update their locations based on their heading and speed
	 * theAnt's food level is reduced by its food consumption rate
	 * GameWorld clockTime is ticked by 1
	 * @see GameObjectCollection, MovGameObject
	 */
	public void ticked(int elapsedTime) {
		for(int i=0; i<theCollection.getSize(); i++) {
			if(theCollection.getCollectionAt(i) instanceof Spider) {
				Spider s = (Spider) theCollection.getCollectionAt(i);
				s.setObjHeading(s.getObjHeading()+rand.nextInt(5));
			}
		}

		for(int i=0; i<theCollection.getSize(); i++) {
			if(theCollection.getCollectionAt(i) instanceof MovGameObject) {
				MovGameObject m = (MovGameObject) theCollection.getCollectionAt(i);
				m.move(GWHeight, GWWidth, elapsedTime);
			}
		}
		
		Ant a = findAnt();
		a.setFoodLevel(a.getFoodLevel()-a.getFoodConsumptionRate());

		clockTime++;
		System.out.println("Game clock ticked");
		//checkCollision();
		loseLivesLeft();
		proxyNotifyObserver();
	}
	
	/**
	 * 
	 */
	public void checkCollision() {
		IIterator iter = theCollection.getIterator();
		while(iter.hasNext()) {
			GameObject thisObj = iter.getNext();
			if(thisObj instanceof ICollider) {
				ICollider thisColliderObj = (ICollider) thisObj;
				IIterator otherIter = theCollection.getIterator();
				while(otherIter.hasNext()) {
					GameObject otherObj = otherIter.getNext();
					if(otherObj instanceof ICollider && !(thisObj.equals(otherObj))) {
						ICollider otherColliderObj = (ICollider) otherObj;
						if(thisColliderObj.collidesWith(otherColliderObj)) {
							thisColliderObj.handleCollision(otherColliderObj);
							if(otherColliderObj instanceof FoodStation) {
								theCollection.add(new FoodStation((10+rand.nextInt(100)),rand.nextFloat() * 1000, rand.nextFloat() * 1000,1));
							}
						}
					}
				}
			}
		}
		removeCollidedObjects();
		proxyNotifyObserver();
	}
	
	/**
	 * 
	 */
	public void removeCollidedObjects() {
		IIterator iter = theCollection.getIterator();
		while(iter.hasNext()) {
			GameObject obj = iter.getNext();
			if(obj instanceof ICollider && ((ICollider)obj).getCollisionFlag()) {
				iter.remove(obj);
			}
		}
	}
	
	/**
	 * 
	 */
	public void position(int x, int y) {
		IIterator iterator = theCollection.getIterator();
		while(iterator.hasNext()) {
			GameObject curr = iterator.getNext();
			if(curr instanceof FixGameObject && ((FixGameObject) curr).isSelected()) {
				curr.setObjLocation(x,y);
			}
		}
		
	}
	
	/**
	 * Method that exits the game when it is called
	 */
	public void exit() {
		System.exit(0);
	}
	
	
	/**
	 * @deprecated As of version 2.0
	 * 
	public void display() {
		System.out.println("Number of lives left: " + livesLeft);
		System.out.println("Elapsed time: " + clockTime);
		Ant a = findAnt();
		System.out.println("Last flag reached: " + a.getLastFlagReached());
		System.out.println("Ant's food level: " + a.getFoodLevel());
		System.out.println("Ant's health level: " + a.getHealthLevel());
	}
	*
	*/
	
	
	/**
	 * @deprecated As of version 2.0
	 * 
	public void map() {
		for (int i=0; i < vectorGameObject.size(); i++) {
			System.out.println(vectorGameObject.elementAt(i).toString());
		}
	}
	*
	*/
	
	/**
	 * @deprecated As of version 2.0
	 *
	public void yes() {
		System.out.println("Closing game");
		System.exit(0);
	}
	*
	*/
	
	/**
	 * @deprecated As of version 2.0
	 * 
	public void no() {
		System.out.println("Not closing game");
	}
	*
	*/
}
