package ch.ilikechickenwings.karpfengame.Level;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import ch.ilikechickenwings.karpfengame.Background;
import ch.ilikechickenwings.karpfengame.CommandExecutor;
import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Storyline;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Explosion;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Entity.Updateable;
import ch.ilikechickenwings.karpfengame.Entity.VisionObstructer;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Item.Coffee;
import ch.ilikechickenwings.karpfengame.Entity.Item.Item;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Carp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Drop;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Grenade;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;
import ch.ilikechickenwings.karpfengame.Entity.Mob.*;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Reader.LvlReader;
import ch.ilikechickenwings.karpfengame.Skill.*;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;


/*
 *  TODO: Please add support for eternal wall-creating, now it is limited and resource heavy, the platforms have to be removed out of the array-> to reduce resouces needed -DPX 9.10.2013
 *  - I think this is now done. Walls are being created (int)preCalcWalls after xOffset. They are removed as soon as they can no more be seen. - SC 09.10.2013
 */

public class Level {

	public static int widht; // TODO: Variable width?
	public static int xOffset;
	public static int yOffset;
	public static int playerTrigger = 250; // as soon as the player reached this
											// position in the window, the
											// screen will follow the player.
	public static int preCalcWalls = KarpfenGame.WIDTH * 2; // the walls will
															// pre-generate
															// something like "render"distance

	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	public static ArrayList<Updateable> entities = new ArrayList<Updateable>();
	public static Skill[] skills = new Skill[Skill.getNr()];
	public Player player;
	public static Level lv;


	// TODO: decide whether all those things have to be public/private, static/non-static.. - SC 14.10.2013
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
	public static int spawnWalkZombie; // in % on walls
	public static int spawnSeagull; // after so many pixels the next Seagull spawns (up to + spawnSeagull), 0 = they do not spawn, 1 = they spawn a very very lot
	public static int spawnHealthPack; // in % on walls
	public static int spawnCoffee; // in % on walls
	// Player:
	public static int maxLife;
	// Coffee:
	public static int maxCoffee;
	// Skills: 
	public static boolean[] useableSkill= new boolean[Skill.getNr()]; // [0] = CarpSkill
	// this has multiple uses. in the beginning its used as a initial boolean, whether to play with this specific skill or not.. AND as a timing variable in Player.java
	public static String nextLevel;
	public static String thisLevel;
	
	// Stuff:
    private KarpfenGame karpfenGame; 
	private String lvl;
	private Wall lastWall;

	protected SoundSystem sS;
	
	private Background background = new Background(0); // 0 = multipi TODO this should be defined in level.properties
	
	// constructor
	public Level(String lvl, KarpfenGame karpfenGame) {
		lv=this;
		this.setLvl(lvl);
		this.setKarpfenGame(karpfenGame);
		new LvlReader("lvl"+lvl+".pros");
		thisLevel=lvl;
		player = new Player(0, 0, maxLife, maxCoffee, useableSkill);

		for(int i=0;i<skills.length;i++){
			if(useableSkill[i]){
				switch(i){
				case 0:
				CarpSkill cs=new CarpSkill();
				skills[i]=cs;
				break;
				case 1:
				EelSkill es=new EelSkill();	
				skills[i]=es;
				break;
				case 2:
				Karpfokalypse skill = new Karpfokalypse();
				skills[i]=skill;
				break;
				case 3:
				FlyingCarpSkill fc = new FlyingCarpSkill();
				skills[i]=fc;
				break;
				case 4:
				GrenadeSkill gs = new GrenadeSkill();
				skills[i]=gs;
				break;
				}
			}
		}
		
		// mobs preparations
		Seagull.setLastSeagull(null);
		
		Wall wall = new Wall(0, KarpfenGame.HEIGHT / 2, widthMu, height); // first
		// wall
        walls.add(wall);
        // on the first wall there should be no zombie

        addWalls();
        sS= new SoundSystem(Sounds.gameMusic1);
	}
	public Level(String string, KarpfenGame karpfenGame,boolean otherLevel){
		lv=this;
		this.setLvl(string);
		this.setKarpfenGame(karpfenGame);
		player = new Player(0, 0, maxLife, maxCoffee, useableSkill);

		for(int i=0;i<skills.length;i++){
			if(useableSkill[i]){
				switch(i){
				case 0:
				CarpSkill cs=new CarpSkill();
				skills[i]=cs;
				break;
				case 1:
				EelSkill es=new EelSkill();	
				skills[i]=es;
				break;
				case 2:
				Karpfokalypse skill = new Karpfokalypse();
				skills[i]=skill;
				break;
				case 3:
				FlyingCarpSkill fc = new FlyingCarpSkill();
				skills[i]=fc;
				break;
				}
			}
		}
		
		
	}
	

