package ch.ilikechickenwings.karpfengame.Level;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Seagull;
import ch.ilikechickenwings.karpfengame.Reader.BossLvlReader;

public class UpLevel extends Level{

	public UpLevel(String lv, KarpfenGame karpfenGame) {
		super(lv, karpfenGame, true);
		new BossLvlReader("ulvl"+lv+".pros");
		thisLevel=lv;
		
		addUpWalls();
		
		Seagull.setLastSeagull(null);
		
		Wall wall = new Wall(KarpfenGame.WIDTH/2, KarpfenGame.HEIGHT-60, widthMu, height); // first
		// wall
        walls.add(wall);
	}

	private void addUpWalls() {
		// TODO Auto-generated method stub
		
	}

}
