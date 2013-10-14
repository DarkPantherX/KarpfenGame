package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import ch.ilikechickenwings.karpfengame.Entity.Entity;


public class Projectile extends Entity {
	
	private int damage;
	private int xVel;
	private int yVel;
	
	
	public Projectile(){
		
	}

	
	
	
	
	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
	
}
