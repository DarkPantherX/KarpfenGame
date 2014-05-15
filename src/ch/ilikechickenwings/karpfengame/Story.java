package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;


public interface Story {
	
	public void update(KarpfenGame karpfenGame, InputHandler inHandler);
	public void draw(Graphics2D g);

}
