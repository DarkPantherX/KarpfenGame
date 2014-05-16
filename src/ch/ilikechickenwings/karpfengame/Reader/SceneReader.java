package ch.ilikechickenwings.karpfengame.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.ilikechickenwings.karpfengame.Scene;
import ch.ilikechickenwings.karpfengame.Storyline;
import ch.ilikechickenwings.karpfengame.Tile;

public class SceneReader {
	public SceneReader(String str){
		Properties prop= new Properties();
		String str2 = "/res/"+str;
		
		try{
			InputStream in = getClass().getResourceAsStream(str2);
			prop.load(in);
			
			String stri[] = prop.getProperty("scenes").replaceAll("\\s+","").split(",");
			int x=0;
			System.out.println(stri.length/6);
			for(int i=0;i<stri.length/6;i++){
			Storyline.scenePics.add(new Scene(Tile.scenePics[Integer.parseInt(stri[x])],Integer.parseInt(stri[x+1]),Integer.parseInt(stri[x+2]),Integer.parseInt(stri[x+3]),Integer.parseInt(stri[x+4]),Integer.parseInt(stri[x+5])));
			x+=6;
			}
			

			Storyline.nextLevel =prop.getProperty("nextLevel");
			in.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}


