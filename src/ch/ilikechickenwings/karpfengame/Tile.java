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
	public static Image shit1;
	public static Image shit2;
	public static Image multipi;
	public static Image lifeBar;
	public static Image lifeHolderBar;
	public static Image coffeeBar;
	public static Image coffeeHolderBar;
	public static Image healthPack;
	
	
	public Tile(){
		coffee= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/coffee.png"));
		shit1= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/shit1.png"));
		shit2= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/shit2.png"));
		multipi = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/multipi.jpg"));
		lifeBar = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/lifebar.png"));
		lifeHolderBar = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/lifeholderbar.png"));
		coffeeBar = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/coffeebar.png"));
		coffeeHolderBar = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/coffeeholderbar.png"));
		healthPack = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/healthpack.png"));
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
