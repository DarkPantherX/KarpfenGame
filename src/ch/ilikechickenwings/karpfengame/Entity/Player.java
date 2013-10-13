package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Entity.Entity;

public class Player extends Entity{

	private boolean falling=true;
	private boolean jumping=false;
	private int gravity=10;
	private int jumped=0;
	private boolean invincible=false;
	private int invincibleCount=0;
	private long currTime=0;
	private long oldTime=0;
	private int dir=0;
	
	// coffee:
	private double coffee;
	private double cReduceWalk=0.2; // the amount of coffee that is lost per jump
	private double cReduceJump=2.0; // the amount of coffee that is lost per update whilst one is walking.
	
	public Player(int x, int y, int lifes, int vel) {
		
		setX_Point(x);
		setY_Point(y);
		setLifes(lifes);
		setWidth(30);
		setHeight(70);
		setVelocity(vel);
		setCoffee(100);
		setInvincible(false);
		//super(x, y, lifes);
		// TODO Auto-generated constructor stub
	}


	public void update(InputHandler inHandler) {
		
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
			
			
			//Added Cooldown for damage
			if(invincible){
			currTime=System.currentTimeMillis();
				if(invincibleCount==0){
				invincibleCount++;
				oldTime=currTime;
			}else if(currTime>oldTime+1000){
				invincibleCount=0;
				setInvincible(false);
			}
				
			}


		if(falling){
			setY_Point(getY_Point()+gravity);
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
	
	public void getDamaged(Entity ent){
		if(!invincible){
		setLifes(getLifes()-ent.getDamage());
		setInvincible(true);
		}
	}
	
	public void getHealed(Entity ent) {
		setLifes(getLifes()+ent.getDamage());
		
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
