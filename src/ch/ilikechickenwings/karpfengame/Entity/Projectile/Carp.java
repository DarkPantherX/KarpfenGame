package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Carp extends Projectile{
	
	int damageLoss; // damageloss per gametick
	private int timeVar;
	private int timeVarPosi;
	private Timer timer;
	
	public Carp(int x, int y, int dir){
		setX_Point(x);
		setY_Point(y);
		setWidth(10);
		setHeight(10);
		setxVel(10);
		setyVel(0);
		setDir(dir);
		setDamage(50);
		setDamageLoss(1);
		setGravityOn(false); // or shall this be changed?
	}
	
	public void update(InputHandler inHandler){
		setX_Point(getX_Point()+(getxVel()*getDir()));
		if(getDamage()>0){
			setDamage(getDamage()-getDamageLoss());
		}else if(getDamage()<0){
			setDamage(0);
		}

		if(timer==null){
			timer= new Timer(200);
		}
		
		
		if(timer.isReady()){
			
			if(timeVar==0){
			
				timeVarPosi=1;
			}else if(timeVar==1){

				timeVarPosi=-1;

			}
			timeVar+=timeVarPosi;
			timer= new Timer(200);
		}
		
		
	}

	public void draw(Graphics2D g2,int xOffset, int yOffset){
		g2.drawImage(Tile.carp[Math.min(1,Math.abs(getDir()-1))][timeVar],getX_Point()-xOffset, getY_Point()+yOffset,getWidth(),getHeight(),null);
		
		
		
	}

	public int getDamageLoss() {
		return damageLoss;
	}

	public void setDamageLoss(int damageLoss) {
		this.damageLoss = damageLoss;
	}
	
	
	
}
