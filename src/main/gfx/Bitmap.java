package main.gfx;

public class Bitmap {
	
	protected int WIDTH, HEIGHT;
	protected int[] pixels;
	
	public Bitmap(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.pixels = new int[width * height];
	}
	
	

}
