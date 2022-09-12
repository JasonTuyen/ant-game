package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

/**
 * Sets the style of buttons used to a uniformed design
 * ButtonStyled extends built-in Button class
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   2.0
 */
public class ButtonStyled extends Button{
	
	/**
	 * Creates new instance of ButtonStyled Button with the following styles
	 * @param cmd
	 */
	public ButtonStyled(Command cmd) {
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.getAllStyles().setPadding(Component.TOP, 5);
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBgTransparency(127);
		this.getPressedStyle().setBgColor(ColorUtil.BLUE);
		this.getPressedStyle().setFgColor(ColorUtil.WHITE);
		this.setCommand(cmd);
	}
}
