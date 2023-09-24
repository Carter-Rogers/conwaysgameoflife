package main.life;

import java.util.Random;

public class World {
	
	public int worldWidth, worldHeight;
	
	private int[] cell_grid;
	
	public World(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		this.cell_grid = new int[worldWidth * worldHeight];
		
		Random r = new Random();
		for(int i = 0; i < cell_grid.length; i++) {
			cell_grid[i] = r.nextInt(2);
		}
		
	}
	
	int tickCount = 0;
	boolean genUpdate = false;
	
	public void updateWorld() {
		tickCount++;
		
		if(tickCount == 60) {
			System.err.println("gen");
			
			Generation iteration = new Generation(worldWidth, worldHeight, cell_grid);
			
			cell_grid = iteration.newGenData.clone();
			genUpdate = true;
			
			tickCount = 0;
		}
	}

	public void renderWorld(int[] pixelData) {
		
		if(!genUpdate) {
			for(int y = 0; y < worldHeight; y++) {
				for(int x = 0; x < worldWidth; x++) {
					int cellState = cell_grid[x + y * worldWidth];
					
					if(cellState == 0) { //dead cell
						pixelData[x + y * worldWidth] = 0x0;
					}
					
					if(cellState == 1) { //alive cell
						pixelData[x + y * worldWidth] = 0xffff00ff;
					}
					
					
				}
			}
		}else {
			genUpdate = false;
			return;
		}
		
		
	}
	
	
	
}