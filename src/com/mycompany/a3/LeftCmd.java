package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command that will be placed on Game Model and awaits button click or key press
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   2.0
 */
public class LeftCmd extends Command{
	
	/**
	 * The GameWorld where the command will be performed
	 * @see GameWorld
	 */
	private GameWorld gw;
	
	/**
	 * Creates a new instance of this Command
	 * @param gw the GameWorld where the method is located and will be performed
	 * @see GameWolrd
	 */
	public LeftCmd(GameWorld gw) {
		super("Left");
		this.gw = gw;
	}
	
	/**
	 * Overrides the action performed to specific method in GameWorld
	 * @see GameWorld#left()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.left();
	}
}
