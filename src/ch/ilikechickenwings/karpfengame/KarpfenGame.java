package ch.ilikechickenwings.karpfengame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.ilikechickenwings.karpfengame.Handler.ComponentHandler;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Menu.Menu;

public class KarpfenGame extends JPanel implements Runnable {

	/**
	 * 08.10.2013 - Strichcoder joined the project 09.10.2013 - Space joined the
	 * project 09.10.2013 - great another idiot...
	 * 
	 * 
	 * 09.10.2013 - I should concentrate on my studies...
	 */

	
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static boolean paused = false;
	
	private static final long serialVersionUID = 1L;
	
	private InputHandler inHandler;
	private ComponentHandler compHandler;
	private Menu menu;
	private Level lvl;
	private long oldTime = System.currentTimeMillis();
	
	
	

	public KarpfenGame() {

		// makes new listener (ComponentHandler and InputHandler) and add them
		// to the game
		inHandler = new InputHandler();
		compHandler = new ComponentHandler();
		addKeyListener(inHandler);
		addComponentListener(compHandler);
		// sets the menu to the IntroScreenMenu (at the begin of the game)
		setMenu(new Menu());

		this.lvl = new Level(1, this);
		// start the game thread (run-method)
		new Thread(this).start();
	}

	private void update() {

		lvl.update(inHandler);
		long timeElapsed = System.currentTimeMillis() - oldTime;
		// System.out.println(timeElapsed);
		if (timeElapsed < 40) {
			try {
				Thread.sleep((long) 40 - timeElapsed); // ~25 updates per second
			} catch (InterruptedException e) {
				System.out.println("Couldn't start sleeping:" + e);
				e.printStackTrace();
			}
		}
		oldTime = System.currentTimeMillis();

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, WIDTH, HEIGHT);

		lvl.draw(g2);

	}

	@Override
	public void run() {

		while (!paused) {
			update();
			repaint();
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
		this.menu = menu;

	}

	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

}
