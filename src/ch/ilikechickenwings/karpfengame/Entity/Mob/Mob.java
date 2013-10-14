package ch.ilikechickenwings.karpfengame.Entity.Mob;

import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;

public class Mob extends Entity{
	
	private int damage;
	
	public Mob(){
		
		
	}

	public void getDamaged(Projectile pr){
		setLifes(getLifes()-pr.getDamage());
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	

}
