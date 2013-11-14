package ch.ilikechickenwings.karpfengame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Tile {
	public static Image coffee;
	public static Image healthpacks;
	public static Image platform;
	public static BufferedImage[] player;
	
	
	public Tile(){
		coffee= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/java-logo[1].gif"));
		
		BufferedImage playerSheet= (BufferedImage) Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/java-logo[1].gif"));
		
		player= AnimatedTile.getAnimation(playerSheet, 3, 3, 30, 50);
		
	}
	
	
	
}
