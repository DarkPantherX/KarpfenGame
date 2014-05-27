package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Level.Level;
import ch.ilikechickenwings.karpfengame.Skill.SkillSelection;
import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Item.Coffee;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Mob;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Player extends Entity{

	private boolean invincible=false;
	private long currTimeInvincible;
	private long oldTimeInvincible;
	private int state=1;
	private int dir; // -1 or 1
	private boolean walking=false;
	private int xVel;
	private int yVel;
	private boolean gravityOn;
	private int g=1;
	private int timeVar;
	private int timeVarPosi;
	private boolean obstructedVision;
	
	private int jumpVel=-15;
	private int xAbsVel=4;
	
	// coffee:
	private int coffee;
	private boolean flying;
	
	private Timer timer;
	private SkillSelection skillselection;

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
		//setEnableSkill(useableSkill);
		setGravityOn(true);
		setDir(1);
		
		skillselection = new SkillSelection(this,useableSkill);
		//super(x, y, lifes);
		// TODO Auto-generated constructor stub
	}


	public void update(InputHandler inHandler) {
		
		// Skill
		skillselection.update(inHandler);
		
		setWalking(false);
			// movement
			if ((inHandler.getKeys()[KeyEvent.VK_W]||inHandler.getKeys()[KeyEvent.VK_UP]||inHandler.getKeys()[KeyEvent.VK_SPACE])&&!isGravityOn()) { // 
				setGravityOn(true);
				setyVel(getJumpVel()); 
				setState(3);
				setWalking(false);
				timeVar=0;
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
			
			if(getY_Point()<0){setY_Point(0);} // makes it impossible to fly out of the field of view
			
	}

	
	public void draw(Graphics2D g2,int xOffset, int yOffset){
		g2.setColor(Color.black);
		//coffe shouldn't be above the player
		
		
		// coffee
		g2.drawImage(Tile.coffeeBar,KarpfenGame.WIDTH-60,5+(int)(100-100*coffee/100),25,100*coffee/100,null);
		g2.drawImage(Tile.coffeeHolderBar,KarpfenGame.WIDTH-60, 5, 25, 100,null);
		// lifes
		// TODO: Load life-image
		g2.drawImage(Tile.lifeBar,KarpfenGame.WIDTH-30,5+(int)(100-100*getLifes()/100),25,100*getLifes()/100,null);
		g2.drawImage(Tile.lifeHolderBar,KarpfenGame.WIDTH-30, 5, 25, 100,null);
		// player
		g2.drawImage(Tile.player[state-1][timeVar],getX_Point()-xOffset, getY_Point()+yOffset,null);
		//g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		if(invincible){
		g2.setColor(Color.blue);
		g2.drawRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		}
		
		skillselection.draw(g2);
	}
	
	
	
	private void walk(){
		setX_Point(getX_Point() + getxVel());
		
		if(isWalking()){
		if(timer==null){
			timer= new Timer(75);
		}
		
		
		if(timer.isReady()){
			
			if(timeVar==0){
			
				timeVarPosi=1;
			}else if(timeVar==3){

				timeVarPosi=-1;

			}
			timeVar+=timeVarPosi;
			timer= new Timer(75);
		}
		}
		
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
