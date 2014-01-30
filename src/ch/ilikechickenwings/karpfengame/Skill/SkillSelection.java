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
import ch.ilikechickenwings.karpfengame.Handler.Timer;
import ch.ilikechickenwings.karpfengame.Sound.SoundSystem;
import ch.ilikechickenwings.karpfengame.Sound.Sounds;

public class SkillSelection {

	private Player player;
	private int yOffset; // this variable makes it slowly disapear at the top...
	private final int width=KarpfenGame.WIDTH/(2*Skill.getNr());
	private int yvel=10; // the speed at wich the frame goes up
	private Timer drawTimer;
	private int drawTimerWait = 800; // millisec
	
	private Timer[] skillTimer = new Timer[Skill.getNr()];
	private int skillTimerWait = 500; // Millisec
	private boolean[] enableSkill;
	private int enabledSkill=0;
	
	
	
	public SkillSelection(Player player,boolean[] useableSkill){
		setyOffset(0);
		setPlayer(player);
		setEnableSkill(useableSkill);
		for(int i=0;i<Skill.getNr();i++){
			skillTimer[i]=new Timer(skillTimerWait);
		}
		
	}
	
	public void update(InputHandler inHandler){
		
		// update GUI
		if(yOffset>0 && drawTimer.isReady()){
			setyOffset(getyOffset()-yvel);
		}
		
		// Select Skill
		if(inHandler.getKeys()[KeyEvent.VK_1]){ // Carp
			enabledSkill=0;
			dropDown();
		}else if(inHandler.getKeys()[KeyEvent.VK_2]){ // Eel
			enabledSkill=1;
			dropDown();
		}else if(inHandler.getKeys()[KeyEvent.VK_3]){ // Karpocalypse
			enabledSkill=2;
			dropDown();
		}else if(inHandler.getKeys()[KeyEvent.VK_4]){ // FlyingCarp
			enabledSkill=3;
			dropDown();
		}else if(inHandler.getKeys()[KeyEvent.VK_5]){ // Grenade
			enabledSkill=4;
			dropDown();
		}else if(inHandler.isTypedReady(KeyEvent.VK_Q)){ // switch weapon to the left
			enabledSkill=(enabledSkill+Skill.getNr()-1)%Skill.getNr();
			dropDown();
		}else if(inHandler.isTypedReady(KeyEvent.VK_E)){ // switch weapon to the right
			enabledSkill=(enabledSkill+1)%Skill.getNr();
			dropDown();
		}
		
		// Use Skills
		for(int i=0;i<Skill.getNr();i++){
			if(inHandler.getKeys()[KeyEvent.VK_S]&&skillTimer[i].isReady()&&Level.useableSkill[i]&&enabledSkill==i){ // Carp
				Skill skill=(Skill) Level.getSkills()[i];
				if(getPlayer().getCoffee()>=skill.getCoffee()){
					getPlayer().setCoffee(getPlayer().getCoffee()-skill.getCoffee());
					skillTimer[i]=new Timer(skillTimerWait);
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
	
	private void dropDown(){
		setyOffset(getWidth()*2);
		drawTimer= new Timer(drawTimerWait);
	}
	
	// Getters and Setters

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
