package ch.ilikechickenwings.karpfengame.Entity;


import java.awt.Graphics2D;
import java.awt.Image;

public class VisionObstructer extends Entity{
	
	private long shitCoolDown;
	private boolean firstTimeShit=true;
	private Image img;
	private Player player;
	
	public VisionObstructer(int x, int y, Image img){
		setX_Point(x);
		setY_Point(y);
		this.img=img;
	}
	
	
	public void update(){
		long currTime = System.currentTimeMillis();

		if (firstTimeShit) {
			shitCoolDown = currTime;
			firstTimeShit = false;

		} else if (shitCoolDown + 20000 < currTime) {
			player.setObstructedVision(false);
			firstTimeShit = true;
		}
			}
		
	
	
	public void draw(Graphics2D g, int xOffset) {
		g.drawImage(img, getX_Point()-xOffset, getY_Point(), null);
		System.out.println("here");
	}

}
