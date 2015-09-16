package ch.ilikechickenwings.karpfengame.Entity.Mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.BossWall;
import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Level.BossLevel;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;

public class FlyingZombie extends BossMob {

	private Player player;
	private Timer spawnTimer;
	private final int resetTime = 6;
	private int modes;
	private int lastmodes;
	private boolean first;

	public FlyingZombie(int x, int y, Player player) {
		setWidth(20);
		setHeight(40);
		setX_Point(x);
		setY_Point(y - getHeight());
		setLifes(300);
		setxVel(3);
		setyVel(3);
		setDamage(10);
		setGravityOn(false);
		this.setPlayer(player);
		
		
		spawnTimer = new Timer(resetTime * 1000);
		modes=0;
		first=true;
	}

	public void update(InputHandler inHandler) {

		// int xP = player.getX_Point();
		// int yP = player.getY_Point();
		//
		// if (xP + 100 < getX_Point() || xP - 100 > getX_Point()) {
		//
		// if (xP > getX_Point()) {
		// setX_Point(getX_Point() + getxVel());
		// } else {
		// setX_Point(getX_Point() - getxVel());
		// }
		//
		// } else if (xP + 100 > getX_Point() || xP - 100 < getX_Point()) {
		// if (xP > getX_Point()) {
		// setX_Point(getX_Point() - getxVel());
		// } else {
		// setX_Point(getX_Point() + getxVel());
		//
		// }
		// }
		// if (yP + 10 <= getY_Point() || yP - 10 >= getY_Point()) {
		// if (yP > getY_Point()) {
		// setY_Point(getY_Point() + getxVel());
		// } else if (yP < getY_Point()) {
		// setY_Point(getY_Point() - getxVel());
		// }
		// }

		
		if (spawnTimer.getProgress() > 0.2) {
			first=true;
		}
		if (spawnTimer.getProgress() < 0.2) {
			if(first){
				lastmodes=modes;
				SoundSystem.playSound(Sounds.bossSpawnSound);
				first=false;
			}
			modes = 4;
			
		}

		switch (modes) {
		case 0:
			if (getX_Point() < KarpfenGame.WIDTH - 50) {
				setX_Point(getX_Point() + getxVel());
			} else {
				modes = 1;
			}
			break;
		case 1:
			if (getY_Point() < KarpfenGame.HEIGHT - 50) {
				setY_Point(getY_Point() + getyVel());
			} else {
				modes = 2;
			}
			break;
		case 2:
			if (getX_Point() > 50) {
				setX_Point(getX_Point() - getxVel());
			} else {
				modes = 3;
			}
			break;
		case 3:
			if (getY_Point() > 50) {
				setY_Point(getY_Point() - getyVel());
			} else {
				modes = 0;
			}
			break;
		case 4:
			modes=lastmodes;
			break;

		}

		if (spawnTimer.isReady()) {
			Random r = new Random();
			BossWall wall;
			for (int i = 0; i < BossLevel.walls.size(); i++) {
				wall = (BossWall) BossLevel.walls.get(i);
				int skin = r.nextInt(4);
				WalkZombie wz = new WalkZombie(wall.getX_Point(), wall.getY_Point(), skin, 1);
				BossLevel.entities.add(wz);
			}
			spawnTimer = new Timer(resetTime * 1000);
		}

	}

	public void draw(Graphics2D g2, int xOffset, int yOffset) {
		g2.setColor(Color.red);
		g2.fillRect(20, 10, 300, 5);
		g2.setColor(Color.green);
		g2.fillRect(20, 10, getLifes(), 5);

		g2.setColor(Color.blue);
		
		
		if (modes == 4) {
			g2.setColor(Color.gray);
		}
		
		g2.fillRect(getX_Point() - xOffset, getY_Point() - yOffset, getWidth(), getHeight());
		g2.setColor(Color.blue);
		g2.fillRect(20, 20, (int) (spawnTimer.getProgress() * 300.0), 5);

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
