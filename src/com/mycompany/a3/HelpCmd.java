package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Command that will be placed on Game Model and awaits button click or key press
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   2.0
 */
public class HelpCmd extends Command{
	
	/**
	 * Creates a new instance of this Command
	 */
	public HelpCmd() {
		super("Help");
	}
	
	/**
	 * Overrides the action performed to display a specific dialog box
	 * Dialog box shows all possible button key presses
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String helpInfo = "Avaliable Commands: 'a' to accelerate, 'b' to brake, 'l' to turn left, 'r' to turn right, 'f' to collide with food station, 'g' to collide with Spider, 't' to tick the game";
		Dialog.show("Help", helpInfo, "Ok", null);
	}
}
