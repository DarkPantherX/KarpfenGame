package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

public interface Updateable {

	public void update(InputHandler inHandler);
	public void draw(Graphics2D g, int xOffset, int yOffset);
	
}
