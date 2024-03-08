package zelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	public static int Width = 480, Height = 480;
	
	public Game() {
		this.setPreferredSize(new Dimension(Width, Height));
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
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		
		
		g.setColor(Color.red);
		g.fillRect(0, 0, 50, 50);
		
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
}