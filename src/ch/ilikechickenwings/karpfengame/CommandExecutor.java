package ch.ilikechickenwings.karpfengame;

import ch.ilikechickenwings.karpfengame.Level.Level;

public class CommandExecutor {

	public CommandExecutor(String[] str2, Level lv){
		boolean sec=str2.length>1;
		
		if(str2[0].equals("player")){
			if(sec){
				if(str2[1].equals("getHealth")){
					System.out.println("Health: "+ lv.player.getLifes());
				}else if(str2[1].equals("kill")){
			lv.player.setLifes(0);
			System.out.println("Killed Player");
				}
			}
		}else if(str2[0].equals("clear")){
			if(sec){
			if(str2[1].equals("level")){
			lv.getKarpfenGame().setLvl(new Level(Level.thisLevel,lv.getKarpfenGame()));
			System.out.println("Level has been reset");
			}else if(str2[1].equals("entities")){
				Level.entities.clear();
				System.out.println("Entities has been reset");
			}else if(str2[1].equals("walls")){
				Level.walls.clear();
				System.out.println("Walls has been reset");
			}
			}
		}else if(str2[0].equals("clearcoffee")){
			lv.player.setCoffee(0);
			System.out.println("Coffe is now empty!");
		}else if(str2[0].equals("fillup")){
			if(sec){
			if(str2[1].equals("coffee")){
			lv.player.setCoffee(100);
			System.out.println("Filled up coffee");
			}else if(str2[1].equals("life")){
			lv.player.setLifes(100);
			System.out.println("Filled up life");
			}
				lv.player.setCoffee(100);
				lv.player.setLifes(100);
			System.out.println("Filled up player");
			
			}
			
		}else if(str2[0].equals("help")){
			System.out.println("List of Commands:" +"\n"+"kill"+"\n"+"clear <level/entities/walls>"+"\n"+"fillup <coffee/health>"+ "\n"+"playerhealth"+"\n"+"playercoffee <coffee/health>" );
		}
	
	}
}
