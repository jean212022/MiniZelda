package zelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inimigo extends Rectangle {
	
	public int spd = 2, curnAnimation = 0, curnFrames = 0, targetFrames = 15, dir = 1;
	public int right = 1, left = 0, up = 0, down = 0;
	boolean shoot = false;
	public static List<Bullet> bullets = new ArrayList<>();
	
	public Inimigo(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void perseguirPlayer() {
		Player p = Game.player;
		if(x < p.x && World.isFree(x+spd, y)) {
			if(new Random().nextInt(100) < 50)
				x += spd;
		} else if (x > p.x && World.isFree(x-spd, y)) {
			if(new Random().nextInt(100) < 50)
				x -= spd;
		}
		
		if(y < p.y && World.isFree(x, y+spd)) {
			if(new Random().nextInt(100) < 50)
				y += spd;
		} else if(y > p.y && World.isFree(x, y-spd)) {
			if(new Random().nextInt(100) < 50)
				y -= spd;
		}
	}
	
	public void tick() {
		boolean moved = true;
		
		perseguirPlayer();
		
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