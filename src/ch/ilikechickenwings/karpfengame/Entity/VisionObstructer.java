package ch.ilikechickenwings.karpfengame.Entity;

import java.awt.Graphics2D;
import java.awt.Image;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Level.Level;

public class VisionObstructer extends Entity {

	private Image img;
	private Timer timer;
	private int drawX;
	private int drawY;
	private Player player;

	public VisionObstructer(int x, int y, Image img, Player player) {
		setLifes(1000);
		setX_Point(player.getX_Point());
		setY_Point(player.getY_Point());
		this.img = img;
		this.player = player;
		this.drawX = x;
		this.drawY = y;
		timer = new Timer(3000);
	}

	public void update(InputHandler inhandler) {

		setX_Point(player.getX_Point());
		setY_Point(player.getY_Point());
		if (timer.isReady()) {
			Level.entities.remove(this);

		}
	}

	public void draw(Graphics2D g, int xOffset, int yOffset) {
		g.drawImage(img, drawX, drawY, 250, 250, null);
	}

	public int getDrawX() {
		return drawX;
	}

	public void setDrawX(int drawX) {
		this.drawX = drawX;
	}

	public int getDrawY() {
		return drawY;
	}

	public void setDrawY(int drawY) {
		this.drawY = drawY;
	}

}
