package schelling;

import java.awt.Point;
import java.util.LinkedList;

import gui.GUISimulator;
import gui.Simulable;
import immigration.MultiCell;

public class SchellingSimulator implements Simulable {

	private int limit;
	private int statesNb;
	private MultiCell[][] cells;
	private MultiCell[][] cellsNext;
	private MultiCell[][] cellsInit;
	
	private LinkedList<Point> vacant = new LinkedList<Point>();
	private LinkedList<Point> vacantCurr = new LinkedList<Point>();
	
	private GUISimulator gui;
	
	private int height;
	private int width;
	
	
	public  SchellingSimulator(GUISimulator gui, MultiCell[][] cellsArg, int K, int n) {
		
		this.limit = K;
		this.statesNb = n;
		this.height = cellsArg.length;
		this.width = cellsArg[0].length;
		
		this.gui = gui;
		
		this.cells = new MultiCell[height][width];
		this.cellsNext = new MultiCell[height][width];
		this.cellsInit = new MultiCell[height][width];
		 
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int state = cellsArg[i][j].getState();
				this.cells[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				this.cellsInit[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				
				if (state == n + 1) {
					vacant.add(new Point(j, i));
				}
				
				gui.addGraphicalElement(cells[i][j]);
			}
		}
		
		
	}
	
	
	@Override
	public void next() {
		
		this.gui.reset();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				System.out.println("cell " + i + ", " + j);
				
				// Si ce n'est pas une maison vide
				if (cells[i][j].getState() != statesNb + 1) {
					
					int NeighborCounter = 0;
					int stateCurr = cells[i][j].getState();
					int NeighborState;
					if (((NeighborState = (getNeighborState(i - 1, j - 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i - 1, j))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i - 1, j + 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i, j - 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i, j + 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i + 1, j - 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i + 1, j))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
					if (((NeighborState = (getNeighborState(i + 1, j + 1))) != stateCurr) && (NeighborState != (this.statesNb + 1))) {
						NeighborCounter++;
					}
				
					System.out.println("Neighbors = " + NeighborCounter);
					
					// Si la maison a trop de voisins diff et qu'il y a des maisons vides.
					if (NeighborCounter > this.limit && !vacant.isEmpty()) {
						
						
						Point newHouse = vacant.poll();
						System.out.println("Déménage à " + newHouse.x + ", " + newHouse.y);
						cellsNext[newHouse.y][newHouse.x] = new MultiCell(newHouse.y*10 + 5, newHouse.x*10 + 5, 10, stateCurr);
						cellsNext[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, statesNb + 1);
						vacantCurr.add(new Point(j, i));
						
					}
					else {
						
						cellsNext[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, stateCurr);
						
					}
					
				}
				// Si c'est une maison vide, on ne fait rien, elle a peut-être été remplie par un précédent
				else {
					
					
					
				}
				
			}
			
			
			
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				
				if (cellsNext[i][j] != null) {
					
					int state = cellsNext[i][j].getState();
					this.cells[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				
				}
				// Ce cas arrive lorsque la maison en question était vide et n'a pas été remplie suite à un déménagement.
				else {
					
					cells[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, statesNb + 1);
					
				}
				
				
				
				
				
				
				gui.addGraphicalElement(cells[i][j]);
			}
		}
		
		
		while (!vacantCurr.isEmpty()) {
		
			vacant.add(vacantCurr.poll());
			
		}
		
		
	}

	@Override
	public void restart() {
	
		gui.reset();
		vacant.clear();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int state = cellsInit[i][j].getState();
				this.cells[i][j] = new MultiCell(j*10 + 5, i*10 + 5, 10, state);
				
				
				if (state == statesNb + 1) {
					vacant.add(new Point(j, i));
				}
				
				gui.addGraphicalElement(cells[i][j]);
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
		return cells[k][l].getState();
		
	}
	
}
