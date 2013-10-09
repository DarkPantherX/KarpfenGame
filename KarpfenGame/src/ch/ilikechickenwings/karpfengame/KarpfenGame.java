package ch.ilikechickenwings.karpfengame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.ComponentHandler;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Menu.Menu;


public class KarpfenGame extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH=500;
	public static int HEIGHT=500;
	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	public static boolean paused=false;
	private InputHandler inHandler;
	private ComponentHandler compHandler;
	private Menu menu;
	private int wid=HEIGHT;
	private long oldTime=0;
	private Player player;
	private boolean a[] = new boolean[8];
	
	
	public KarpfenGame(){
		
		// makes new listener (ComponentHandler and InputHandler) and add them
				// to the game
				inHandler = new InputHandler();
				compHandler = new ComponentHandler();
				addKeyListener(inHandler);
				addComponentListener(compHandler);
				// sets the menu to the IntroScreenMenu (at the begin of the game)
				setMenu(new Menu());

				Player player = new Player(0,0,10,30,70);
				this.player = player;
				
				// start the game thread (run-method)
				new Thread(this).start();
				
				
		
	}
	


		@Override
	public void run() {
		createWalls(true);
		
			while(!paused){
			update();
			repaint();
			
		}
		
	}
	
	private void createWalls(boolean first) {
		if(first){
			Wall wall = new Wall(0,230,80,20);
			walls.add(wall);
		}else if(wid>0){
				int in = walls.size()-1;
				Wall wi = (Wall) walls.get(in);
				Random r= new Random();
				int x=(int) (r.nextInt(100-30)+30)+wi.getX_Point()+wi.getWidth();
				int y=getWallY(wi);
				int xy=(int) (r.nextInt(100-30)+30);
				int yy=20;
				wid= wid-xy;
				Wall wall= new Wall(x,y,xy,yy);
				walls.add(wall);
			}
			
		}

	private int getWallY(Wall wi){
		Random r = new Random();
		int a=(int) (r.nextInt(240)-120)+wi.getY_Point()+wi.getHeight();
		
		if(a>HEIGHT-50||a<50){
					
			a=getWallY(wi);
				}
		return a;
		
	}

	private void update() {
		long currTime= System.currentTimeMillis();
		
		if(currTime>oldTime+40){
		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			
			wall.setX_Point(wall.getX_Point()-2);
			if(wall.getX_Point()+wall.getWidth()<0){
				walls.remove(wall);
				wid=wid+wall.getWidth();
			}
		
		 }
		//getA();
		player.update(inHandler);
		oldTime=currTime;
		}
		
			if(wid>0){
				createWalls(false);
			}
		}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, WIDTH, HEIGHT);

		

		for (int w = 0; w < walls.size(); w++) {
			g2.setColor(Color.red);
			Wall wall = (Wall) walls.get(w);
			//System.out.println(w);
			//System.out.println(wall.getX_Point() + " " + wall.getY_Point()
			//		+ " " + wall.getWidth() + " " + wall.getHeight());
			g2.fillRect(wall.getX_Point(), wall.getY_Point(), wall.getWidth(),
					wall.getHeight());
			player.draw(g2);
		
		}
	}

	
	
	
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// opens new frame
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		// game paused false
		paused = false;
		// make a new JPanel (this FightClub class) and adds it to the frame
		final KarpfenGame mainP = new KarpfenGame();
		frame.add(mainP);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

			private void setMenu(Menu menu) {
		this.menu=menu;
		
	}

		/**	public boolean[] getA() {

				if (inHandler.getKeys()[KeyEvent.VK_W]) {
					a[0] = true;

				}
				if (inHandler.getKeys()[KeyEvent.VK_S]) {
					a[1] = true;

				}
				if (inHandler.getKeys()[KeyEvent.VK_A]) {
					System.out.println("asdf");
					a[2] = true;

				}
				if (inHandler.getKeys()[KeyEvent.VK_D]) {
					a[3] = true;

				}

				if (inHandler.getKeys()[KeyEvent.VK_UP]) {
					a[8] = true;
				}

				if (inHandler.getKeys()[KeyEvent.VK_DOWN]) {
					a[9]=true;
				}

				if (inHandler.getKeys()[KeyEvent.VK_SPACE]) {
					a[4] = true;

				}
				if (inHandler.getKeys()[KeyEvent.VK_ENTER]) {
					a[5] = true;
				}

				if (inHandler.getKeys()[KeyEvent.VK_ESCAPE]) {
					a[6] = true;
				}
				if (inHandler.getKeys()[KeyEvent.VK_L]) {
					a[7] = true;
				}

				return a;
			}
**/}

			