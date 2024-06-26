package zelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
	
	public int spd = 4, curnAnimation = 0, curnFrames = 0, targetFrames = 15, dir = 1;
	public boolean right, left, up, down, shoot = false;
	public static List<Bullet> bullets = new ArrayList<>();
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
			dir = 1;
			moved = true;
		} else if(left && World.isFree(x-spd, y)) {
			x -= spd;
			dir = -1;
			moved = true;
		}
		
		if(up && World.isFree(x, y-spd)) {
			y -= spd;
			moved = true;
		} else if (down && World.isFree(x, y+spd)) {
			y += spd;
			moved = true;
		}
		
		if(moved) {
			this.curnFrames++;
			if(this.curnFrames == this.targetFrames) {
				this.curnFrames = 0;
				this.curnAnimation++;
				if(this.curnAnimation == Spritesheet.player_front.length) {
					this.curnAnimation = 0;
				}
			}
		}
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	public void render(Graphics g) {
		g.drawImage(Spritesheet.player_front[this.curnAnimation], x, y, 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}
