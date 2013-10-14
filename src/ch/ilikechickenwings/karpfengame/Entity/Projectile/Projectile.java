package ch.ilikechickenwings.karpfengame.Entity.Projectile;

import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class Projectile extends Entity {
	
	private int damage;
	
	public Projectile(){
		
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
	
}
