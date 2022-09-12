package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Class that creates a sound object that loops the playing sound
 * 
 * @author  Jason Tuyen
 * @version 3.0
 * @since   3.0
 */
public class BGSound implements Runnable{
	private Media m;
	
	/**
	 * Creates a new instance of a background sound object
	 * @param fileName
	 */
	public BGSound(String fileName) {
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
	 * Method that pauses the looping sound
	 */
	public void pause() {
		m.pause();
	}
	
	/**
	 * Method that plays the looping sound from where we left off
	 */
	public void play() {
		m.play();
	}

	/**
	 * Method that plays the media continuously on a loop
	 */
	@Override
	public void run() {
		m.setTime(0);
		m.play();
	}

}
