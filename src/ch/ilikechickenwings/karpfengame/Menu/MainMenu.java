package ch.ilikechickenwings.karpfengame.Menu;

import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;


public class MainMenu extends Menu{
	private int selected;
	private String options[];
	
	public void update(KarpfenGame karpfenGame, InputHandler inputHandler) {


		if (inputHandler.getTyped()[KeyEvent.VK_S]||inputHandler.getTyped()[KeyEvent.VK_DOWN]) {
			selected++;
			inputHandler.returnToFalse(KeyEvent.VK_S,KeyEvent.VK_DOWN);
		}
		if (inputHandler.getTyped()[KeyEvent.VK_W]||inputHandler.getTyped()[KeyEvent.VK_UP]) {
			selected--;
			inputHandler.returnToFalse(KeyEvent.VK_W,KeyEvent.VK_UP);
		}
		if (selected < 0) {
			selected = options.length - 1;
		}
		if (selected >= options.length) {
			selected = 0;
		}
		if ((inputHandler.getTyped()[KeyEvent.VK_SPACE]||inputHandler.getTyped()[KeyEvent.VK_ENTER])&& selected == 0) { // a[5]=ENTER 
		}
		if ((inputHandler.getTyped()[KeyEvent.VK_SPACE]||inputHandler.getTyped()[KeyEvent.VK_ENTER]) && selected == 1) { // a[5]=ENTER 
		}
		if ((inputHandler.getTyped()[KeyEvent.VK_SPACE]||inputHandler.getTyped()[KeyEvent.VK_ENTER])&& selected == 2) { // a[5]=ENTER
		}
		

	}

}
