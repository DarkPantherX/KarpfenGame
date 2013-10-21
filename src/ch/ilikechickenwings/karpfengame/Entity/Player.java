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
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Eel;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Player extends Entity{

	private boolean falling=true;
	private boolean jumping=false;
	private int gravity=10;
	private int jumped=0;
	private boolean invincible=false;
	private long currTimeInvincible;
	private long oldTimeInvincible;
	private long[] currTimeSkill= new long[Skill.getNr()];
	private long[] oldTimeSkill= new long[Skill.getNr()];
	private int dir=0;
	private boolean[] enableSkill;
	private int enabledSkill=1;
	private int xVel;
	private int yVel;
	
	// coffee:
	private int coffee;

	public Player(int x, int y, int lifes, int coffee, boolean[] enableSkill) {
		
		setX_Point(x);
		setY_Point(y);
		setLifes(lifes);
		setWidth(30);
		setHeight(70);
		setxVel(4);
		setyVel(0);
		setCoffee(coffee);
		setInvincible(false);
		setEnableSkill(enableSkill);
		//super(x, y, lifes);
		// TODO Auto-generated constructor stub
	}


	public void update(InputHandler inHandler) {
		
		//Skill
		if(currTimeSkill[0]>=oldTimeSkill[0]+500){
			enableSkill[0]=true;
		}else{
			currTimeSkill[0]=System.currentTimeMillis();
		}
		if(currTimeSkill[1]>=oldTimeSkill[0]+4000){
			enableSkill[1]=true;
		}else{
			currTimeSkill[1]=System.currentTimeMillis();
		}
		
		if(inHandler.getKeys()[KeyEvent.VK_K]&&enableSkill[0]&&enabledSkill==1){ // Carp
			Skill skill=(Skill) Level.getSkills()[0];
			if(getCoffee()>=skill.getCoffee()){
				setCoffee(getCoffee()-skill.getCoffee());
				enableSkill[0]=false;
				oldTimeSkill[0]=System.currentTimeMillis();
				Carp carp=new Carp(getX_Point()+getWidth(),getY_Point()+getHeight()/2);
			    Level.getEntities().add(carp);
			}
			
		}
		
		if(inHandler.getKeys()[KeyEvent.VK_K]&&enableSkill[1]&&enabledSkill==2){ // Carp
			Skill skill=(Skill) Level.getSkills()[1];
			if(getCoffee()>=skill.getCoffee()){
				setCoffee(getCoffee()-skill.getCoffee());
				enableSkill[1]=false;
				oldTimeSkill[1]=System.currentTimeMillis();
				Eel eel=new Eel(getX_Point()+getWidth(),getY_Point()+getHeight()/2,this);
			    Level.getEntities().add(eel);
			}
			
		}
		
		
		if(inHandler.getKeys()[KeyEvent.VK_1]){ // Carp
			enabledSkill=1;
		}else if(inHandler.getKeys()[KeyEvent.VK_2]){ // Carp
				enabledSkill=2;
				}
			
		
		
		
			// movement
			if ((inHandler.getKeys()[KeyEvent.VK_W]||inHandler.getKeys()[KeyEvent.VK_UP]||inHandler.getKeys()[KeyEvent.VK_SPACE])&&!jumping&&!falling) { // 
				jumping=true;
				falling=false;
				setDir(3);
			}
			
			jump();

			if (inHandler.getKeys()[KeyEvent.VK_A]||inHandler.getKeys()[KeyEvent.VK_LEFT]) {
				walk(-getxVel());
				setDir(2);
			}
			if (inHandler.getKeys()[KeyEvent.VK_D]||inHandler.getKeys()[KeyEvent.VK_RIGHT]) {
				walk(getxVel());
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
		if(getLifes()>Level.getMaxLife()){
			setLifes(Level.getMaxLife());// this is totally static and should totally not be static
		}
		
	}
	public void getCaffeined(Coffee co){
		setCoffee(getCoffee()+co.getCaffeine());
		if(getCoffee()>Level.getMaxCoffee()){
			setCoffee(Level.getMaxCoffee()); // this is totally static and should totally not be static
		}
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
	

	
	
	public int getxVel() {
		return xVel;
	}


	public void setxVel(int xVel) {
		this.xVel = xVel;
	}


	public int getyVel() {
		return yVel;
	}


	public void setyVel(int yVel) {
		this.yVel = yVel;
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
		return coffee;
	}

	public void setCoffee(int coffee) {
		this.coffee = coffee;
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
