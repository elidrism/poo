import java.awt.Point;
import java.util.LinkedList;

public class TestBalls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Point> list = new LinkedList<Point>();
		list.add(new Point(5, 2));
		list.add(new Point(-2, 3));
		list.add(new Point(1, -4));
		Balls balles = new Balls(list);
		System.out.println(balles);
		balles.translate(3, 2);
		System.out.println(balles);
		balles.translate(-3, 8);
		System.out.println(balles);
		
	}

}
