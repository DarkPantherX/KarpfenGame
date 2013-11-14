package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class GiantCarp extends Projectile {
	
	public GiantCarp (){
		setWidth(10);
		setHeight(KarpfenGame.HEIGHT);
		setX_Point(Level.getxOffset()-getWidth());
		setY_Point(0);
		setxVel(10);
		setyVel(0);
		setDamage(1000);
		setGravityOn(false); 
	}
	
	
	public void update(InputHandler inHandler){
		setX_Point(getX_Point()+getxVel());
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.white);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
		
	}

	
}
