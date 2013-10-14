package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Mob;
import ch.ilikechickenwings.karpfengame.Entity.Mob.WalkZombie;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;

/*
 *  TODO: Please add support for eternal wall-creating, now it is limited and resource heavy, the platforms have to be removed out of the array-> to reduce resouces needed -DPX 9.10.2013
 *  - I think this is now done. Walls are being created (int)preCalcWalls after xOffset. They are removed as soon as they can no more be seen. - SC 09.10.2013
 */

public class Level {

	public static int widht; // TODO: Variable width?
	public static int xOffset;
	public static int playerTrigger = 250; // as soon as the player reached this
											// position in the window, the
											// screen will follow the player.
	public static int preCalcWalls = KarpfenGame.WIDTH * 2; // the walls will
															// pregenerate

	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static Player player;

	// These parameters may vary from level to level
	// General:
	public static int xMax; // length of the level
	// Walls:
	public static int widthMu; // average length of wall (has to be modified at
								// some stage, as the graphics should fit the
								// wall's length
	public static int dxMu; // average difference between to walls (Mu comes
							// from the Greek letter mu and stands for the
							// arithmetic mean in statistics)
	public static int widthVar; // maximal variance of xMu (+ or -) (-> e.g. the
								// wall is smaller than usual)
	public static int dxVar; // maximal variance of dxMu (+ or -) (-> e.g. walls
								// are closer together than normal)
	public static int height;
	public static int dyVar; // maximal y-offset (+ or -) (-> e.g. wall is
								// higher than the one before)
	// Monsters:
	public static int spawnWalkZombie; // in %
	public static int spawnHealthPack; // in %
	// Player:
	public static int maxLife;
	public static int velPlayer; // velocity

	private KarpfenGame karpfenGame;
	private int lvl;

	// TODO: Coffee etc..

	public Level(int lvl, KarpfenGame karpfenGame) {

		this.setLvl(lvl);
		this.setKarpfenGame(karpfenGame);
		walls.clear();
		entities.clear();
		xOffset = 0;
		if (this.lvl == 1) {
			// data should now be loaded depending on lvl
			xMax = KarpfenGame.WIDTH * 4;

			widthMu = 100;
			dxMu = 65;
			widthVar = 35;
			dxVar = 30;
			height = 15;
			dyVar = 60; // Here I took half of the previous value..

			spawnWalkZombie = 30;
			spawnHealthPack = 20;

			maxLife = 100;
			velPlayer = 4;

			player = new Player(0, 0, maxLife, velPlayer);
			createWalls();
		}
	}

	public void update(InputHandler inHandler) {

		// follow the player with camera
		if (player.getX_Point() > xOffset + playerTrigger) {
			xOffset = player.getX_Point() - playerTrigger;
		}

		addWalls();

		if (!player.isJumping()) {
			player.setFalling(true);
		}
		// checks collision of player with walls
		// TODO: This check does probably not work if there's more than one wall
		// above each other.. (witch is not possible yet)
		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			if (player.getX_Point() < wall.getX_Point() + wall.getWidth()
					&& player.getX_Point() + player.getWidth() > wall
							.getX_Point()) {// player's x is not on a wall
				if (wall.getY_Point() < player.getY_Point()
						+ player.getHeight()
						&& wall.getY_Point() + wall.getHeight() > player
								.getY_Point() + player.getHeight()) { // player's
																		// y is
																		// on a
																		// wall
					player.setY_Point(wall.getY_Point() + 1
							- player.getHeight());
					player.setFalling(false);
					player.setJumping(false);
				} else if (!player.isJumping()) {
					player.setFalling(true);
				}

			}
		}

		player.update(inHandler);

