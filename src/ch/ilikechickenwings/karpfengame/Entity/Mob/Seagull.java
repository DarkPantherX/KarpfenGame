package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Drop;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Seagull extends Mob{
	
	private int cooldown; // in millisecs
	private long oldTimeDrop;
	private static Seagull lastSeagull=null;


	public Seagull(int x){
		setWidth(30);
		setHeight(30);
		setX_Point(x);
		setY_Point(0);
		setLifes(100);
		setxVel(2);
		setyVel(0);
		setDamage(10);
		Random r=new Random();
		oldTimeDrop=System.currentTimeMillis()-r.nextInt(5000);
		setCooldown(5000);
		setGravityOn(false); // this might be changed for a more realistic fly
	}

    public void update(InputHandler inHandler) {
		setX_Point(getX_Point()-getxVel());
		if(System.currentTimeMillis()>oldTimeDrop+cooldown){
			oldTimeDrop=System.currentTimeMillis();
			Drop drop=new Drop(getX_Point(),getY_Point()+getHeight(),getxVel(),getxVel());
		    Level.getEntities().add(drop);
		}
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
	
	
	
}
