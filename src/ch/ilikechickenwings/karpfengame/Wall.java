package ch.ilikechickenwings.karpfengame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class Wall {
	
	private int x_Point;
	private int y_Point;
	private int width;
	private int height=20;
	private int pf=0; // what the heck is that? //DAFUQ? THIS ISNT FROM ME!
	private boolean playerStandingOn=false;
	private Image img[];
	private int ant;
	
	public Wall(int x, int y, int width, int height){
		this.setX_Point(x);
		this.setY_Point(y);
		this.setWidth(width);
		this.setHeight(height);
		
		ant= (int) ((KarpfenGame.HEIGHT-getY_Point())/30);
		img= new Image[ant];
		
		for(int i=0;i<ant;i++){
			Random rand = new Random();
			int num= rand.nextInt(5)+1;
			if(num==1){
				img[i]=Tile.flat1;
			}else if(num==2){
				img[i]=Tile.flat2;
			}else if(num==3){
				img[i]=Tile.flat3;
			}else if(num==4){
				img[i]=Tile.flat4;
			}else if(num==5){
				img[i]=Tile.flat5;
			}
			
			
			
		}
		
	}

	
	public void draw(Graphics2D g2,int xOffset){
		g2.setColor(Color.red);
		g2.drawImage(Tile.flat_ceiling,getX_Point()-xOffset, getY_Point(), getWidth(),
				getHeight()+20,null);
		for(int i=0;i<ant;i++){
			g2.drawImage(img[i],getX_Point()-xOffset, getY_Point()+(i+1)*30, getWidth(),
					30,null);
		}
	}
	
	/**
	 * @return the x_Point
	 */
	public int getX_Point() {
		return x_Point;
	}

	/**
	 * @param x_Point the x_Point to set
	 */
	public void setX_Point(int x_Point) {
		this.x_Point = x_Point;
	}

	/**
	 * @return the y_Point
	 */
	public int getY_Point() {
		return y_Point;
	}

	/**
	 * @param y_Point the y_Point to set
	 */
	public void setY_Point(int y_Point) {
		this.y_Point = y_Point;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the pf
	 */
	public int getPf() {
		return pf;
	}

	/**
	 * @param pf the pf to set
	 */
	public void setPf(int pf) {
		this.pf = pf;
	}
	
	/**
	 * @return the playerStandingOn
	 */
	public boolean isPlayerStandingOn() {
		return playerStandingOn;
	}

	/**
	 * @param playerStandingOn the playerStandingOn to set
	 */
	public void setPlayerStandingOn(boolean playerStandingOn) {
		this.playerStandingOn = playerStandingOn;
	}


}
