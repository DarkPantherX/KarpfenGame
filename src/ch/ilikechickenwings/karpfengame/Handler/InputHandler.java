package ch.ilikechickenwings.karpfengame.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public boolean keys[] = new boolean[50000];
	public boolean typed[]= new boolean[50000];

	//creates the KeyEvents and adds Keys to the boolean
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length) {
			keys[code] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code > 0 && code < keys.length) {
			keys[code] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	//setts the tpyed back
	public void returnToFalse(int i, int j){
		typed[i]=false;
		typed[j]=false;
		
	}

	//create getters and setters for other methods
	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}
	public boolean[] getTyped() {
		return typed;
	}

	public void setTyped(boolean[] typed) {
		this.typed = typed;
	}

	
	
}
