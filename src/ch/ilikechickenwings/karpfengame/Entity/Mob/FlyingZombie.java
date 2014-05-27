package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class FlyingZombie extends BossMob{
	
	private Player player;
	
	public FlyingZombie(int x, int y,Player player){
	setWidth(20);
	setHeight(40);
	setX_Point(x);
	setY_Point(y-getHeight());
	setLifes(100);
	setxVel(3);
	setyVel(0);
	setDamage(10);
	setGravityOn(false);
	this.player=player;
	}
	
	public void update(InputHandler inHandler) {

		
		int xP=player.getX_Point();
		int yP=player.getY_Point();

		
		if(xP+100<getX_Point()||xP-100>getX_Point()){

			if(xP>getX_Point()){
				setX_Point(getX_Point()+getxVel());
			}else{
				setX_Point(getX_Point()-getxVel());	
			}
			
			}
		else if(xP+100>getX_Point()||xP-100<getX_Point()){
				if(xP>getX_Point()){
					setX_Point(getX_Point()-getxVel());
				}else{
					setX_Point(getX_Point()+getxVel());	
				
		}
		}		
		if(yP+10<=getY_Point()||yP-10>=getY_Point()){
		if(yP>getY_Point()){
			setY_Point(getY_Point()+getxVel());
		}else if(yP<getY_Point()){
			setY_Point(getY_Point()-getxVel());	
		}
		}
		
	}
	
	public void draw(Graphics2D g2,int xOffset, int yOffset){
		g2.setColor(Color.blue);
		g2.fillRect(getX_Point()-xOffset, getY_Point()-yOffset, getWidth(), getHeight());
	}
}
