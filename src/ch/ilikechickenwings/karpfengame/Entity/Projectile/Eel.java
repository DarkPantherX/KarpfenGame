package ch.ilikechickenwings.karpfengame.Entity.Projectile;


import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Eel extends Projectile{

	private Timer lifeTimer;
	private int intervall=75;
	private int lifeCounter=0;
	
	public Eel(int x, int y, Player player){
		setDir(player.getDir());
		setX_Point(x);
		setY_Point(y);	
		setWidth(20);
		setHeight(20);
		setxVel(10);
		setyVel(0);
		setDamage(100);
		setLifeTimer(new Timer(getIntervall()));
		setPlayer(player);
		setGravityOn(false);
	}
	
	public void update(InputHandler inHandler){
		
		if(getLifeTimer().isReady()){
			if(lifeCounter<2){
				setWidth(getWidth()+15);
				lifeCounter++;
			setLifeTimer(new Timer(getIntervall()));
		}else{
			Level.entities.remove(this);
			}
		}
		setX_Point(getPlayer().getX_Point()+
					(getPlayer().getWidth()*Math.max(0, getPlayer().getDir()))+
					(getWidth()*Math.min(0, getPlayer().getDir())));
		
		setY_Point(getPlayer().getY_Point()+getHeight()/2);
		
		
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.drawImage(Tile.eel[Math.min(1,Math.abs(getDir()-1))][lifeCounter],getX_Point()-xOffset, getY_Point(),getWidth(),getHeight(),null);
		
	}

	public Timer getLifeTimer() {
		return lifeTimer;
	}

	public void setLifeTimer(Timer lifeTimer) {
		this.lifeTimer = lifeTimer;
	}

	public int getIntervall() {
		return intervall;
	}

	public void setIntervall(int lifeTimerWait) {
		this.intervall = lifeTimerWait;
	}



}
