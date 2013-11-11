package ch.ilikechickenwings.karpfengame;

import java.awt.Image;
import java.awt.Toolkit;

public class Tile {
	public static Image coffee;
	public static Image healthpacks;
	public static Image platform;
	
	
	public Tile(){
		coffee= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/java-logo[1].gif"));
		
	}
	
	
	
}
