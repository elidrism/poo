import java.awt.Point;
import java.util.LinkedList;

public class Balls {

	
	private LinkedList<Point> balles;
	private LinkedList<Point> ballesInit = new LinkedList<Point>();
	public Balls(LinkedList<Point> balles) {
		
		this.balles = balles;
		// POUR VOIR INIT, nécessaire de faire une copie de chaque point 
		for (Point p : balles) {

			this.ballesInit.add((Point)p.clone());
		}
		
	}
	
	public void translate(int dx, int dy) {
		
		for (Point b : balles) {
			
			b.translate(dx, dy);
		}
		
	}
	
	public void reInit() {
		
		for (int i = 0; i < balles.size(); i++) {
			
			this.balles.get(i).setLocation(ballesInit.get(i).getLocation());
		}
		
	}

	@Override
	public String toString() {
		
		String msg = "Positions des balles : \n";
		for (Point i : balles) {
			
			msg = msg + "(" + i.getX() + ", " + i.getY() + ") \n";
			
		}
		
		return msg;
		
	}
	
	// pas sûr de la sécu de ca, renvoyer la liste, bof bof. FAire copie ?
	public LinkedList<Point> getBallsList() {
		return this.balles;
	}

	
}
