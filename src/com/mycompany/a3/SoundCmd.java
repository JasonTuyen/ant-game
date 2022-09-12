package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command that will be placed on Game Model and awaits button click or key press
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   2.0
 */
public class SoundCmd extends Command{
	/**
	 * The CheckBox that will activate the command
	 * The GameWorld where the command will be performed
	 * @see GameWorld
	 */
	private CheckBox soundCB;
	private GameWorld gw;
	
	/**
	 * Creates a new instance of this Command
	 * @param gw the GameWorld where the method is located and will be performed
	 * @see GameWolrd
	 */
	public SoundCmd(GameWorld gw, CheckBox soundCB) {
		super("Sound ON/OFF");
		this.gw = gw;
		this.soundCB = soundCB;
	}
	
	/**
	 * Overrides the action performed to specific method in GameWorld
	 * @see GameWorld#setSound()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(soundCB.isSelected()) {
			System.out.println("Sound turned on");
		}else {
			System.out.println("Sound turned off");
		}
		gw.setSound();
	}
}
