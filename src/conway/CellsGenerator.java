package conway;

import java.awt.Color;
import java.util.Random;

public class CellsGenerator {

	private int width;
	private int height;
	private Cell[][] cellsGenerated;
	
	public CellsGenerator(int width, int height) {
		
		this.width = width;
		this.height = height;
		cellsGenerated = new Cell[height][width];
		
		Random r = new Random();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if (r.nextBoolean()) {
					
					cellsGenerated[i][j] = new Cell(0, 0, Color.GRAY, Color.BLUE, 1, true);
					
				}
				else {
					cellsGenerated[i][j] = new Cell(0, 0, Color.GRAY, Color.white, 1, false);
				}
				
			}
		}
		
	}

	public Cell[][] getCellsGenerated() {
		return cellsGenerated;
	}
	
	
}
