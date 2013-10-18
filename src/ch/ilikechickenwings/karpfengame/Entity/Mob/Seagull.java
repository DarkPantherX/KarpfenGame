package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;

public class Seagull extends Mob{
	
	public Seagull(int x){
		setWidth(30);
		setHeight(30);
		setX_Point(x);
		setY_Point(0);
		setLifes(100);
		setxVel(4);
		setyVel(0);
		setDamage(10);
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.yellow);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
	}
	
}
