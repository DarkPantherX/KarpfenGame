package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import java.awt.Image;

public class Background {
	
	private Image img;
	
	public Background(int nr){
		switch(nr){
			case 0:
				img = Tile.multipi;
			break;
		}
			
	}
	
	public void draw(Graphics2D g, int xOffset) {
		g.drawImage(img, -xOffset/2, 0, img.getWidth(null)*KarpfenGame.HEIGHT/img.getHeight(null),KarpfenGame.HEIGHT,null);
		//System.out.println("debug");
	}

}
