package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Eel extends Projectile{

	private Timer lifeTimer;
	private int lifeTimerWait;
	
	public Eel(int x, int y, Player player){
		setDir(player.getDir());
		setX_Point(x);
		setY_Point(y);	
		setWidth(50);
		setHeight(20);
		setxVel(10);
		setyVel(0);
		setDamage(100);
		setLifeTimer(new Timer(getLifeTimerWait()));
		setPlayer(player);
		setGravityOn(false);
	}
	
	public void update(InputHandler inHandler){
		setX_Point(getPlayer().getX_Point()+
					(getPlayer().getWidth()*Math.max(0, getPlayer().getDir()))+
					(getWidth()*Math.min(0, getPlayer().getDir())));
		
		setY_Point(getPlayer().getY_Point()+getHeight()/2);
		
		if(getLifeTimer().isReady()){
			Level.entities.remove(this);
		}
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.white);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

	public Timer getLifeTimer() {
		return lifeTimer;
	}

	public void setLifeTimer(Timer lifeTimer) {
		this.lifeTimer = lifeTimer;
	}

	public int getLifeTimerWait() {
		return lifeTimerWait;
	}

	public void setLifeTimerWait(int lifeTimerWait) {
		this.lifeTimerWait = lifeTimerWait;
	}



}
