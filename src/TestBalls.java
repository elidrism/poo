import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import gui.GUISimulator;

public class TestBalls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Point> list = new LinkedList<Point>();
		list.add(new Point(15, 25));
		list.add(new Point(300, 239));
		list.add(new Point(450, 32));
		Balls balles = new Balls(list);
		System.out.println(balles);
		balles.translate(3, 2);
		System.out.println(balles);
		balles.translate(-3, 8);
		System.out.println(balles);
		balles.reInit();
		System.out.println("INIT");
		System.out.println(balles);
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		System.out.println("Simulateur de taille " + gui.getWidth() + " * " + gui.getHeight());
		gui.setSimulable(new BallsSimulator(gui, balles));
		
		
		
	}

}
