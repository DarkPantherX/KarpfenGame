package ch.ilikechickenwings.karpfengame.Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundSystem {

	public static void playSound(final URL url){
		
	    
	          try {
	            Clip clip = AudioSystem.getClip();
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
	            clip.open(inputStream);
	            clip.start(); 
	          } catch (Exception e) {
	            System.err.println(e.getMessage());
	          }
	        }
	   
		
		
		
	}
	
	
	
	

