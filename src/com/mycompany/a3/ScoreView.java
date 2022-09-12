package com.mycompany.a3;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Observable;
import java.util.Observer;

/**
 * Serves as a "view" in the MVC architecture
 * Interacts with Game and GameWorld to complete the MVC architecture
 * ScoreView extends built-in Container class and implements built-in Observer class
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     Game and GameWorld
 * @since   2.0
 */
public class ScoreView extends Container implements Observer{
	
	/**
	 * These fields are displayed on the ScoreView
	 */
	private Label livesLeft;
	private Label clockValue;
	private Label lastFlagReached;
	private Label foodLevel;
	private Label healthLevel;
	private Label soundSetting;
	
	/**
	 * Creates an new instance of ScoreView
	 */
	public ScoreView() {
		this.setLayout(new FlowLayout(LEFT));
		this.getAllStyles().setPadding(Component.LEFT, 400);
		
		Label livesLeftLabel = new Label("Lives:");
		this.add(livesLeftLabel);
		livesLeft = new Label("0");
		this.add(livesLeft);
		
		Label clockValueLabel = new Label("Time:");
		this.add(clockValueLabel);
		clockValue = new Label("0");
		this.add(clockValue);
		
		Label lastFlagReachedLabel = new Label("Flag Reached:");
		this.add(lastFlagReachedLabel);
		lastFlagReached = new Label("1");
		this.add(lastFlagReached);
		
		Label foodLevelLabel = new Label("Food Level:");
		this.add(foodLevelLabel);
		foodLevel = new Label("100");
		this.add(foodLevel);
		
		Label healthLevelLabel = new Label("Health:");
		this.add(healthLevelLabel);
		healthLevel = new Label("10");
		this.add(healthLevel);
		
		Label soundSettingLabel = new Label("Sound:");
		this.add(soundSettingLabel);
		soundSetting = new Label("OFF");
		this.add(soundSetting);	
	}

	/**
	 * Method that updates the information displayed in ScoreView 
	 * Method will update automatically when needed
	 */
	@Override
	public void update(Observable o, Object arg) {
		GameWorldProxy proxy = (GameWorldProxy) arg;
		livesLeft.setText(""+Integer.toString(proxy.getLivesLeft()));
		clockValue.setText(""+Integer.toString(proxy.getClockValue()));
		lastFlagReached.setText(""+Integer.toString(proxy.getLastFlag()));
		foodLevel.setText(""+Integer.toString(proxy.getFoodLevel()));
		healthLevel.setText(""+Integer.toString(proxy.getHealthLevel()));
		if(proxy.getSoundSetting()) {
			soundSetting.setText("ON");
		}else {
			soundSetting.setText("OFF");
		}
		this.repaint();
	}
}
