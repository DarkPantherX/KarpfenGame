package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Player;


public class Projectile extends Entity {
	
	private int damage;
	private int xVel;
	private int yVel;
	private int dir;
	private boolean gravityOn;
	private int g=1; 
	private Player player;
	
	public Projectile(){
		setLifes(2);
		
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

	/**
	 * @return the dir
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	
	
}
