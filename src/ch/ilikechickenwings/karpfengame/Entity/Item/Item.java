package ch.ilikechickenwings.karpfengame.Entity.Item;

import ch.ilikechickenwings.karpfengame.Entity.Entity;

public class Item extends Entity{
	
	private boolean dropable;
	
	public Item(){
		
		setLifes(1);
		
	}

	/**
	 * @return the dropable
	 */
	public boolean isDropable() {
		return dropable;
	}

	/**
	 * @param dropable the dropable to set
	 */
	public void setDropable(boolean dropable) {
		this.dropable = dropable;
	}

}
