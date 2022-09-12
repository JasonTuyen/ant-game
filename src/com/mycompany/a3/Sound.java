package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Class that encapsulates a sound file and provides a method to play the sound
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   3.0
 */
public class Sound {
	private Media m;
	
	/**
	 * Creates new instance of a sound object
	 * @param fileName
	 */
	public Sound(String fileName) {
		if(Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		while(m==null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
				m = MediaManager.createMedia(is, "audio/wav");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method that plays the sound object
	 */
	public void play() {
		m.setTime(0);
		m.play();
	}
}
