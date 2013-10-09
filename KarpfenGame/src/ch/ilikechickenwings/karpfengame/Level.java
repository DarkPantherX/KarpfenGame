package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

/*
 *  TODO: The parameters of a level should be stored in a .txt file
 */


public class Level { 
	
    public static int widht; // TODO: Variable width?
	public static int xOffset=0;
	public static int playerTrigger=50; // as soon as the player reached this position in the window, the screen will follow the player.
	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	public static Player player;
	
	// These parameters may vary from level to level and should be stored in a .txt file:
	// General:
	public static int xMax; // length of the level
	// Walls:
	public static int widthMu; // average length of wall (has to be modified at some stage, as the graphics should fit the wall's length
	public static int dxMu; // average difference between to walls (Mu comes from the Greek letter mu and stands for the arithmetic mean in statistics)
	public static int widthVar; // maximal variance of xMu (+ or -) (-> e.g. the wall is smaller than usual)
	public static int dxVar; // maximal variance of dxMu (+ or -) (-> e.g. walls are closer together than normal)
	public static int height;
	public static int dyVar; // maximal y-offset (+ or -) (-> e.g. wall is higher than the one before)
	// TODO: Coffee / Monsters / Player etc..
	
	
	public Level(int lvl){
		
		player = new Player(0,0,10);
		
		// data should now be loaded depending on lvl
		xMax=KarpfenGame.WIDTH*2;
		
		widthMu=65; 
		dxMu=65; 
		widthVar=35;
		dxVar=35; 
		height=20;
		dyVar=60; // I took half of the previous value..
		createWalls();
	}
	
	private void createWalls(){
		Wall wall = new Wall(0,KarpfenGame.HEIGHT/2,widthMu,height); // first wall
		walls.add(wall);
		
		int in = walls.size()-1;
		Wall wi = (Wall) walls.get(in);
		Random r= new Random();
		while(wi.getX_Point()+wi.getWidth()<xMax){ // as long as there is space
			System.out.println("random"+wi.getX_Point()+" "+wi.getY_Point());
			
			int dyOffset=r.nextInt(2*dyVar)-dyVar;
			if(wi.getY_Point()+dyOffset+height>KarpfenGame.HEIGHT||wi.getY_Point()+dyOffset<0){ // makes walls outside of the window impossible
				dyOffset*=-1;
			}
			
			wall = new Wall(wi.getX_Point()+wi.getWidth()+dxMu+r.nextInt(2*dxVar)-dxVar,wi.getY_Point()+dyOffset,widthMu+r.nextInt(2*widthVar)-widthVar,height);
			wi=wall;
			walls.add(wall);
		}
		
	}
	
	public void update(InputHandler inHandler) {
		
		if(player.getX_Point()>xOffset+playerTrigger){ // follow the player with camera
			xOffset=player.getX_Point()-playerTrigger;
		}
		
		// checks collision of player with walls
		// TODO: This check does probably not work if there's more than one wall above each other.. (witch is not possible yet)
		for (int w = 0; w < walls.size(); w++) { 
			Wall wall = (Wall) walls.get(w);
		    if(player.getX_Point()<wall.getX_Point()+wall.getWidth() && 
		    		player.getX_Point()+player.getWidth()>wall.getX_Point()){// player's x is not on a wall
   
			    if(wall.getY_Point()<player.getY_Point()+player.getHeight() && 
			    		wall.getY_Point()+wall.getHeight()>player.getY_Point()+player.getHeight()){ // player's y is on a wall
				    player.setFalling(false);
				    player.setJumping(false);
			    }else if(!player.isJumping()){
				    player.setFalling(true);
				}
				
			}
		}
	
		player.update(inHandler);
		
	}
	
	public void draw(Graphics2D g2){
		player.draw(g2,xOffset);
		
		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			wall.draw(g2,xOffset);
		}
	}
	
}
