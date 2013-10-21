package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class WalkZombie extends Mob{
	

	private boolean dir=true; // true = they walk to right, false = they walk to left
	
	
	public WalkZombie(int x,int y) {
		setWidth(30);
		setHeight(70);
		setX_Point(x);
		setY_Point(y-getHeight());
		setLifes(100);
		setxVel(4);
		setyVel(0);
		setDamage(10);
		setGravityOn(false);
	}


	public void update(InputHandler inHandler) {
		
		if (dir) { // right
			setX_Point(getX_Point()+getxVel());
		} else {
			setX_Point(getX_Point()-getxVel());
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
