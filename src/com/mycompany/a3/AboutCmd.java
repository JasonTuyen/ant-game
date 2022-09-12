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
public class AboutCmd extends Command{
	
	/**
	 * Creates a new instance of this Command
	 * @param gw the GameWorld where the method is located and will be performed
	 * @see GameWolrd
	 */
	public AboutCmd() {
		super("About");
	}
	
	/**
	 * Overrides the action performed to specific display a dialog box
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("About", "Jason Tuyen - CSC 133", "Ok", null);
	}
}
