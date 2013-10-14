package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Carp extends Projectile{
	
	
	
	public Carp(int x, int y){
		setX_Point(x);
		setY_Point(y);
		setWidth(20);
		setHeight(20);
		setVelocity(10);
		setDamage(50);
		
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.darkGray);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}
	
}
