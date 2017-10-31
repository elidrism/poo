package immigration;

import gui.GUISimulator;
import gui.Simulable;

public class ImmigrationSimulator implements Simulable {

	private GUISimulator gui;
	private MultiCell[][] cellsCurr;
	private MultiCell[][] cellsNext;
	private MultiCell[][] cellsInit;
	private int height;
	private int width;
	
	private int statesNb;
	
	public ImmigrationSimulator(GUISimulator gui, MultiCell[][] cells) {
		
		this.gui = gui;	
		this.height = cells.length;
		this.width = cells[0].length;
		cellsCurr = new MultiCell[height][width];
		cellsNext = new MultiCell[height][width];
		cellsInit = new MultiCell[height][width];
		
		statesNb = 0; 
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int state = cells[i][j].getState();
				cellsCurr[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				cellsInit[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				gui.addGraphicalElement(cellsCurr[i][j]);
				
				// MAJ DU NB DE STATES SUR LE STATE MAX
				if (state > statesNb) {
					statesNb = state;
				}
			}
		}
	}
	
	@Override
	public void next() {
		
		gui.reset();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
		
				int NeighborCounter = 0;
				int stateCurr = cellsCurr[i][j].getState();
				if (getNeighborState(i - 1, j - 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i - 1, j) == (stateCurr + 1) % statesNb ) {
					NeighborCounter++;
				}
				if (getNeighborState(i - 1, j + 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i, j - 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i, j + 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i + 1, j - 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i + 1, j) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				if (getNeighborState(i + 1, j + 1) == (stateCurr + 1) % statesNb) {
					NeighborCounter++;
				}
				
				if (NeighborCounter >= 3) {
					cellsNext[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, (stateCurr + 1) % statesNb);
				}
				else {
					cellsNext[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, stateCurr);
				}
				
			}
		}	
		

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				
				cellsCurr[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, cellsNext[i][j].getState());
				gui.addGraphicalElement(cellsCurr[i][j]);
				
				
				
			}

		}
		
		
	}

	private int getNeighborState(int i, int j) {
		
		int k = i;
		if (i == -1) {
			k = height - 1;
		}
		else if (i == height) {
			k = 0;
		}
		
		int l = j;
		if (j == -1) {
			l = width - 1;
		}
		else if (j == width) {
			l = 0;
		}
		return cellsCurr[k][l].getState();
		
	}
	
	@Override
	public void restart() {
		
		gui.reset();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
		
				cellsCurr[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, cellsInit[i][j].getState());
				gui.addGraphicalElement(cellsCurr[i][j]);
			}
		}
		
	}

}
