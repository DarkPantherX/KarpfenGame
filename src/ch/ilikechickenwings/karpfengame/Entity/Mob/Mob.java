package ch.ilikechickenwings.karpfengame.Entity.Mob;

import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Mob extends Entity{
	
	private int damage;
	private int xVel;
	private int yVel;
	private boolean gravityOn;
	private int g=1; // actually 9.81 m/s^-2
	
	public Mob(){
		
		
	}


	public void getDamaged(Projectile pr){
		setLifes(getLifes()-pr.getDamage());
	}
	
	public void updateGravity(){
		setyVel(getyVel()+g);
	}
	
	
	
	public boolean isGravityOn() {
		return gravityOn;
	}


	public void setGravityOn(boolean gravityOn) {
		this.gravityOn = gravityOn;
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
