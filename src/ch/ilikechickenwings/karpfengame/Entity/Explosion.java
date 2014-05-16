package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Explosion extends Entity{

	private int timeVar;
	private Timer timer;

	
	public Explosion(int x, int y, int width, int height){
		setX_Point(x);
		setY_Point(y);
		setWidth(width);
		setHeight(height);
		setLifes(1);
		timer= new Timer(40);
	}
	
	public void update(InputHandler inhandler){
		
		if(timer.isReady()){
			if(timeVar<5){
			timeVar++;
		timer= new Timer(40);
		}else{
			Level.entities.remove(this);
			}
		}
		
	}
	
	public void draw(Graphics2D g2, int xOffset){
		
		g2.drawImage(Tile.explosion1[0][timeVar], getX_Point()-xOffset, getY_Point(), getWidth(), getHeight(),  null);
	}
	
	
}
