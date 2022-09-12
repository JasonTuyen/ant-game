package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

/**
 * Command that will be placed on Game Model and awaits button click or key press
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   2.0
 * @see GameObject#collidesWith()
 * @deprecated As of version 3.0
 */
public class FlagCmd extends Command{
	
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
	public FlagCmd(GameWorld gw) {
		super("Flag Collision");
		this.gw = gw;
	}
	
	/**
	 * Overrides the action performed to specific method in GameWorld
	 * When action is performed dialog pops up to take user input
	 * Dialog will only accept numbers 1-9 as input
	 * @see GameWorld#flagCollision(int)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		Command cOk = new Command("Ok");
		TextField myText = new TextField(TextArea.NUMERIC);
		Command c = Dialog.show("Collide with Flag #", myText, cOk);
		if(c == cOk) {
			try {
				int flagNum = Integer.parseInt(myText.getText());
				gw.flagCollision(flagNum);
			}
			catch(Exception E) {
				Dialog.show("Error", "Please enter a number", "Ok", null);
			}
		}
		*/
	}
}
