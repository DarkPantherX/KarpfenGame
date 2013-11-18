package ch.ilikechickenwings.karpfengame.Entity;


import java.awt.Graphics2D;
import java.awt.Image;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class VisionObstructer extends Entity{
	
	
	private long shitCoolDown;
	private boolean firstTimeShit=true;
	private Image img;
	
	public VisionObstructer(int x, int y, Image img){
		setLifes(1);
		setX_Point(x);
		setY_Point(y);
		this.img=img;
	}
	
	
	public void update(InputHandler inhandler){
		long currTime = System.currentTimeMillis();

		if (firstTimeShit) {
			shitCoolDown = currTime;
			firstTimeShit = false;

		} else if (shitCoolDown + 3000 < currTime) {
			firstTimeShit = true;
			Level.entities.remove(this);
		}
			}
		
	
	
	public void draw(Graphics2D g, int xOffset) {
		g.drawImage(img, getX_Point()-xOffset, getY_Point(),250,250, null);
	}

}
