package ch.ilikechickenwings.karpfengame.Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class SoundSystem {
	private Clip clip;
	
	
	public SoundSystem(final URL url){

	
	          try {
	            this.clip = AudioSystem.getClip();
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
	            clip.open(inputStream);
	            FloatControl gainControl = 
	            	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	            	gainControl.setValue(-5.0f);
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
	          } catch (Exception e) {
	            System.err.println(e.getMessage());
	          }
	        }
	   
		

		public void stop(){
			this.clip.stop();
			
		}
	

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