		// die
		if (player.getY_Point() > KarpfenGame.HEIGHT || player.getLifes() <= 0) {
			die();
		}
		// Monsters update:
		if (!(entities.size() == 0)) {
			Entity ent = entities.get(0);
			
			if (ent.getX_Point() + ent.getWidth() * 10 < xOffset) { // unnice
				entities.remove(0);
			
			}
			for (int wz = 0; wz < entities.size(); wz++) {
				ent = (Entity) entities.get(wz);
				if(entities.get(wz) instanceof WalkZombie){
				
				WalkZombie wZombie= (WalkZombie) entities.get(wz);
				// check if they are still on the platform
				for (int w = 0; w < walls.size(); w++) {
					Wall wall = (Wall) walls.get(w);
					if (wZombie.isDir()
							&& wZombie.getX_Point() + wZombie.getWidth() > wall
									.getX_Point() + wall.getWidth()
							&& wZombie.getX_Point() < wall.getX_Point()
									+ wall.getWidth()) {
						wZombie.setDir(false); // change direction
						w = walls.size();
					} else if (!wZombie.isDir()
							&& wZombie.getX_Point() < wall.getX_Point()
							&& wZombie.getX_Point() + wZombie.getWidth() > wall
									.getX_Point()) {

						wZombie.setDir(true); // change direction
						w = walls.size();
					}
				}
				wZombie.update(inHandler);
				}
				
				// Monster - Player
				// we have to talk about this after we made the graphics...
				if (player.getX_Point() + player.getWidth() > ent
						.getX_Point()
						&& player.getX_Point() < ent.getX_Point()
								+ ent.getWidth()
						&& player.getY_Point() + player.getHeight() > ent
								.getY_Point()
						&& player.getY_Point() < ent.getY_Point()
								+ ent.getHeight()) {
					if(ent instanceof WalkZombie){
					
					player.getDamaged((Mob) ent);
					}else if(ent instanceof HealthPack){
						if(player.getLifes()<=maxLife){
						player.getHealed((HealthPack) ent);
						entities.remove(ent);
						}
					}
				}
			}
			}
		}

	

	public void draw(Graphics2D g2) {
		player.draw(g2, xOffset);

		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			wall.draw(g2, xOffset);
		}
		for (int wz = 0; wz < entities.size(); wz++) {
			Entity en= entities.get(wz);
			if(en instanceof WalkZombie){
			((WalkZombie) en).draw(g2, xOffset);
			}else if(en instanceof HealthPack){
			((HealthPack) en).draw(g2, xOffset);
			}
		}
	}

	private void createWalls() {
		Wall wall = new Wall(0, KarpfenGame.HEIGHT / 2, widthMu, height); // first
																			// wall
		walls.add(wall);
		// on the first wall there should be no zombie

		addWalls();
	}

	private void addWalls() {
		int in = walls.size() - 1;
		Wall wi = (Wall) walls.get(in);
		Wall wall;
		Random r = new Random();
		while (wi.getX_Point() + wi.getWidth() < xMax
				&& wi.getX_Point() + wi.getWidth() < xOffset + preCalcWalls) { // as
																				// long
																				// as
																				// there
																				// is
																				// space

			int dyOffset = r.nextInt(2 * dyVar) - dyVar;
			if (wi.getY_Point() + dyOffset + height > KarpfenGame.HEIGHT
					|| wi.getY_Point() + dyOffset < 0) { // makes walls outside
															// of the window
															// impossible
				dyOffset *= -1;
			}

			wall = new Wall(wi.getX_Point() + wi.getWidth() + dxMu
					+ r.nextInt(2 * dxVar) - dxVar, wi.getY_Point() + dyOffset,
					widthMu + r.nextInt(2 * widthVar) - widthVar, height);
			wi = wall;
			if (spawnWalkZombie >= r.nextInt(100)) {
				WalkZombie wz = new WalkZombie(wi.getX_Point(), wi.getY_Point());
				entities.add(wz);
			}
			
			if (spawnHealthPack >= r.nextInt(100)) {
				HealthPack co = new HealthPack(wi.getX_Point()+(wi.getWidth()/2), wi.getY_Point());
				entities.add(co);
			}
			
			
			walls.add(wall);
		}

		// remove walls that are no longer nessecary.
		wall = (Wall) walls.get(0);
		if (wall.getX_Point() + wall.getY_Point() < xOffset) {
			walls.remove(0);
		}
	}

	public void die() {
		karpfenGame.setLvl(new Level(1, karpfenGame));
	}

	/**
	 * @return the karpfenGame
	 */
	public KarpfenGame getKarpfenGame() {
		return karpfenGame;
	}

	/**
	 * @param karpfenGame
	 *            the karpfenGame to set
	 */
	public void setKarpfenGame(KarpfenGame karpfenGame) {
		this.karpfenGame = karpfenGame;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

}
