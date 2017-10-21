package conway;

import java.awt.Color;

import gui.Rectangle;

public class Cell extends Rectangle {

	public Cell(int x, int y, Color drawColor, Color fillColor, int height, boolean alive) {
	
		super(x, y, drawColor, fillColor, height);
		this.alive = alive;
		
	}
	private boolean alive;
	
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
	
	
	
}
