package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Drop;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Seagull extends Mob{
	
	private Timer dropTimer;
	private int dropTimerWait = 2000;  // in millisecs
	private int cooldown; // in millisecs
	private long oldTimeDrop;
	private static Seagull lastSeagull=null;
	
	private int upAc=4; // for realistic flying, upwards acceleration, > 1, high number=small difference


	public Seagull(int x){
		Random r=new Random();
		setWidth(30);
		setHeight(30);
		setX_Point(x);
		setY_Point(0);
		setLifes(100);
		setxVel(1+r.nextInt(3));
		setyVel(0);
		setDamage(10);
		//oldTimeDrop=System.currentTimeMillis()-r.nextInt(5000);
		//setCooldown(2000);
		dropTimer=new Timer(r.nextInt(5000));
		setGravityOn(true); 
		
	}

    public void update(InputHandler inHandler) {
    	// movement
		setX_Point(getX_Point()-getxVel());
		setY_Point(getY_Point()+getyVel());
		if(isGravityOn()){
			updateGravity();
			if(getY_Point()>getHeight()){
		    setyVel(getyVel()-upAc);
		    }
		}
		
		// "droping"
		if(getDropTimer().isReady()){
			setDropTimer(new Timer(getDropTimerWait()));
			Drop drop=new Drop(getX_Point(),getY_Point()+getHeight(),getxVel(),getxVel());
		    Level.getEntities().add(drop);
		}
		/*
		if(System.currentTimeMillis()>oldTimeDrop+cooldown){
			oldTimeDrop=System.currentTimeMillis();
			Drop drop=new Drop(getX_Point(),getY_Point()+getHeight(),getxVel(),getxVel());
		    Level.getEntities().add(drop);
		}*/
	}
    
    
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.yellow);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
	}
	
		public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public static Seagull getLastSeagull() {
		return lastSeagull;
	}

	public static void setLastSeagull(Seagull lastSeagull) {
		Seagull.lastSeagull = lastSeagull;
	}

	public Timer getDropTimer() {
		return dropTimer;
	}

	public void setDropTimer(Timer dropTimer) {
		this.dropTimer = dropTimer;
	}

	public int getDropTimerWait() {
		return dropTimerWait;
	}

	public void setDropTimerWait(int dropTimerWait) {
		this.dropTimerWait = dropTimerWait;
	}
	
	
	
	
}
