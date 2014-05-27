package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Drop;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Level.Level;

public class Seagull extends Mob{
	
	private Timer dropTimer;
	private int dropTimerWait = 2000;  // in millisecs
	private static Seagull lastSeagull=null;
	private int timeVarPosi;
	private int timeVar=0;
	private Timer timer;
	
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
		if(timer==null){
			timer= new Timer(100);
		}
		
		
		if(timer.isReady()){
			
			if(timeVar==0){
			
				timeVarPosi=1;
			}else if(timeVar==3){

				timeVarPosi=-1;

			}
			timeVar+=timeVarPosi;
			timer= new Timer(100);
		}

	}
    
    
	public void draw(Graphics2D g2,int xOffset, int yOffset){
		g2.setColor(Color.yellow);
		g2.drawImage(Tile.seagull[0][timeVar],getX_Point()-xOffset, getY_Point()+yOffset,getWidth()+10,getHeight()+10,null);
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
