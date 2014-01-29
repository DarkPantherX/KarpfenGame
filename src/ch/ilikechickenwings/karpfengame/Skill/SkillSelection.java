package ch.ilikechickenwings.karpfengame.Skill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ch.ilikechickenwings.karpfengame.KarpfenGame;
import ch.ilikechickenwings.karpfengame.Level;
import ch.ilikechickenwings.karpfengame.Entity.Player;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Carp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Eel;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.FlyingCarp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.GiantCarp;
import ch.ilikechickenwings.karpfengame.Entity.Projectile.Grenade;
import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;

public class SkillSelection {

	private Player player;
	private int yOffset; // this variable makes it slowly disapear at the top...
	private final int width=KarpfenGame.WIDTH/(2*Skill.getNr());
	private int yvel=-80; // the speed at wich the frame goes up
	private int g = 3; // just for fun some acceleration :D
	
	private long[] currTimeSkill= new long[Skill.getNr()];
	private long[] oldTimeSkill= new long[Skill.getNr()];
	private boolean[] enableSkill;
	private int enabledSkill=0;
	private int timeVar;
	private long oldTime;
	
	
	
	public SkillSelection(Player player,boolean[] useableSkill){
		setyOffset(0);
		setPlayer(player);
		setEnableSkill(useableSkill);
		
	}
	
	public void update(InputHandler inHandler){
		
		// update GUI
		yvel=yvel+g;
		setyOffset(getyOffset()-yvel/100);
		
		
		// update Skills
		long currTime=System.currentTimeMillis();
		if(getPlayer().isWalking()){
			if(oldTime+200<currTime){
				if(timeVar==0){
					timeVar=1;
				}else{
					timeVar=0;
				}
				oldTime=currTime;
			}
		}
		
		//Skill
		for(int i=0;i<Skill.getNr();i++){
		    if(currTimeSkill[i]>=oldTimeSkill[i]+500){
			    enableSkill[i]=true;
		    }else{
			    currTimeSkill[i]=System.currentTimeMillis();
		    }
		}
		
		if(inHandler.getKeys()[KeyEvent.VK_1]){ // Carp
			enabledSkill=0;
			setyOffset(getWidth()*2);
		}else if(inHandler.getKeys()[KeyEvent.VK_2]){ // Eel
			enabledSkill=1;
			setyOffset(getWidth()*2);
		}else if(inHandler.getKeys()[KeyEvent.VK_3]){ // Karpocalypse
			enabledSkill=2;
			setyOffset(getWidth()*2);
		}else if(inHandler.getKeys()[KeyEvent.VK_4]){ // FlyingCarp
			enabledSkill=3;
			setyOffset(getWidth()*2);
		}else if(inHandler.getKeys()[KeyEvent.VK_5]){ // Grenade
			enabledSkill=4;
			setyOffset(getWidth()*2);
		}else if(inHandler.isTypedReady(KeyEvent.VK_Q)){ // switch weapon to the left
			enabledSkill=(enabledSkill+Skill.getNr()-1)%Skill.getNr();
			setyOffset(getWidth()*2);
		}else if(inHandler.isTypedReady(KeyEvent.VK_E)){ // switch weapon to the right
			enabledSkill=(enabledSkill+1)%Skill.getNr();
			setyOffset(getWidth()*2);
		}
		
		
		for(int i=0;i<Skill.getNr();i++){
			//The problem lies here... i debugged the game and the forbiddenskill always jumps back to true!
			if(inHandler.getKeys()[KeyEvent.VK_S]&&enableSkill[i]&&Level.useableSkill[i]&&enabledSkill==i){ // Carp
				Skill skill=(Skill) Level.getSkills()[i];
				if(getPlayer().getCoffee()>=skill.getCoffee()){
					getPlayer().setCoffee(getPlayer().getCoffee()-skill.getCoffee());
					enableSkill[i]=false;
					oldTimeSkill[i]=System.currentTimeMillis();
					switch(i){
					    case 0:
						SoundSystem.playSound(Sounds.playerShot);
					    Carp carp=new Carp(getPlayer().getX_Point()+(getPlayer().getWidth()*Math.max(0,getPlayer().getDir())),getPlayer().getY_Point()+getPlayer().getHeight()/2,getPlayer().getDir());
					    Level.getEntities().add(carp);
					    break;
					    case 1:
					    Eel eel=new Eel(getPlayer().getX_Point()+getPlayer().getWidth(),getPlayer().getY_Point()+getPlayer().getHeight()/2,getPlayer());
					    Level.getEntities().add(eel);
					    break;
					    case 2:
					    GiantCarp gc=new GiantCarp();
					    Level.getEntities().add(gc);
					    break;
					    case 3:
						FlyingCarp fc=new FlyingCarp(getPlayer());
						Level.getEntities().add(fc);
					    break;
					    case 4:
					    Grenade gr=new Grenade(getPlayer().getX_Point()+getPlayer().getWidth()/2,getPlayer().getY_Point(),getPlayer().getDir());
					    Level.getEntities().add(gr);
					    break;
					}
				}
			}
		}
	}
	
	public void draw(Graphics2D g2){
		if(getyOffset()>0){
			
			
		
		    g2.setColor(Color.CYAN);
		    g2.fillRect(0,getyOffset()-2*getWidth(),KarpfenGame.WIDTH-1, getWidth()*2);
		    
		    g2.setColor(Color.BLUE);
		    for(int i=0;i<Skill.getNr();i++){
		    	
		    	g2.drawRect(i*KarpfenGame.WIDTH/(Skill.getNr())+getWidth()/2, getyOffset()-getWidth(), getWidth(), getWidth());
		    	if(i==getEnabledSkill()){
		    		g2.setColor(Color.RED);
		    		g2.fillRect(i*KarpfenGame.WIDTH/(Skill.getNr())+getWidth()/2+5, getyOffset()-getWidth()+5, getWidth()-10, getWidth()-10);
		    		g2.setColor(Color.BLUE);
		    	}
		    }
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	

	public boolean[] getEnableSkill() {
		return enableSkill;
	}

	

	public int getWidth() {
		return width;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public void setEnableSkill(boolean[] useableSkill) {
		this.enableSkill = useableSkill.clone();
	}
	
	public int getEnabledSkill(){
		return enabledSkill;
	}
	
	public void setEnabledSkill(int enabledSkill){
		this.enabledSkill=enabledSkill;
	}
}
