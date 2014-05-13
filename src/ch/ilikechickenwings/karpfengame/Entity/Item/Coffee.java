package ch.ilikechickenwings.karpfengame.Entity.Item;

import java.awt.Graphics2D;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Entity.Item.Item;

public class Coffee extends Item{
	
	private int caffeine;
	
	public Coffee(int x, int y){
		setWidth(15);
		setHeight(15);
		setX_Point(x-(getWidth()/2));
		setY_Point(y-getHeight());
		
		setCaffeine(100); // may has to be changed
	}

	public void draw(Graphics2D g2, int xOffset){
	    g2.drawImage(Tile.coffee,getX_Point()-xOffset,getY_Point(), getWidth(), getHeight(),null);
		
	}

	public int getCaffeine() {
		return caffeine;
	}

	public void setCaffeine(int caffeine) {
		this.caffeine = caffeine;
	}
	
	
	
}
