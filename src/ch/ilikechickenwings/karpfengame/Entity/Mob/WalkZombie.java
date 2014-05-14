package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class WalkZombie extends Mob{
	

	private boolean dir=true; // true = they walk to right, false = they walk to left
	private int timeVar;
	private int timeVarPosi;
	private Timer timer;
	private int dire;
	private int skin;
	
	public WalkZombie(int x,int y, int skin) {
		setWidth(20);
		setHeight(40);
		setX_Point(x);
		setY_Point(y-getHeight());
		setLifes(100);
		setxVel(3);
		setyVel(0);
		setDamage(10);
		setGravityOn(false);
		setSkin(skin);
	}


	public void update(InputHandler inHandler) {
		
		if (dir) { // right
			setX_Point(getX_Point()+getxVel());
			dire=0;
		} else {
			setX_Point(getX_Point()-getxVel());
			dire=1;
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
	
		if(timer==null){
			timer= new Timer(75);
		}
		
		
		if(timer.isReady()){
			
			if(timeVar==0){
			
				timeVarPosi=1;
			}else if(timeVar==2){

				timeVarPosi=-1;

			}
			timeVar+=timeVarPosi;
			timer= new Timer(75);
		}
		
	}
	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.blue);
		if(skin==1){
			g2.drawImage(Tile.zombie1[dire][timeVar],getX_Point()-xOffset, getY_Point(),null);
		}else if (skin==2){
			g2.drawImage(Tile.zombie2[dire][timeVar],getX_Point()-xOffset, getY_Point(),null);
		}else if(skin==3){
			g2.drawImage(Tile.zombie3[dire][timeVar],getX_Point()-xOffset, getY_Point(),null);
		}else{
			g2.drawImage(Tile.zombie4[dire][timeVar],getX_Point()-xOffset, getY_Point(),null);
		}
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}


	public int getSkin() {
		return skin;
	}


	public void setSkin(int skin) {
		this.skin = skin;
	}
	
	
	
}
