package ch.ilikechickenwings.karpfengame.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public boolean keys[] = new boolean[500];
	public boolean typed[]= new boolean[500]; // this array does NOT use the method keyTyped()
	
	/*
	 * Speed tutorial on typed[]: 
	 * To detect if a Key was typed, do not use the standard keyTyped() method.
	 * Instead, if you want to know, wheter a Key was typed, write:
	 *     inHandler.isTypedReady(KeyEvent.VK_A);
	 * This will only return true the first time you call it. 
	 * To reset, the respective key has to be released.
	 * This method is only true, the first time you call it after the key has been released.
	 * 
	 * Please do not use typed in an other manner.
	 * 
	 */
	
	public void KeyListener(){
		for(int i=0;i<keys.length;i++){
			keys[i]=false;
			typed[i]=true;
		}
	}

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
			typed[code] = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { 
		
	}
	
    public boolean isTypedReady(int e){
    	if(keys[e] && typed[e]){
    		typed[e]=false;
    		return true;
    	}
    	return false;
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
