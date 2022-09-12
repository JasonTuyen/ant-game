package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCmd extends Command{
	Game g;
	public PauseCmd(Game g) {
		super("Pause");
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		g.pauseGame();
	}
}
