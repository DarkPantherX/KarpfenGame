package ch.ilikechickenwings.karpfengame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import ch.ilikechickenwings.karpfengame.Handler.ComponentHandler;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Menu.Menu;

public class KarpfenGame extends JPanel implements Runnable{

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
	public static PrintStream old;
	public static JTextArea textArea2;
	public static JTextArea textArea1;
	
	private static final long serialVersionUID = 1L;
	
	private InputHandler inHandler;
	private ComponentHandler compHandler;
	private Menu menu;
	private Level lvl;
	private long oldTime = System.currentTimeMillis();
	private boolean opened=false;

	
	private ByteArrayOutputStream baos;

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
			
			if (inHandler.getKeys()[KeyEvent.VK_F3]) {
				if(!opened){
					opened=true;
					// opens new frame
					JFrame frame = new JFrame();
					frame.setSize(500, 210);
					frame.setLayout(new BorderLayout());
					
					 baos = new ByteArrayOutputStream();
					 PrintStream ps = new PrintStream(baos);
					 old = System.out;
					 System.setOut(ps);
					 textArea1 = new JTextArea();
					 textArea1.setEditable(false);
					 JScrollPane scrollPane = new JScrollPane(textArea1);
					 scrollPane.setPreferredSize(new Dimension(490,170));
					 frame.add(scrollPane, BorderLayout.NORTH);
					 textArea2 = new JTextArea();
					 textArea2.setPreferredSize(new Dimension(440,30));
					 frame.add(textArea2, BorderLayout.CENTER);
					 JButton but = new JButton("Enter");
					 but.setPreferredSize(new Dimension(50,30));
					 frame.add(but,BorderLayout.EAST);
					 frame.setVisible(true);
					 WinListener win = new WinListener();
					 but.addActionListener(win);
					 frame.addWindowListener(win);
					  Action keyAction = new AbstractAction() {
					        /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
					        public void actionPerformed(ActionEvent ae) {
					            System.out.println(textArea2.getText());
					           if(textArea2.getText().indexOf("/")==0){
					            String str= textArea2.getText().replace("/", "");
					            String[] str2 = str.split(" ");
					            Level.executeCommand(str2);
					            }
					            textArea2.setText("");
					            
					        }
					    };

					 textArea2.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "ENTER key");
				       textArea2.getActionMap().put("ENTER key", keyAction);
					 System.out.println("Started Dev Console");
					  
					   
					 
					 
				}	
			}
			if(baos!=null){
				if(!baos.toString().equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				textArea1.append("<"+dateFormat.format(date)+">"+baos.toString());
				baos.reset();
				}
			}
			
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

	public Menu getMenu() {
		return menu;
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


	class WinListener implements WindowListener, ActionListener, Action{


		@Override
		public void windowClosing(WindowEvent e) {
			System.setOut(old);
			System.out.println("Dev Console closed!");
			
		}
		
	

		@Override
		public void actionPerformed(ActionEvent e) {
			print(KarpfenGame.textArea2.getText());
			KarpfenGame.textArea2.setText("");
			
		}
		
		public void print(String str){
			System.out.println(str);
			
		}
		
		
		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowOpened(WindowEvent e) {}
		
		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowActivated(WindowEvent e) {}
		
		@Override
		public void addPropertyChangeListener(PropertyChangeListener arg0) {}

		@Override
		public Object getValue(String arg0) {return null;}

		@Override
		public boolean isEnabled() {return false;}

		@Override
		public void putValue(String arg0, Object arg1) {}

		@Override
		public void removePropertyChangeListener(PropertyChangeListener arg0) {}

		@Override
		public void setEnabled(boolean arg0) {}



		
	}
	
}
