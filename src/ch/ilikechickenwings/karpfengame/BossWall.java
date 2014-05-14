package ch.ilikechickenwings.karpfengame;

import java.awt.Color;
import java.awt.Graphics2D;

public class BossWall extends Wall{

	public BossWall(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.red);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(),
				getHeight());
		
	}
	
}
