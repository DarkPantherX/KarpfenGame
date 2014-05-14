package ch.ilikechickenwings.karpfengame.Entity;


import java.awt.Graphics2D;
import java.awt.Image;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class VisionObstructer extends Entity{
	
	
	private Image img;
	private Timer timer;
	
	public VisionObstructer(int x, int y, Image img){
		setLifes(1);
		setX_Point(x);
		setY_Point(y);
		this.img=img;
		timer = new Timer(3000);
	}
	
	
	public void update(InputHandler inhandler){

		if (timer.isReady()) {
			Level.entities.remove(this);
			
		}
	}
		
	
	
	public void draw(Graphics2D g, int xOffset) {
		g.drawImage(img, getX_Point(), getY_Point(),250,250, null);
	}

}
