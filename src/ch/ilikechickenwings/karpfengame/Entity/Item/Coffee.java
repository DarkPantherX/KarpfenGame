package ch.ilikechickenwings.karpfengame.Entity.Item;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import ch.ilikechickenwings.karpfengame.Entity.Item.Item;

public class Coffee extends Item{
	
	private int caffeine;
	
	public Coffee(int x, int y){
		setWidth(30);
		setHeight(30);
		setX_Point(x-(getWidth()/2));
		setY_Point(y-getHeight());
		
		setCaffeine(100); // may has to be changed
	}

	public void draw(Graphics2D g2, int xOffset){
		
		Image imge1 = null;
		imge1 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/java-logo[1].gif")); // not declared to use for commercial uses..
	    g2.drawImage(imge1,getX_Point()-xOffset,getY_Point(), getWidth(), getHeight(),null);
		
	}

	public int getCaffeine() {
		return caffeine;
	}

	public void setCaffeine(int caffeine) {
		this.caffeine = caffeine;
	}
	
	
	
}
