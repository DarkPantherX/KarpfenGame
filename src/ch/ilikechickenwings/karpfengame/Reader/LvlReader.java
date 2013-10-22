package ch.ilikechickenwings.karpfengame.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.ilikechickenwings.karpfengame.Level;

public class LvlReader {

	public LvlReader(String str){
		Properties prop= new Properties();
		String str2 = "/res/"+str;
		try{
			InputStream in = getClass().getResourceAsStream(str2);
			prop.load(in);
			Level.xMax =Integer.parseInt(prop.getProperty("xMax"));
			Level.widthMu =Integer.parseInt(prop.getProperty("widthMu"));
			Level.dxMu =Integer.parseInt(prop.getProperty("dxMu"));
			Level.widthVar =Integer.parseInt(prop.getProperty("widthVar"));
			Level.dxVar=Integer.parseInt(prop.getProperty("dxVar"));
			Level.height =Integer.parseInt(prop.getProperty("height"));
			Level.dyVar =Integer.parseInt(prop.getProperty("dyVar"));
			Level.spawnWalkZombie =Integer.parseInt(prop.getProperty("spawnWalkZombie"));
			Level.spawnSeagull =Integer.parseInt(prop.getProperty("spawnSeagull"));
			Level.spawnHealthPack =Integer.parseInt(prop.getProperty("spawnHealthPack"));
			Level.spawnCoffee =Integer.parseInt(prop.getProperty("spawnCoffee"));
			Level.maxLife =Integer.parseInt(prop.getProperty("maxLife"));
			Level.maxCoffee =Integer.parseInt(prop.getProperty("maxCoffee"));
			String strin[] = prop.getProperty("enableSkill").split(",");
			for(int i=0;i<strin.length;i++){
			Level.enableSkill[i] =Boolean.valueOf(strin[i]);
			}
			Level.nextLevel =Integer.parseInt(prop.getProperty("nextLevel"));
			in.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
}