	public void update(InputHandler inHandler) {

		// follow the player with camera
		if (player.getX_Point() > xOffset + playerTrigger) {
			xOffset = player.getX_Point() - playerTrigger;
		}
		
		// update Walls:
		addWalls();
		
		// Player
		// checks collision of player with walls
		// TODO: This check does probably not work if there's more than one wall
		// above each other.. (witch is not possible yet)
		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			if(player.isGravityOn()){
				wall.setPlayerStandingOn(false);
			}
			if(PlayerWallCollide(wall,player)){
				player.setY_Point(wall.getY_Point() + 1 - player.getHeight());
				player.setGravityOn(false);
				wall.setPlayerStandingOn(true);	
			}
			if(wall.isPlayerStandingOn() && 
			   (player.getX_Point()+5>wall.getX_Point()+wall.getWidth() ||
			    player.getX_Point()+player.getWidth()-5<wall.getX_Point())){
				player.setGravityOn(true);
			}
		}
		player.update(inHandler);
		if(player.getX_Point()<xOffset){ // player can't fall off the walls by walking backwards
			player.setX_Point(xOffset);
		}
		// die
		if (player.getY_Point() > KarpfenGame.HEIGHT || player.getLifes() <= 0) {
			die();
		}
		
		// Spawn Seagulls
		if(Seagull.getLastSeagull()==null && spawnSeagull!=0){
			Seagull sg=new Seagull(xOffset+KarpfenGame.WIDTH);
			entities.add(sg);
			Seagull.setLastSeagull(sg);
		}else if(spawnSeagull!=0){ // there already is a Seagull
			if(Seagull.getLastSeagull().getX_Point()+spawnSeagull<xOffset+KarpfenGame.WIDTH){
				Random r = new Random();
				Seagull sg = new Seagull(xOffset+KarpfenGame.WIDTH+r.nextInt(spawnSeagull));
				entities.add(sg);
				Seagull.setLastSeagull(sg);
			}
		}
		
		// Entities update:
		if (!(entities.size() == 0)) {

			for (int wz = 0; wz < entities.size(); wz++) {
				Entity ent = (Entity) entities.get(wz);
				
				// despawn because out of range
			   if (ent.getX_Point() + ent.getWidth() * 10 < xOffset || ent.getX_Point() > xOffset+ preCalcWalls) { // unnice
				    entities.remove(ent);
			   }
				
			    // SINGLE UPDATES
				ent.update(inHandler);
				
				// Entity - Walls
				for (int w = 0; w < walls.size() && ent instanceof Grenade; w++) {
					Wall wall = (Wall) walls.get(w);
					Projectile pr = (Projectile)ent;
					if(ProjectileWallCollide(wall,pr)){
						pr.setLifes(0);	
						pr.setY_Point(wall.getY_Point() + 1 - pr.getHeight());
						Grenade gr=(Grenade)pr;
						if(gr.getSize()>1){
							Grenade g1=new Grenade(gr.getX_Point(),gr.getY_Point(),gr.getxVel(),-gr.getyVel(),gr.getSize()-1);
							Grenade g2=new Grenade(gr.getX_Point(),gr.getY_Point(),-gr.getxVel()/2,-gr.getyVel()/2,gr.getSize()-2);
							Grenade g3=new Grenade(gr.getX_Point(),gr.getY_Point(),gr.getxVel()/4,-gr.getyVel()/2,gr.getSize()-1);

							entities.add(g1);
							entities.add(g2);
							entities.add(g3);
						}
					}
				}
				
				// Entity - Player
				// we have to talk about this after we made the graphics...
				if (player.getX_Point() + player.getWidth() > ent
						.getX_Point()
						&& player.getX_Point() < ent.getX_Point()
								+ ent.getWidth()
						&& player.getY_Point() + player.getHeight() > ent
								.getY_Point()
						&& player.getY_Point() < ent.getY_Point()
								+ ent.getHeight()) // if ent and player collide
				{
					if(ent instanceof Mob){
					    player.getDamaged((Mob) ent);
					}else if(ent instanceof HealthPack){
						if(player.getLifes()<maxLife){
						    player.getHealed((HealthPack) ent);
						    entities.remove(ent);
						}
					}else if(ent instanceof Coffee){
						if(player.getCoffee()<maxCoffee){
							player.getCaffeined((Coffee) ent);
							entities.remove(ent);
							}
					}else if(ent instanceof Drop){
						
						player.getDamaged((Drop) ent);
						entities.remove(ent);
						
						for(int i=0;i<5;i++){
							Random rand = new Random();
							int ui=(int) (rand.nextInt(10)+1);
							
							player.setObstructedVision(true);
							VisionObstructer s;
							if(ui<5){
								s= new VisionObstructer(rand.nextInt(KarpfenGame.WIDTH-250),rand.nextInt(KarpfenGame.HEIGHT-250),Tile.shit1,player);
							}else{
								s= new VisionObstructer(rand.nextInt(KarpfenGame.WIDTH-250),rand.nextInt(KarpfenGame.HEIGHT-250),Tile.shit2,player);
							}
								entities.add(s);	
						}
					}
				}
				
				// Monster (ent) - Entity (entity)
				if(ent instanceof Mob){
					for (int pr = 0; pr < entities.size(); pr++) { // pr for projetiles
						Entity entity=(Entity)entities.get(pr);
						if(entity instanceof Projectile && 
							!(entity instanceof Drop) &&
								ent.getX_Point() + ent.getWidth() > entity.getX_Point()
								&& ent.getX_Point() < entity.getX_Point() + entity.getWidth()
								&& ent.getY_Point() + ent.getHeight() > entity.getY_Point()
								&& ent.getY_Point() < entity.getY_Point()+ entity.getHeight()) 
							    // if Projectile entity intersects Mob ent
						{
							Mob mob=(Mob) ent;
							// damage Mob, as long as it is not a Seagull-Drop
							if(!(entity instanceof Drop)){
							    mob.getDamaged((Projectile) entity);
							}
							// delete Projectile entity, if it is a Carp or a Grenade
							if(entity instanceof Carp || entity instanceof Grenade){
								entities.remove(entity);
							    
							}
						}
					}
				}
				// Item (ent) - Entity (entity)
				else if(ent instanceof Item){
					for (int pr = 0; pr < entities.size(); pr++) { // pr for projetiles
						Entity entity=(Entity) entities.get(pr);
						if(entity instanceof Projectile 
								&& ent.getX_Point() + ent.getWidth() > entity.getX_Point()
								&& ent.getX_Point() < entity.getX_Point() + entity.getWidth()
								&& ent.getY_Point() + ent.getHeight() > entity.getY_Point()
								&& ent.getY_Point() < entity.getY_Point() + entity.getHeight()) 
							    // if Projectile entity intersects Item ent
						{
							Item item=(Item) ent;
							item.setLifes(0);
							pr=entities.size();
						}
					}
				}
				if(ent.getLifes()<=0){
					entities.add(new Explosion(ent.getX_Point(),ent.getY_Point(),50,50));
					entities.remove(ent);
					
				}
			} // end for each Entity
		} // end if there are Entities at all
		
