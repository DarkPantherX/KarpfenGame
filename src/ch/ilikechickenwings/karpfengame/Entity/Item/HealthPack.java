package ch.ilikechickenwings.karpfengame.Entity.Item;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Tile;


public class HealthPack extends Item{

	private int health; // regenerate
	
	public HealthPack(int x, int y){
		setWidth(15);
		setHeight(15);
		setX_Point(x-(getWidth()/2));
		setY_Point(y-getHeight());
		setHealth(10);
	}
	
	public void draw(Graphics2D g2, int xOffset){
		g2.setColor(Color.magenta);
		g2.drawImage(Tile.healthPack,getX_Point()-xOffset, getY_Point(),getWidth(),getHeight(),null);
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
}
