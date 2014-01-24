package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Entity.Player;

public class FlyingCarp extends Projectile{
	
	public FlyingCarp(Player player){
		setWidth(10);
		setHeight(KarpfenGame.HEIGHT);
		setX_Point(player.getX_Point()+player.getWidth()/2);
		setY_Point(player.getY_Point()+1);
		setxVel(0);
		setyVel(10);
		setDamage(10);
		setGravityOn(false);
		setPlayer(player);
		
		
		
	}

}
