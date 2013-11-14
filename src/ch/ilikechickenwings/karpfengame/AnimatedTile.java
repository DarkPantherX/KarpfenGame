package ch.ilikechickenwings.karpfengame;

import java.awt.image.BufferedImage;

public class AnimatedTile {
	
	public static BufferedImage[][] getAnimation(BufferedImage img, int row, int col, int width, int height){
		BufferedImage[][] animation = new BufferedImage[row][col];
		
		for (int i = 0; i < row; i++)
		{
		    for (int j = 0; j < col; j++)
		    {
		        animation[i][j] = img.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
		
		
		
		return animation;
	}

}
