package main.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import main.life.World;

public class Display extends Canvas{

	public static final long serialVersionUID = 1L;
	public static final int WIDTH = 106, HEIGHT = 60, SCALE = 12;
	
	//Imaging
	protected BufferedImage display_image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	protected int[] pixels;
	
	private World world;
	
	public Display() {
		Dimension screensize = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setMinimumSize(screensize);
		this.setPreferredSize(screensize);
		this.setMaximumSize(screensize);
		this.setFocusable(true);
		
		pixels = ((DataBufferInt)display_image.getRaster().getDataBuffer()).getData();
		
		world = new World(106, 60);
		
		Random r = new Random();
		
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = r.nextInt();
		
		JFrame window = new JFrame("Conway's Game of Life");
		window.add(this);
		window.pack();
		window.setDefaultCloseOperation(3);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	public void update() {
		world.updateWorld();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0x0;
		world.renderWorld(pixels);

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(display_image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
	
}
