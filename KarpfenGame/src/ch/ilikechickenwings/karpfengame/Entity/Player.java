package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Player extends Entity{

	private boolean falling=true;
	private boolean jumping=false;
	private int gravity=10;
	private int jumped=0;
	private int x=0;
	
	public Player(int x, int y, int lifes, int width, int height) {
		super(x, y, lifes, width, height);
		// TODO Auto-generated constructor stub
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

	public void update(InputHandler inHandler) {
		
			if ((inHandler.getKeys()[KeyEvent.VK_W]||inHandler.getKeys()[KeyEvent.VK_UP]||inHandler.getKeys()[KeyEvent.VK_SPACE])&&!jumping&&!falling) { // 
				jumping=true;
				falling=false;
			}
			
			jump();

			if (inHandler.getKeys()[KeyEvent.VK_A]) {
				setX_Point(getX_Point()-4);
				
			}
			if (inHandler.getKeys()[KeyEvent.VK_D]) {
				setX_Point(getX_Point()+4);

			}

		/* moved to Level.java - SC
		for (int w = 0; w < KarpfenGame.walls.size(); w++) { // TODO: This collision is checked in Level.java
			Wall wall = (Wall) KarpfenGame.walls.get(w);
		if(getX_Point()<wall.getX_Point()+wall.getWidth()&&getX_Point()+30>wall.getX_Point()){
			if(wall.getY_Point()<getY_Point()+getHeight() && !(wall.getY_Point()+10<getY_Point()+70)){
				x=1;
			}else{
				x=0;}
				
			}
		}
		
		if(x==1){
			falling=false;
			jumping=false;
			x=0;
		}else if (!jumping){
			falling=true;
		}
		x=0;
		*/ 
			
		if(falling){
			setY_Point(getY_Point()+gravity);
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
	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.green);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

}
