package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Point;

import java.util.Observable;
import java.util.Observer;

/**
 * Serves as a "view" in the MVC architecture
 * Interacts with Game and GameWorld to complete the MVC architecture
 * MapView extends built-in Container class and implements built-in Observer class
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @see     Game and GameWorld
 * @since   2.0
 */
public class MapView extends Container implements Observer{
	
	/**
	 * TextArea that will store the text to be displayed on view and IGameWorld as proxy to access GameWorld
	 */
	TextArea mapText;
	private IGameWorld proxy;
	private int px;
	private int py;
	
	/**
	 * Creates an new instance of MapView
	 */
	public MapView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
		/**
		 * @deprecated As of version 3.0, console text is now replaced with graphics
		 * 
		mapText = new TextArea();
		mapText.setEditable(false);
		this.add(mapText);
		*/
	}
	
	/**
	 * Method that updates the information displayed in MapView 
	 * Method will update automatically when needed
	 */
	public void update(Observable o, Object arg) {
		proxy = (IGameWorld) arg;
		repaint();
		/**
		 * @see MapView#paint()
		 * @deprecated As of version 3.0 and moved to paint method
		 * 
		IIterator i = proxy.getCollection().getIterator();
		String displayText = "";
		while(i.hasNext()) {
			displayText = displayText + i.getNext().toString()+"\n";
		}
		mapText.setText(displayText);
		this.repaint();
		 *
		 */
	}
	
	/**
	 * Method that gets the height of MapView container
	 * @return the height of MapView container in double
	 */
	public double getMapHeight() {
		return this.getHeight();
	}
	
	/**
	 * Method that gets the width of MapView container
	 * @return the width of MapView container in double
	 */
	public double getMapWidth() {
		return this.getWidth();
	}
	
	/**
	 * Method that overrides base paint method to draw necessary game objects onto MapView
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = proxy.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject curr = iterator.getNext();
			curr.draw(g, pCmpRelPrnt);
		}
	}
	
	public Point getClicked() {
		Point upPoint = new Point(px,py);
		return upPoint;
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		px = x - getParent().getAbsoluteX();
		py = y - getParent().getAbsoluteY();
		
		Point pPtrRelPrnt = new Point(px, py);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator iterator = proxy.getCollection().getIterator();
		
		while(iterator.hasNext()) {
			GameObject curr = iterator.getNext();
			if(curr instanceof ISelectable) {
				ISelectable selectedObj = (ISelectable) curr;
				if(selectedObj.contains(pPtrRelPrnt, pCmpRelPrnt) && proxy.getGameState()) {
					selectedObj.setSelected(true);
				}else {
					selectedObj.setSelected(false);
				}
			}
		}
		repaint();
	}
}
