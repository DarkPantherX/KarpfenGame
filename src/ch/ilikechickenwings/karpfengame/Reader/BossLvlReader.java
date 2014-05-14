package ch.ilikechickenwings.karpfengame.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.ilikechickenwings.karpfengame.BossLevel;
import ch.ilikechickenwings.karpfengame.BossWall;
import ch.ilikechickenwings.karpfengame.Level;

public class BossLvlReader {

	public BossLvlReader(String str){
		Properties prop= new Properties();
		String str2 = "/res/"+str;
		
		try{
			InputStream in = getClass().getResourceAsStream(str2);
			prop.load(in);
			Level.maxCoffee =Integer.parseInt(prop.getProperty("maxCoffee"));
			
			String stri[] = prop.getProperty("wall").replaceAll("\\s+","").split(",");
			int x=0;
			for(int i=0;i<stri.length/4;i++){
			BossLevel.walls.add(new BossWall(Integer.parseInt(stri[x]),Integer.parseInt(stri[x+1]),Integer.parseInt(stri[x+2]),Integer.parseInt(stri[x+3])));
			x=x+4;
			}
			
			
			String strin[] = prop.getProperty("enableSkill").split(",");
			for(int i=0;i<strin.length;i++){
			Level.useableSkill[i] =Boolean.valueOf(strin[i]);
			}
			Level.nextLevel =Integer.parseInt(prop.getProperty("nextLevel"));
			in.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}