		if(lastWall.isPlayerStandingOn()){
			nextLevel();
		}

	}


	public void draw(Graphics2D g2) {
		
		background.draw(g2, xOffset);
		
		player.draw(g2, xOffset, yOffset);

		for (int w = 0; w < walls.size(); w++) {
			Wall wall = (Wall) walls.get(w);
			wall.draw(g2, xOffset,yOffset);
		}
		player.draw(g2, xOffset, yOffset);
		for (int wz = 0; wz < entities.size(); wz++) {
			Updateable en= (Updateable)entities.get(wz);
			en.draw(g2, xOffset, yOffset);
		}
	}
	
	public boolean PlayerWallCollide(Wall wall,Player player){
		if (player.getX_Point() + player.getxVel() < wall.getX_Point() + wall.getWidth()
			&& player.getX_Point() + player.getWidth() + player.getxVel() > wall.getX_Point()
			&& wall.getY_Point() < player.getY_Point() + player.getHeight() + player.getyVel()
			&& wall.getY_Point() + wall.getHeight() > player.getY_Point() + player.getHeight() + player.getyVel()
			&& player.getyVel()>0)
		{ // direct collide
			return true;
		}else if(player.getyVel()>=wall.getHeight()/2 )
			{// player's y is too fast
				for(int i=0;i<player.getyVel();i+=wall.getHeight()/2){ 
					if(player.getX_Point() + player.getxVel()*i/player.getyVel() <= wall.getX_Point() + wall.getWidth()
					   && player.getX_Point() + player.getWidth() + player.getxVel()*i/player.getyVel() >= wall.getX_Point()
					   && wall.getY_Point() <= player.getY_Point() + player.getHeight() + i
					   && wall.getY_Point() + wall.getHeight() >= player.getY_Point() + player.getHeight() + i){
						return true;
					}
				}
			}
	    return false;
	}
	
	public boolean ProjectileWallCollide(Wall wall,Projectile pr){
		if (pr.getX_Point() + pr.getxVel() < wall.getX_Point() + wall.getWidth()
			&& pr.getX_Point() + pr.getWidth() + pr.getxVel() > wall.getX_Point()
			&& wall.getY_Point() < pr.getY_Point() + pr.getHeight() + pr.getyVel()
			&& wall.getY_Point() + wall.getHeight() > pr.getY_Point() + pr.getHeight() + pr.getyVel()
			&& pr.getyVel()>0)
		{ // direct collide
			return true;
		}else if(pr.getyVel()>=wall.getHeight()/2 )
			{// player's y is too fast
				for(int i=0;i<pr.getyVel();i+=wall.getHeight()/2){ 
					if(pr.getX_Point() + pr.getxVel()*i/pr.getyVel() <= wall.getX_Point() + wall.getWidth()
					   && pr.getX_Point() + pr.getWidth() + pr.getxVel()*i/pr.getyVel() >= wall.getX_Point()
					   && wall.getY_Point() <= pr.getY_Point() + pr.getHeight() + i
					   && wall.getY_Point() + wall.getHeight() >= pr.getY_Point() + pr.getHeight() + i){
						return true;
					}
				}
			}
	    return false;
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
			if (wi.getY_Point() + dyOffset + player.getHeight() > KarpfenGame.HEIGHT
					|| wi.getY_Point() + dyOffset + wi.getHeight() < 40) { // makes walls outside
															// of the window
															// impossible
				dyOffset *= -1;
			}
			
			while(wi.getY_Point()+dyOffset<120){
				++dyOffset;
			}

			wall = new Wall(wi.getX_Point() + wi.getWidth() + dxMu
					+ r.nextInt(2 * dxVar) - dxVar, wi.getY_Point() + dyOffset,
					widthMu + r.nextInt(2 * widthVar) - widthVar, height);
			wi = wall;
			
			// spawn Entities:
			if (spawnWalkZombie >= r.nextInt(100)) {
				int skin=r.nextInt(4);
				WalkZombie wz = new WalkZombie(wi.getX_Point(), wi.getY_Point(),skin);
				entities.add(wz);
			}
			if (spawnHealthPack >= r.nextInt(100)) {
				HealthPack hp = new HealthPack(wi.getX_Point()+(wi.getWidth()/2), wi.getY_Point());
				entities.add(hp);
				if (spawnCoffee >= r.nextInt(100)) {
				    Coffee co = new Coffee(wi.getX_Point()+wi.getWidth()/2, wi.getY_Point());
				    entities.add(co);
				    hp.setX_Point(hp.getX_Point()-hp.getWidth());
				    co.setX_Point(co.getX_Point()+co.getWidth());
			    }
			}else if (spawnCoffee >= r.nextInt(100)) {
			    Coffee co = new Coffee(wi.getX_Point()+wi.getWidth()/2, wi.getY_Point());
			    entities.add(co);
		    }
			
			walls.add(wall);
		}

		// remove walls that are no longer necessary.
		wall = (Wall) walls.get(0);
		if (wall.getX_Point() + wall.getY_Point() < xOffset) {
			walls.remove(0);
		}
		lastWall= (Wall) walls.get(walls.size()-1);
	}

	public void die() {
		resetLevel();
		karpfenGame.setLvl(new Level(thisLevel, karpfenGame));
	}

	
	private void nextLevel() {
		resetLevel();
		if(nextLevel.startsWith("b")){
		getKarpfenGame().setLvl(new BossLevel(nextLevel, getKarpfenGame()));
		}else if(nextLevel.startsWith("s")){
		getKarpfenGame().setStory(new Storyline(nextLevel, getKarpfenGame()));	
		}else{
		getKarpfenGame().setLvl(new Level(nextLevel, karpfenGame));
		}
		
	}

	protected void resetLevel(){
		walls.clear();
		entities.clear();
		xOffset = 0;
		if(sS!=null){
		sS.stop();
		}
	}
	
	
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public static Skill[] getSkills() {
		return skills;
	}

	public static void setSkills(Skill[] skills) {
		Level.skills = skills;
	}

	public static ArrayList<Updateable> getEntities() {
		return entities;
	}

	public static void setEntities(ArrayList<Updateable> entities) {
		Level.entities = entities;
	}

	
	
	
	
	public static int getxOffset() {
		return xOffset;
	}

	public static void setxOffset(int xOffset) {
		Level.xOffset = xOffset;
	}

	public static int getMaxLife() {
		return maxLife;
	}

	public static void setMaxLife(int maxLife) {
		Level.maxLife = maxLife;
	}

	public static int getMaxCoffee() {
		return maxCoffee;
	}

	public static void setMaxCoffee(int maxCoffee) {
		Level.maxCoffee = maxCoffee;
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
	public String getLvl() {
		return lvl;
	}

	/**
	 * @param string
	 *            the lvl to set
	 */
	public void setLvl(String string) {
		this.lvl = string;
	}

	public static void executeCommand(String[] str2){
		new CommandExecutor(str2, lv);
	}
	
	
}
