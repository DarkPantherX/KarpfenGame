package ch.ilikechickenwings.karpfengame.Sound;

import java.net.URL;


public class Sounds {
	public static URL playerShot;
	public static URL gameMusic1;
	public static URL gameMusic2;
	
		public Sounds(){
		playerShot =getClass().getResource("/Res/shot.wav");
		gameMusic1 =getClass().getResource("/Res/Game1.wav");
		gameMusic2 =getClass().getResource("/Res/Game2.wav");
		
	}

}
