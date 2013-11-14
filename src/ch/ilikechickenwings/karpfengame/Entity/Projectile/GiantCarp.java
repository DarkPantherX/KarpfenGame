package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import ch.ilikechickenwings.karpfengame.Level;

public class GiantCarp extends Projectile {
	
	public GiantCarp (){
		setWidth(10);
		setHeight(10);
		setX_Point(Level.xOffset-getWidth());
		setY_Point(0);
		setxVel(10);
		setyVel(0);
		setDamage(1000);
		setGravityOn(false); 
	}
	
	
}
