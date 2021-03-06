package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Drop extends Projectile{
	
	public Drop(int x, int y, int xvel, int yvel){
		setWidth(10);
		setHeight(10);
		setX_Point(x);
		setY_Point(y);
		setxVel(xvel);
		setyVel(yvel);
		setDamage(10);
		setGravityOn(true);
	}
	
	public void update(InputHandler inHandler){
		if(isGravityOn()){
			updateGravity();
		}
		setX_Point(getX_Point()-getxVel());
		setY_Point(getY_Point()+getyVel());
	}
	
	public void draw(Graphics2D g2,int xOffset, int yOffset){
		g2.setColor(Color.white);
		g2.drawImage(Tile.drop,getX_Point()-xOffset, getY_Point()+yOffset, getWidth(), getHeight(),null);
	}
}
