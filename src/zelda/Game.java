package zelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	public static int Width = 480, Height = 480;
	public Player player;
	public World world;
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(Width, Height));
		new Spritesheet();
		player = new Player(32, 32);
		world = new World();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void tick() {
		player.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, Width, Height);
		
		
		player.render(g);
		world.render(g);
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame jframe = new JFrame();
		jframe.add(game);
		jframe.setTitle("Mini Zelda");
		jframe.pack();
		
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		new Thread(game).start();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		} else if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}