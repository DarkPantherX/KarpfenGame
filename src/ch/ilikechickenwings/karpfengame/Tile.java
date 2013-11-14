package ch.ilikechickenwings.karpfengame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	public static Image coffee;
	public static Image healthpacks;
	public static Image platform;
	public static BufferedImage[][] player;
	
	
	public Tile(){
		coffee= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/java-logo[1].gif"));
		
		BufferedImage playerSheet = null;
		try {
			playerSheet = ImageIO.read(getClass().getResource("/res/playerSheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		player= AnimatedTile.getAnimation(playerSheet, 3, 3, 20, 40);
		
	}
	
	
	
}
