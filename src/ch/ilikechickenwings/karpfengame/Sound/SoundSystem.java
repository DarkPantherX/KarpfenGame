package ch.ilikechickenwings.karpfengame.Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundSystem {

	public static synchronized void playSound(final URL url){
		
		new Thread(new Runnable() { 
            public void run() {
	          try {
	            Clip clip = AudioSystem.getClip();
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
	            clip.open(inputStream);
	            clip.start(); 
	          } catch (Exception e) {
	            System.err.println(e.getMessage());
	          }
	        }
	   
		
		}).start();
		
		
	}
	
	
	
}

