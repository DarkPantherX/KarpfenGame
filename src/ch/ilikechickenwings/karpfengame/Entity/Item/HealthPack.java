package ch.ilikechickenwings.karpfengame.Entity.Item;

import java.awt.Color;
import java.awt.Graphics2D;


public class HealthPack extends Item{

	private int health; // regenerate
	
	public HealthPack(int x, int y){
		setWidth(10);
		setHeight(10);
		setX_Point(x-(getWidth()/2));
		setY_Point(y-getHeight());

		setHealth(10);
	}
	
	public void draw(Graphics2D g2, int xOffset){
		g2.setColor(Color.magenta);
		g2.fillRect(getX_Point()-xOffset, getY_Point(),getWidth(),getHeight());
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
}
