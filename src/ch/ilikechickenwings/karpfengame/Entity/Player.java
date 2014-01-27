package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Skill.Skill;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;
import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Item.Coffee;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Mob;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Carp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Eel;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.FlyingCarp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.GiantCarp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Grenade;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Player extends Entity{

	private boolean invincible=false;
	private long currTimeInvincible;
	private long oldTimeInvincible;
	private long[] currTimeSkill= new long[Skill.getNr()];
	private long[] oldTimeSkill= new long[Skill.getNr()];
	private int state=1;
	private int dir; // -1 or 1
	private boolean walking=false;
	private boolean[] enableSkill;
	private int enabledSkill=0;
	private int xVel;
	private int yVel;
	private boolean gravityOn;
	private int g=1;
	private int timeVar;
	private long oldTime;
	private boolean obstructedVision;
	
	private int jumpVel=-15;
	private int xAbsVel=4;
	
	// coffee:
	private int coffee;
	private boolean flying;

	public Player(int x, int y, int lifes, int coffee, boolean[] useableSkill) {
		
		setX_Point(x);
		setY_Point(y);
		setLifes(lifes);
		setWidth(20);
		setHeight(40);
		setxVel(0);
		setyVel(0);
		setCoffee(coffee);
		setInvincible(false);
		setEnableSkill(useableSkill);
		setGravityOn(true);
		setDir(1);
		//super(x, y, lifes);
		// TODO Auto-generated constructor stub
	}


	public void update(InputHandler inHandler) {
		long currTime=System.currentTimeMillis();
		if(isWalking()){
		if(oldTime+200<currTime){
			if(timeVar==0){
				timeVar=1;
			}else{
				timeVar=0;
			}
			oldTime=currTime;
		}
		
		}
		
	
		
		//Skill
		for(int i=0;i<Skill.getNr();i++){
		    if(currTimeSkill[i]>=oldTimeSkill[i]+500){
			    enableSkill[i]=true;
		    }else{
			    currTimeSkill[i]=System.currentTimeMillis();
		    }
		}
		
		if(inHandler.getKeys()[KeyEvent.VK_1]){ // Carp
			enabledSkill=0;
		}else if(inHandler.getKeys()[KeyEvent.VK_2]){ // Eel
			enabledSkill=1;
		}else if(inHandler.getKeys()[KeyEvent.VK_3]){ // Karpocalypse
			enabledSkill=2;
		}else if(inHandler.getKeys()[KeyEvent.VK_4]){ // FlyingCarp
			enabledSkill=3;
		}else if(inHandler.getKeys()[KeyEvent.VK_5]){ // Grenade
			enabledSkill=4;
		}
		
		
		for(int i=0;i<Skill.getNr();i++){
			//The problem lies here... i debugged the game and the forbiddenskill always jumps back to true!
			if(inHandler.getKeys()[KeyEvent.VK_S]&&enableSkill[i]&&Level.useableSkill[i]&&enabledSkill==i){ // Carp
				Skill skill=(Skill) Level.getSkills()[i];
				if(getCoffee()>=skill.getCoffee()){
					setCoffee(getCoffee()-skill.getCoffee());
					enableSkill[i]=false;
					oldTimeSkill[i]=System.currentTimeMillis();
					switch(i){
					    case 0:
						SoundSystem.playSound(Sounds.playerShot);
					    Carp carp=new Carp(getX_Point()+(getWidth()*Math.max(0,getDir())),getY_Point()+getHeight()/2,getDir());
					    Level.getEntities().add(carp);
					    break;
					    case 1:
					    Eel eel=new Eel(getX_Point()+getWidth(),getY_Point()+getHeight()/2,this);
					    Level.getEntities().add(eel);
					    break;
					    case 2:
					    GiantCarp gc=new GiantCarp(getDir());
					    Level.getEntities().add(gc);
					    break;
					    case 3:
						FlyingCarp fc=new FlyingCarp(this);
						Level.getEntities().add(fc);
					    break;
					    case 4:
					    Grenade gr=new Grenade(getX_Point()+getWidth()/2,getY_Point(),getDir());
					    Level.getEntities().add(gr);
					    break;
					}
				}
			}
		}

		setWalking(false);
			// movement
			if ((inHandler.getKeys()[KeyEvent.VK_W]||inHandler.getKeys()[KeyEvent.VK_UP]||inHandler.getKeys()[KeyEvent.VK_SPACE])&&!isGravityOn()) { // 
				setGravityOn(true);
				setyVel(getJumpVel()); 
				setState(3);
				setWalking(true);
			}

			if (inHandler.getKeys()[KeyEvent.VK_A]||inHandler.getKeys()[KeyEvent.VK_LEFT]) {
				setxVel(-getxAbsVel());
				setState(2);
				setDir(-1);
				setWalking(true);
			}
			if (inHandler.getKeys()[KeyEvent.VK_D]||inHandler.getKeys()[KeyEvent.VK_RIGHT]) {
				setxVel(getxAbsVel()); 
				setState(1);
				setDir(1);
				setWalking(true);
			}
			if(!(inHandler.getKeys()[KeyEvent.VK_A]||inHandler.getKeys()[KeyEvent.VK_LEFT]||inHandler.getKeys()[KeyEvent.VK_D]||inHandler.getKeys()[KeyEvent.VK_RIGHT])){
				setxVel(0);
			}
			if(isGravityOn()){
				setyVel(getyVel()+g);
		    }
			
			walk();
			
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
		g2.drawImage(Tile.player[state-1][timeVar],getX_Point()-xOffset, getY_Point(),null);
		//g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		if(invincible){
		g2.setColor(Color.blue);
		g2.drawRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		}
	}
	
	
	
	private void walk(){
		setX_Point(getX_Point() + getxVel());
		
		if(!flying){
			setY_Point(getY_Point()+getyVel());
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
	
	public void updateGravity(){
		setyVel(getyVel()+g);
	}
	
	// Getters and Setters:
	
	public boolean isGravityOn() {
		return gravityOn;
	}


	public int getJumpVel() {
		return jumpVel;
	}


	public void setJumpVel(int jumpVel) {
		this.jumpVel = jumpVel;
	}


	public int getxAbsVel() {
		return xAbsVel;
	}


	public void setxAbsVel(int xAbsVel) {
		this.xAbsVel = xAbsVel;
	}


	public void setGravityOn(boolean gravityOn) {
		this.gravityOn = gravityOn;
		setyVel(0);
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


	public void setEnableSkill(boolean[] useableSkill) {
		this.enableSkill = useableSkill.clone();
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

	/**
	 * @return the dir
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setState(int dir) {
		this.state = dir;
	}


	/**
	 * @return the walking
	 */
	public boolean isWalking() {
		return walking;
	}


	/**
	 * @param walking the walking to set
	 */
	public void setWalking(boolean walking) {
		this.walking = walking;
	}


	/**
	 * @return the obstrucetedVision
	 */
	public boolean hasObstructedVision() {
		return obstructedVision;
	}


	/**
	 * @param obstrucetedVision the obstrucetedVision to set
	 */
	public void setObstructedVision(boolean obstructedVision) {
		this.obstructedVision = obstructedVision;
	}


	/**
	 * @return the dir
	 */
	public int getDir() {
		return dir;
	}


	/**
	 * @param i the dir to set
	 */
	public void setDir(int i) {
		this.dir = i;
	}


	public void setFlying(boolean b) {
		this.flying=b;
		
	}

	public boolean isFlying() {
		return flying;
		
	}
}
