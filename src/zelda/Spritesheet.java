package zelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	public static BufferedImage player_front, player_down, spritesheet, tileWall;
	
	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
			tileWall = ImageIO.read(getClass().getResource("/bloco.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		player_front = Spritesheet.getSprite(0, 11, 16, 16);
		player_down= Spritesheet.getSprite(19, 11, 16, 16);
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
