package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Level.Level;

public class FlyingCarp extends Projectile{
	
	private Timer flyingTimer;
	private Timer waitingTimer;
	private int flyingTimerFly=2600; // millis
	private int flyingTimerWait=1200;
	private int yMargin=0;
	
	public FlyingCarp(Player player){
		setWidth(20);
		setHeight(20);
		setPlayer(player);
		setX_Point(player.getX_Point()+player.getWidth()/2);
		setY_Point(player.getY_Point()-40);
		setxVel(0);
		setyVel(10);
		setDamage(10);
		setGravityOn(false);
		setFlyingTimer(new Timer(flyingTimerFly));
		waitingTimer= new Timer(flyingTimerWait);
	}
	
	public void update(InputHandler inhandler){
	if(getFlyingTimer().isReady()){ // time's out :)
			getPlayer().setGravityOn(true);
			Level.getEntities().remove(this);
			getPlayer().setFlying(false);
		}else{
			if(waitingTimer.isReady()){
			getPlayer().setY_Point(getPlayer().getY_Point()-2);
			getPlayer().setFlying(true);
			setWidth(50);
			setHeight(50);
			yMargin=-50;
			}else{
				setWidth(getWidth()+1);
				setHeight(getHeight()+1);
				if(yMargin<50){
				yMargin+=-2;
				}
			}
		}
	setX_Point(getPlayer().getX_Point());
	setY_Point(getPlayer().getY_Point());
	}
	
	public void draw(Graphics2D g,int xOffset, int yOffset) {
		
		g.drawImage(Tile.baloonFish2,getX_Point()-xOffset - 10, getY_Point()+yOffset+yMargin,
				getWidth(), getHeight(), null);

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
