package conway;

import java.awt.Color;

import gui.GUISimulator;
import gui.Simulable;

public class ConwaySimulator implements Simulable {

	
	private Cell[][] cells;
	private Cell[][] cellsInit;
	private Cell[][] cellsNext;
	private int width;
	private int height;
	private GUISimulator gui;
	
	
	public ConwaySimulator(GUISimulator gui, Cell[][] cellsArg) {
		
		this.gui = gui;
		
		// Vérifier que tableau de dimension non nulle?
		// Gérer tablea trop grands pour rentrer dans simu?
		this.height = cellsArg.length;
		this.width = cellsArg[0].length;
		cells = new Cell[height][width];
		cellsInit = new Cell[height][width];
		cellsNext = new Cell[height][width];
		
		
		
		// Ajouter clone() à Cell ?
		boolean isAlive;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				isAlive = cellsArg[i][j].isAlive();
				
				Color colorFill = Color.WHITE;
				if (isAlive) {
					colorFill = Color.blue;
				}
				cells[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, colorFill, 10, isAlive);
				cellsInit[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, colorFill, 10, isAlive);
				gui.addGraphicalElement(cells[i][j]);
			}
		}
		
		
		
		
	}
	
	
	@Override
	public void next() {
		
		gui.reset();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int aliveN = 0;
				if (isNeighborAlive(i - 1, j - 1)) {
					aliveN++;
				}
				if (isNeighborAlive(i - 1, j)) {
					aliveN++;
				}
				if (isNeighborAlive(i - 1, j + 1)) {
					aliveN++;
				}
				if (isNeighborAlive(i, j - 1)) {
					aliveN++;
				}
				if (isNeighborAlive(i, j + 1)) {
					aliveN++;
				}
				if (isNeighborAlive(i + 1, j - 1)) {
					aliveN++;
				}
				if (isNeighborAlive(i + 1, j)) {
					aliveN++;
				}
				if (isNeighborAlive(i + 1, j + 1)) {
					aliveN++;
				}
				if (cells[i][j].isAlive()) {
					if ((aliveN == 2)||(aliveN == 3)) {
						cellsNext[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.BLUE, 10, true);
					}
					else {
						cellsNext[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.WHITE, 10, false);
					}
				}
				else {
					if (aliveN == 3) {
						cellsNext[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.BLUE, 10, true);
					}
					else {
						cellsNext[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.WHITE, 10, false);
					}
				}
				
			
			}
			
			
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if (cellsNext[i][j].isAlive()) {
					cells[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.BLUE, 10, true);
				}
				else {
					cells[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.white, 10, false);
					
				}
				gui.addGraphicalElement(cells[i][j]);
				
			}

		}
		
	}

	private boolean isNeighborAlive(int i, int j) {
		
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
		return cells[k][l].isAlive();
		
	}
	
	// factoriser copie de tableaux?
	
	@Override
	public void restart() {
		
		gui.reset();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if (cellsInit[i][j].isAlive()) {
					cells[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.BLUE, 10, true);
				}
				else {
					cells[i][j] = new Cell(j*10 + 5, i*10 + 5, Color.GRAY, Color.white, 10, false);
				}
				gui.addGraphicalElement(cells[i][j]);
				
				
			}

		}
		
	}

	
	
}
