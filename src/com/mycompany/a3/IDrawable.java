package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Interface for drawing game objects
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     GameObject
 * @since   3.0
 */
public interface IDrawable {
	/**
	 * Method that draws game object
	 * @param g
	 * @param pCmpRelPrnt
	 */
	public void draw(Graphics g, Point pCmpRelPrnt);
}
