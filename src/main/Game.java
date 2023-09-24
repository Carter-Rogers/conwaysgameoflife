package main;

import main.gfx.Display;

public class Game implements Runnable {
	
	public static final double UPDATE_CAP = 60.0D; //60 updates per second
	

	private Thread gameThread;
	private boolean isRunning = false;
	
	private Display display;
	
	private void init() {
		display = new Display();
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();

	}

	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			gameThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public void update() {
		display.update();
	}
	
	public void render() {
		display.render();
	}

	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000/60.0D;
		double delta = 0;
		
		int frames = 0, ticks = 0;
		
		while(isRunning) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;
			if(delta >= 1.0D) {
				update();
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			while(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("frames: " + frames + " | updates: " + ticks);
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}

}
