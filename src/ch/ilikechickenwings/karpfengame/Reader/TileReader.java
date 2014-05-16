package ch.ilikechickenwings.karpfengame.Reader;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.ilikechickenwings.karpfengame.Tile;

public class TileReader {

	
	public TileReader(){
		Properties prop= new Properties();
		
		try{
			InputStream in = getClass().getResourceAsStream("/res/tiles.txt");
			prop.load(in);
			
			String stri[] = prop.getProperty("pictures").replaceAll("\\s+","").split(",");
			Tile.scenePics= new Image[stri.length];
			for(int i=0;i<stri.length;i++){
			Tile.scenePics[i]=Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/res/"+stri[i]+".png"));
			}
			
			in.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	
	
}
