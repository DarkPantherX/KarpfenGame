package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Skill.Skill;
import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Item.Coffee;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Mob;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Carp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Player extends Entity{

	private boolean falling=true;
	private boolean jumping=false;
	private int gravity=10;
	private int jumped=0;
	private boolean invincible=false;
	private long currTimeInvincible;
	private long oldTimeInvincible;
	private long[] currTimeSkill= new long[Skill.nr];
	private long[] oldTimeSkill= new long[Skill.nr];
	private int dir=0;
	private boolean[] enableSkill;
	
	// coffee:
	private double coffee;
	private double cReduceWalk=0.2; // the amount of coffee that is lost per jump
	private double cReduceJump=2.0; // the amount of coffee that is lost per update whilst one is walking.
	
	public Player(int x, int y, int lifes, int vel,int coffee, boolean[] enableSkill) {
		
		setX_Point(x);
		setY_Point(y);
		setLifes(lifes);
		setWidth(30);
		setHeight(70);
		setVelocity(vel);
		setCoffee(coffee);
		setInvincible(false);
		setEnableSkill(enableSkill);
		//super(x, y, lifes);
		// TODO Auto-generated constructor stub
	}


	public void update(InputHandler inHandler) {
		
			// movement
			if ((inHandler.getKeys()[KeyEvent.VK_W]||inHandler.getKeys()[KeyEvent.VK_UP]||inHandler.getKeys()[KeyEvent.VK_SPACE])&&!jumping&&!falling) { // 
				jumping=true;
				falling=false;
				setDir(3);
				if(!(coffee<=0)){
					coffee-=cReduceJump;
				}
			}
			
			jump();

			if (inHandler.getKeys()[KeyEvent.VK_A]||inHandler.getKeys()[KeyEvent.VK_LEFT]) {
				walk(-getVelocity());
				setDir(2);
			}
			if (inHandler.getKeys()[KeyEvent.VK_D]||inHandler.getKeys()[KeyEvent.VK_RIGHT]) {
				walk(getVelocity());
				setDir(1);
			}
			  if(falling){
			     setY_Point(getY_Point()+gravity);
		    }
			
			//Added Cooldown for damage
			if(invincible){
			    currTimeInvincible=System.currentTimeMillis();
				if(currTimeInvincible>oldTimeInvincible+1000){ // 1 sec
				    oldTimeInvincible=System.currentTimeMillis();
				    setInvincible(false);
			    }	
			}

			// Skills:
			if(currTimeSkill[0]>=oldTimeSkill[0]+500){
				enableSkill[0]=true;
			}else{
				currTimeSkill[0]=System.currentTimeMillis();
			}
			if(inHandler.getKeys()[KeyEvent.VK_1]&&enableSkill[0]){ // Carp
				Skill skill=(Skill) Level.getSkills()[0];
				if(getCoffee()>=skill.getCoffee()){
					setCoffee(getCoffee()-skill.getCoffee());
					enableSkill[0]=false;
					oldTimeSkill[0]=System.currentTimeMillis();
					Carp carp=new Carp(getX_Point()+getWidth(),getY_Point()+getHeight()/2);
				    Level.getEntities().add(carp);
				}
				
			}
		
		
	}

	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.black);
		//coffe shouldn't be above the player
		
		Image imge1 = null;
			imge1 = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/res/loadingbar.png"));
		// coffee
		g2.drawImage(imge1,5, 5, 150, 30,null);
		g2.fillRect(5+(int)(150*coffee/100), 5,150,30);
		// lifes
		// TODO: Load life-image
		g2.drawImage(imge1,160, 5, 150, 30,null);
		g2.fillRect(160+(int)(150*getLifes()/100), 5,150,30);
		
		// player
		g2.setColor(Color.green);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		if(invincible){
		g2.setColor(Color.blue);
		g2.drawRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		}
	}
	
	private void walk(int i){
		setX_Point(getX_Point() + i);
		
		if(!(coffee<=0)){
			coffee-=cReduceWalk;
		}
		
	}
	
	public void getDamaged(Mob mob){
		if(!invincible){
		setLifes(getLifes()-mob.getDamage());
		setInvincible(true);
		}
	}
	public void getDamaged(Projectile pro){
		if(!invincible){
		setLifes(getLifes()-pro.getDamage());
		setInvincible(true);
		}
	}
	
	public void getHealed(HealthPack hp) {
		setLifes(getLifes()+hp.getHealth());
		
	}
	public void getCaffeined(Coffee co){
		setCoffee(getCoffee()+co.getCaffeine());
	}
	
	
	private void jump() {
		if(jumping==true){
		jumped++;
		if(jumped>9){
			falling=true;
			jumping=false;
			jumped=0;
		}
		setY_Point(getY_Point()-10);
		}
	}
	

	
	
	public boolean[] getEnableSkill() {
		return enableSkill;
	}


	public void setEnableSkill(boolean[] enableSkill) {
		this.enableSkill = enableSkill;
	}


	public boolean isInvincible(){
		return invincible;
	}
	
	public void setInvincible(boolean b) {
		this.invincible=b;
		
	}
	

	public int getCoffee() {
		return (int)coffee;
	}

	public void setCoffee(int coffee) {
		this.coffee = (double)coffee;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
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

}
