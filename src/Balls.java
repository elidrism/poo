import java.awt.Point;
import java.util.LinkedList;

public class Balls {

	
	private LinkedList<Point> balles;
	private LinkedList<Point> ballesInit;
	public Balls(LinkedList<Point> balles) {
		
		this.balles = balles;
		this.ballesInit = balles;
		
	}
	
	void translate(int dx, int dy) {
		
		for (Point b : balles) {
			
			b.translate(dx, dy);
		}
		
	}
	
	void reInit() {
		
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
	
	

	
}
