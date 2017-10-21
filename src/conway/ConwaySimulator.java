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
		
		
		
		// Ajouter toClone à Cell ?
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	
	
}
