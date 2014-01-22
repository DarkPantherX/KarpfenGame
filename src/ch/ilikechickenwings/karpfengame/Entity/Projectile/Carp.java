package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Carp extends Projectile{
	
	int damageLoss; // damageloss per gametick
	
	public Carp(int x, int y, int dir){
		setX_Point(x);
		setY_Point(y);
		setWidth(10);
		setHeight(10);
		setxVel(10);
		setyVel(0);
		setDir(dir);
		setDamage(50);
		setDamageLoss(1);
		setGravityOn(false); // or shall this be changed?
	}
	
	public void update(InputHandler inHandler){
		setX_Point(getX_Point()+(getxVel()*getDir()));
		if(getDamage()>0){
			setDamage(getDamage()-getDamageLoss());
		}else if(getDamage()<0){
			setDamage(0);
		}

	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.darkGray);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

	public int getDamageLoss() {
		return damageLoss;
	}

	public void setDamageLoss(int damageLoss) {
		this.damageLoss = damageLoss;
	}
	
	
	
}
