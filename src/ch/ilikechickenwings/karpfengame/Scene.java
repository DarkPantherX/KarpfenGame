package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import java.awt.Image;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;

public class Scene implements Story{
	
	private int showTime;
	private Image img;
	private int x_Point;
	private int y_Point;
	private int moveX;
	private int moveY;
	private Timer timer1;
	private int timeVar;

	
	public Scene(Image img, int showTime,int x, int y,int movX, int movY){
		setShowTime(showTime);
		this.x_Point=x;
		this.y_Point=y;
		this.img=img;
		this.moveX=movX;
		this.moveY=movY;
		timer1= new Timer(100);
		timeVar=100;
		
	}

	public void update(Storyline storyLine, InputHandler inHandler) {
		
		if(timer1.isReady()){
			if(timeVar>0){
			x_Point+=moveX/100;
			y_Point+=moveY/100;
			timeVar--;
			timer1= new Timer(100);
			//System.out.println(timeVar);
			}else{
				storyLine.alertNext();
			}
		}
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(img,x_Point,y_Point,KarpfenGame.WIDTH,KarpfenGame.HEIGHT,null);
		//g2.drawImage(img,0,0,KarpfenGame.WIDTH,KarpfenGame.HEIGHT,null);
	}

	/**
	 * @return the showTime
	 */
	public int getShowTime() {
		return showTime;
	}

	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}
	/**
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}
	/**
	 * @param img the Image to set
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	public void update(Storyline storyline) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(KarpfenGame karpfenGame, InputHandler inHandler) {
		// TODO Auto-generated method stub
		
	}

}
