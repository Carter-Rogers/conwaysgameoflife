package main.life;

public class Generation {

	public int[] newGenData;

	public Generation(int worldWidth, int worldHeight, int[] oldGenData) {
		this.newGenData = Gen(worldWidth, worldHeight, oldGenData);
	}

	public int[] Gen(int worldWidth, int worldHeight, int[] oldGenData) {
		this.newGenData = new int[oldGenData.length];

		for (int y = 0; y < worldHeight; y++) {
			int yPos = y;

			for (int x = 0; x < worldWidth; x++) {
				int xPos = x;

				int neighbors = 0;

				if (xPos > 0 && xPos < worldWidth) {
					if (oldGenData[(xPos - 1) + (yPos)] == 1) { // left cell neighbor
						neighbors = neighbors + 1;
					}

					if (yPos > 0) {

						if (oldGenData[(xPos - 1) + (yPos - 1)] == 1) { // top left cell neighbor
							neighbors = neighbors + 1;
						}
						
						if(oldGenData[(xPos + 1) + (yPos - 1)] == 1) { //top right cell neighbor
							neighbors = neighbors + 1;
						}
						
						if(oldGenData[xPos + (yPos - 1)] == 1) { //top cell neighbor
							neighbors = neighbors + 1;
						}

					}
					if (yPos < worldHeight) {

						if (oldGenData[(xPos - 1) + (yPos + 1)] == 1) { // bottom left cell neighbor
							neighbors = neighbors + 1;
						}
						
						if(oldGenData[xPos + (yPos + 1)] == 1) { //bottom cell neighbor
							neighbors = neighbors + 1;
						}
						
						if(oldGenData[(xPos + 1) + (yPos + 1)] == 1) { //bottom right cell neighbor
							neighbors = neighbors + 1;
						}

					}

				}

				if (neighbors == 2 || neighbors == 3 && oldGenData[x + y * worldWidth] == 1) { //surviving cell
					newGenData[x + y * worldWidth] = 1;
				}
				
				if(neighbors == 3 && oldGenData[x + y * worldWidth] == 0) { //cell birth
					newGenData[x + y * worldWidth] = 1;
				}
				
				if(neighbors < 2 || neighbors > 3) { //dead cell next gen
					newGenData[x + y * worldWidth] = 0;
				}

			}

		}

		return newGenData;
	}

}
