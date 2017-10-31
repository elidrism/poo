package immigration;

import java.awt.Color;
import java.util.Random;


public class MultiCellGenerator {


	
	private MultiCell[][] cellsGenerated;
	
	public MultiCellGenerator(int width, int height, int statesNb) {
		

		cellsGenerated = new MultiCell[height][width];
		
		Random r = new Random();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				
				
				cellsGenerated[i][j] = new MultiCell(0, 0, 1, r.nextInt(statesNb) + 1);
					
				
				
			}
		}
		
	}
//
//	public MultiCellGenerator(int width, int height, Color[] colors) {
//		
//		cellsGenerated = new MultiCell[height][width];
//		Random r = new Random();
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width; j++) {
//				
//				int rCurr = r.nextInt(colors.length);
//				
//				cellsGenerated[i][j] = new MultiCell(0, 0, 1, rCurr + 1, colors[rCurr]);
//					
//				
//				
//			}
//		}
//		
//		
//		
//	}
	
	public MultiCell[][] getCellsGenerated() {
		return cellsGenerated;
	}
	
}
