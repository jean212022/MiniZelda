package zelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	public static BufferedImage[] player_front, inimigo_front;
	public static BufferedImage spritesheet, tileWall;
	
	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
			tileWall = ImageIO.read(getClass().getResource("/bloco.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		player_front = new BufferedImage[2];
		player_front[0] = spritesheet.getSubimage(0, 11, 16, 16);
		player_front[1] = spritesheet.getSubimage(16, 11, 16, 16);
		inimigo_front = new BufferedImage[2];
		inimigo_front[0] = spritesheet.getSubimage(0, 11, 16, 16);
		inimigo_front[1] = spritesheet.getSubimage(16, 11, 16, 16);
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
