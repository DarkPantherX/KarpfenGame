package ch.ilikechickenwings.karpfengame;

public class CommandExecutor {

	public CommandExecutor(String str, Level lv){
		if(str.equals("kill")){
			lv.player.setLifes(0);
		}else if(str.equals("reset")){
			lv.getKarpfenGame().setLvl(new Level(1,lv.getKarpfenGame()));
		}else if(str.equals("clearcoffe")){
			lv.player.setCoffee(0);
		}else if(str.equals("fillup")){
			lv.player.setCoffee(100);
			lv.player.setLifes(100);
		}
		
	}
	
	
}
