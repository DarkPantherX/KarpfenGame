package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class WalkZombie extends Entity{
	

	public boolean dir=true; // true = they walk to left, false = they walk to right
	
	public WalkZombie(int x,int y) {
		setWidth(30);
		setHeight(70);
		setX_Point(x);
		setY_Point(y-getHeight());
		setLifes(100);
		setVelocity(4);
		
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.blue);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
	}
	
}
