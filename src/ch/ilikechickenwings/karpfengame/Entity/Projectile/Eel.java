package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Eel extends Projectile{

	private long lifeTime;
	private long lifeSpan;
	private Player player;
	
	public Eel(int x, int y, Player player){
		setX_Point(x);
		setY_Point(y);
		setWidth(50);
		setHeight(20);
		setxVel(10);
		setyVel(0);
		setDamage(100);
		setLifeTime(System.currentTimeMillis());
		setLifeSpan(2000l);
		setPlayer(player);
		setGravityOn(false);
	}
	
	public void update(InputHandler inHandler){
		setX_Point(player.getX_Point()+player.getWidth());
		setY_Point(player.getY_Point()+getHeight()/2);
		if((System.currentTimeMillis()-getLifeTime())>getLifeSpan()){
			Level.entities.remove(this);
		}
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.white);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

	/**
	 * @return the lifeTime
	 */
	public long getLifeTime() {
		return lifeTime;
	}

	/**
	 * @param lifeTime the lifeTime to set
	 */
	public void setLifeTime(long lifeTime) {
		this.lifeTime = lifeTime;
	}

	/**
	 * @return the lifeSpan
	 */
	public long getLifeSpan() {
		return lifeSpan;
	}

	/**
	 * @param lifeSpan the lifeSpan to set
	 */
	public void setLifeSpan(long lifeSpan) {
		this.lifeSpan = lifeSpan;
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
