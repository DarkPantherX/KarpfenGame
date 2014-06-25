package ch.ilikechickenwings.karpfengame.Level;

import java.awt.Graphics2D;

import ch.ilikechickenwings.karpfengame.BossWall;
import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Storyline;
import ch.ilikechickenwings.karpfengame.Tile;
import ch.ilikechickenwings.karpfengame.Wall;
import ch.ilikechickenwings.karpfengame.Entity.Entity;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Entity.Updateable;
import ch.ilikechickenwings.karpfengame.Entity.VisionObstructer;
import ch.ilikechickenwings.karpfengame.Entity.Item.Coffee;
import ch.ilikechickenwings.karpfengame.Entity.Item.HealthPack;
import ch.ilikechickenwings.karpfengame.Entity.Item.Item;
import ch.ilikechickenwings.karpfengame.Entity.Mob.BossMob;
import ch.ilikechickenwings.karpfengame.Entity.Mob.FlyingZombie;
import ch.ilikechickenwings.karpfengame.Entity.Mob.Mob;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Drop;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Projectile;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Carp;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Reader.BossLvlReader;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;

public class BossLevel extends Level{
	



		
		// constructor
		public BossLevel(String lv, KarpfenGame karpfenGame) {
			super(lv, karpfenGame,true);
			
			new BossLvlReader("blvl"+lv+".pros");
			thisLevel=lv;
			entities.add(new FlyingZombie(300,300,getPlayer()));
			sS = new SoundSystem(Sounds.gameMusic2);
			}
			
		
		public void update(InputHandler inHandler) {
			// checks collision of player with walls
			// TODO: This check does probably not work if there's more than one wall
			// above each other.. (witch is not possible yet)
			for (int w = 0; w < walls.size(); w++) {
				BossWall wall = (BossWall) walls.get(w);
				if(player.isGravityOn()){
					wall.setPlayerStandingOn(false);
				}
				if(PlayerWallCollide(wall,player)){
					player.setY_Point(wall.getY_Point() + 1 - player.getHeight());
					player.setGravityOn(false);
					wall.setPlayerStandingOn(true);	
				}
				if(wall.isPlayerStandingOn() && 
				   (player.getX_Point()>wall.getX_Point()+wall.getWidth() ||
				    player.getX_Point()+player.getWidth()<wall.getX_Point())){
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
								int ui=(int) (Math.random()*10);
								
								player.setObstructedVision(true);
								VisionObstructer s;
								if(ui<5){
									s= new VisionObstructer((int)(Math.random()*KarpfenGame.WIDTH),(int)(Math.random()*KarpfenGame.HEIGHT),Tile.shit1);
								}else{
									s= new VisionObstructer((int)(Math.random()*KarpfenGame.WIDTH),(int)(Math.random()*KarpfenGame.HEIGHT),Tile.shit2);
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
									ent.getX_Point() + ent.getWidth() > entity
									.getX_Point()
									&& ent.getX_Point() < entity.getX_Point()
											+ entity.getWidth()
									&& ent.getY_Point() + ent.getHeight() > entity
											.getY_Point()
									&& ent.getY_Point() < entity.getY_Point()
											+ entity.getHeight()) // if Projectile entity intersects Mob ent
							{
								Mob mob=(Mob) ent;
								// damage Mob, as long as it is not a Seagull-Drop
								if(!(entity instanceof Drop)){
								    mob.getDamaged((Projectile) entity);
								}
								// delete Projectile entity, if it is a Carp
								if(entity instanceof Carp){
								    entities.remove(entity);
								}
							}
						}
					}
					// Item (ent) - Entity (entity)
					else if(ent instanceof Item){
						for (int pr = 0; pr < entities.size(); pr++) { // pr for projetiles
							Entity entity=(Entity) entities.get(pr);
							if(entity instanceof Projectile && 
									ent.getX_Point() + ent.getWidth() > entity
									.getX_Point()
									&& ent.getX_Point() < entity.getX_Point()
											+ entity.getWidth()
									&& ent.getY_Point() + ent.getHeight() > entity
											.getY_Point()
									&& ent.getY_Point() < entity.getY_Point()
											+ entity.getHeight()) // if Projectile entity intersects Item ent
							{
								Item item=(Item) ent;
								item.setLifes(0);
								pr=entities.size();
							}
						}
					}
					if(ent.getLifes()<=0){
						entities.remove(ent);
						if(ent instanceof BossMob){
							resetLevel();
							if(nextLevel.startsWith("b")){
								getKarpfenGame().setLvl(new BossLevel(nextLevel, getKarpfenGame()));
								}else if(nextLevel.startsWith("s")){
								getKarpfenGame().setStory(new Storyline(nextLevel, getKarpfenGame()));	
								}else{
								getKarpfenGame().setLvl(new Level(nextLevel, getKarpfenGame()));
								}
						}
					}
				} // end for each Entity
			} // end if there are Entities at all
			
			

		}


		public void draw(Graphics2D g2) {
			player.draw(g2, xOffset, yOffset);

			for (int w = 0; w < walls.size(); w++) {
				Wall wall = (Wall) walls.get(w);
				wall.draw(g2, xOffset,yOffset);
			}
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

			
		public void die() {
			resetLevel();
			getKarpfenGame().setLvl(new BossLevel(thisLevel, getKarpfenGame()));
		}
}
