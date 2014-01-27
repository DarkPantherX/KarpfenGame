package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Grenade extends Projectile {
	
	// Cheaty Constructor
	public Grenade(int x, int y, int dir){
		setX_Point(x);
		setY_Point(y);
		setWidth(10);
		setHeight(10);
		setxVel(10);
		setyVel(-10);
		setDir(dir);
		setDamage(50);
		setGravityOn(true); 
	}
	
	
	// More general Constructor
	public Grenade(int x, int y, int x_vel, int y_vel){
		setX_Point(x);
		setY_Point(y);
		setWidth(10);
		setHeight(10);
		setxVel(x_vel);
		setyVel(y_vel);
		setDir(1);
		if(x_vel<0){setDir(-1);}
		setDamage(50);
		setGravityOn(true); 
	}
	

	public void update(InputHandler inHandler){
		if(isGravityOn()){
			updateGravity();
		}
		setX_Point(getX_Point()+getxVel()*getDir());
		setY_Point(getY_Point()+getyVel());

	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.CYAN);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

}
