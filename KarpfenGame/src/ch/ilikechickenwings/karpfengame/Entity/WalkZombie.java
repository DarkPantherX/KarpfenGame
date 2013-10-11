package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;


import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class WalkZombie extends Entity{
	

	private boolean dir=true; // true = they walk to right, false = they walk to left
	
	
	public WalkZombie(int x,int y) {
		setWidth(30);
		setHeight(70);
		setX_Point(x);
		setY_Point(y-getHeight());
		setLifes(100);
		setVelocity(4);
		setDamage(10);
		
	}


	public void update(InputHandler inHandler) {
		
		if (dir) { // right
			setX_Point(getX_Point()+getVelocity());
		} else {
			setX_Point(getX_Point()-getVelocity());
		}
	}
	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.blue);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}
	
	
	
}
