package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public class WalkZombie extends Mob{
	

	private boolean dir=true; // true = they walk to right, false = they walk to left
	
	
	public WalkZombie(int x,int y) {
		setWidth(20);
		setHeight(40);
		setX_Point(x);
		setY_Point(y-getHeight());
		setLifes(100);
		setxVel(3);
		setyVel(0);
		setDamage(10);
		setGravityOn(false);
	}


	public void update(InputHandler inHandler) {
		
		if (dir) { // right
			setX_Point(getX_Point()+getxVel());
		} else {
			setX_Point(getX_Point()-getxVel());
		}
	
		for (int w = 0; w < Level.walls.size(); w++) {
			Wall wall = (Wall) Level.walls.get(w);
			if (isDir()
					&& getX_Point() + getWidth() > wall
					.getX_Point() + wall.getWidth()
					&& getX_Point() < wall.getX_Point()
					+ wall.getWidth()) 
			{ // if Zombie is about to walk off the wall at the right side.
				setDir(false); // change direction
				w = Level.walls.size();
			} else if (!isDir()
					&& getX_Point() < wall.getX_Point()
					&& getX_Point() + getWidth() > wall
					.getX_Point())
			{  // if Zombie is about to walk off the wall on the left side.
				setDir(true); // change direction
				w = Level.walls.size();
			}
		}
	
	}
	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.blue);
		g2.fillRect(getX_Point()-xOffset, getY_Point(), getWidth(), getHeight());
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}
	
	
	
}
