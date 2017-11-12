package boids;
import java.awt.Color;

import gui.Oval;

// Classe créée pour rajouter l'information du sens de déplacement des ovales
// True = déplacement positif, false = déplacement négatif

public class OvalMoving extends Oval {

	public OvalMoving(int x, int y, Color drawColor, Color fillColor, int size, boolean dx, boolean dy) {
		super(x, y, drawColor, fillColor, size);
		this.dx = dx;
		this.dy = dy;
	}



	@Override
	public String toString() {

		return "(" + this.getX() + ", " + this.getY() + ")";
	}



	public boolean isDx() {
		return dx;
	}
	public void setDx(boolean dx) {
		this.dx = dx;
	}
	public boolean isDy() {
		return dy;
	}
	public void setDy(boolean dy) {
		this.dy = dy;
	}


	private boolean dx;
	private boolean dy;


}
