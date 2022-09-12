package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Interface for GameObjects that are selectable, at our current version only fixed game objects are selectable
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     FixGameObject
 * @since   3.0
 */
public interface ISelectable {
	public void setSelected(boolean y);
	
	public boolean isSelected();
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	public void draw(Graphics g, Point pCmpRelPrnt);
}
