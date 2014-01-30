package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class FlyingCarp extends Projectile{
	
	private Timer flyingTimer;
	private int flyingTimerWait=1200;
	int updates = 30;
	
	public FlyingCarp(Player player){
		setWidth(10);
		setHeight(KarpfenGame.HEIGHT);
		setX_Point(player.getX_Point()+player.getWidth()/2);
		setY_Point(player.getY_Point()+1);
		setxVel(0);
		setyVel(10);
		setDamage(10);
		setGravityOn(false);
		setPlayer(player);
		setFlyingTimer(new Timer(getFlyingTimerWait()));
	}
	
	public void update(InputHandler inhandler){
		if(getFlyingTimer().isReady()){
			getPlayer().setGravityOn(true);
			Level.getEntities().remove(this);
			getPlayer().setFlying(false);
		}else{
			getPlayer().setY_Point(getPlayer().getY_Point()-2);
			getPlayer().setFlying(true);
			updates--;
		}
		/*
		if(updates>0){
		getPlayer().setY_Point(getPlayer().getY_Point()-2);
		getPlayer().setFlying(true);
		updates--;
		}else{
			getPlayer().setGravityOn(true);
			Level.getEntities().remove(this);
			getPlayer().setFlying(false);
			
		}
		*/
	}
	
	public void draw(Graphics2D g,int xOffset) {
		g.setColor(Color.cyan);
		g.drawRect(getPlayer().getX_Point()-xOffset - 10, getPlayer().getY_Point() + 1,
				20 + getPlayer().getHeight(), 20);

	}

	public Timer getFlyingTimer() {
		return flyingTimer;
	}

	public void setFlyingTimer(Timer flyingTimer) {
		this.flyingTimer = flyingTimer;
	}

	public int getFlyingTimerWait() {
		return flyingTimerWait;
	}

	public void setFlyingTimerWait(int flyingTimerWait) {
		this.flyingTimerWait = flyingTimerWait;
	}
	
	

}
