package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Grenade extends Projectile {
	
	/*
	 *   This Projectile does not use the variable dir to calculate do the update
	 *   TODO
	 */
	
	
	private int size; // 1= small, 2= medium, 3=large
	
	// Cheaty Constructor, (Grenade created by Player)
	public Grenade(int x, int y, int dir){
		setSize(3);
		setX_Point(x);
		setY_Point(y);
		setWidth(10*getSize());
		setHeight(10*getSize());
		setxVel(10*dir);
		setyVel(-10);
		setDamage(50*getSize());
		setGravityOn(true); 
	}
	
	
	// More general Constructor
	public Grenade(int x, int y, int x_vel, int y_vel, int size){
		setX_Point(x);
		setY_Point(y);
		setSize(size);
		setWidth(10*size);
		setHeight(10*size);
		setxVel(x_vel);
		setyVel(y_vel);
		setDir(1);
		if(x_vel<0){setDir(-1);}
		setDamage(50*size);
		setGravityOn(true); 
	}
	

	public void update(InputHandler inHandler){
		if(isGravityOn()){
			updateGravity();
		}
		setX_Point(getX_Point()+getxVel());
		setY_Point(getY_Point()+getyVel());

	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.CYAN);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}
	
	
	

}
