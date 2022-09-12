package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;

public class PositionCmd extends Command{
	
	private GameWorld gw;
	private MapView mv;
	
	public PositionCmd(GameWorld gw, MapView mv) {
		super("Position");
		this.gw = gw;
		this.mv = mv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int x = mv.getClicked().getX();
		int y = mv.getClicked().getY();
		gw.position(x,y);
	}
}
