package ch.ilikechickenwings.karpfengame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.ilikechickenwings.karpfengame.Reader.TileReader;

public class Tile {
	public static Image flat1;
	public static Image flat2;
	public static Image flat3;
	public static Image flat4;
	public static Image flat5;
	public static Image flat_ceiling;
	public static Image coffee;
	public static Image healthpacks;
	public static Image platform;
	public static BufferedImage[][] player;
	public static BufferedImage[][] eel;
	public static BufferedImage[][] seagull;
	public static BufferedImage[][] carp;
	public static BufferedImage[][] zombie1;
	public static BufferedImage[][] zombie2;
	public static BufferedImage[][] zombie3;
	public static BufferedImage[][] zombie4;
	public static BufferedImage[][] explosion1;
	public static Image shit1;
	public static Image shit2;
	public static Image background;
	public static Image lifeBar;
	public static Image lifeHolderBar;
	public static Image coffeeBar;
	public static Image coffeeHolderBar;
	public static Image healthPack;
	public static Image drop;
	public static Image baloonFish;
	public static Image baloonFish2;
	public static Image baloonFish2pic;
	public static Image eelPic;
	public static Image dropDownBackground;
	public static Image dropDownSelected;

	

	public static Image[] scenePics;
	
	

	
	public Tile(){
		
		new TileReader();
		
		
		coffee= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/coffee.png"));
		shit1= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/shit1.png"));
		shit2= Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/shit2.png"));
		background = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/background.png"));
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
		drop = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/drop.png"));
		baloonFish = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/baloonfish.png"));
		baloonFish2 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/baloonfish2.png"));
		baloonFish2pic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/baloonfish2pic.png"));
		eelPic = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/eel.png"));
		flat1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat1.png"));
		flat2 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat2.png"));		
		flat3 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat3.png"));
		flat4 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat4.png"));
		flat5 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat5.png"));
		flat_ceiling = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/flat_ceiling.png"));
		dropDownSelected = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/dropdownselected.png"));
		dropDownBackground = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/dropdownback.png"));
		BufferedImage eelSheet = null;
		BufferedImage playerSheet = null;
		BufferedImage seagullSheet = null;
		BufferedImage carpSheet = null;
		BufferedImage zombieSheet = null;
		BufferedImage zombieSheet2 = null;
		BufferedImage zombieSheet3 = null;
		BufferedImage zombieSheet4 = null;
		BufferedImage explosionSheet1 = null;
		try {
			eelSheet = ImageIO.read(getClass().getResource("/res/eelSheet.png"));
			playerSheet = ImageIO.read(getClass().getResource("/res/playerSheet.png"));
			seagullSheet = ImageIO.read(getClass().getResource("/res/seagullSheet.png"));
			carpSheet = ImageIO.read(getClass().getResource("/res/carpSheet.png"));
			zombieSheet = ImageIO.read(getClass().getResource("/res/zombieSheet.png"));
			zombieSheet2 = ImageIO.read(getClass().getResource("/res/zombieSheet2.png"));
			zombieSheet3 = ImageIO.read(getClass().getResource("/res/zombieSheet3.png"));
			zombieSheet4 = ImageIO.read(getClass().getResource("/res/zombieSheet4.png"));
			explosionSheet1 = ImageIO.read(getClass().getResource("/res/explosionSheet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eel= AnimatedTile.getAnimation(eelSheet, 2, 3, 50, 20);
		player= AnimatedTile.getAnimation(playerSheet, 3, 4, 20, 40);
		seagull= AnimatedTile.getAnimation(seagullSheet, 1, 4, 30, 30);
		carp= AnimatedTile.getAnimation(carpSheet, 2, 2, 15, 10);
		zombie1= AnimatedTile.getAnimation(zombieSheet, 3, 4, 20, 40);
		zombie2= AnimatedTile.getAnimation(zombieSheet2, 3, 4, 20, 40);
		zombie3= AnimatedTile.getAnimation(zombieSheet3, 3, 4, 20, 40);
		zombie4= AnimatedTile.getAnimation(zombieSheet4, 3, 4, 20, 40);
		explosion1= AnimatedTile.getAnimation(explosionSheet1, 1, 6, 50, 50);
	}
	
	
	
}
