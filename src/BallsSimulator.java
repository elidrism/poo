

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

public class BallsSimulator implements Simulable {
	
	private LinkedList<OvalMoving> ovals = new LinkedList<OvalMoving>();
	private LinkedList<Oval> ovalsInit = new LinkedList<Oval>();
	private Balls balles;
	private GUISimulator gui;
	
	// Déplacement par pas en x en valeur absolue
	private int px = 10;
	// Déplacement par pas en Y en valeur absolue
	private int py = 10;
	// Positivité du déplacement en X
	private boolean posX = true;
	// Positivité du déplacement Y
	private boolean posY = true;
	
	// OBLIGE DE METTRE des valeurs de taille en dur et non des variables CAR LE SIMULATEUR NE CREE PAS A LA TAILLE DEMANDEE
	public BallsSimulator(GUISimulator gui, Balls b) {
		
		super();
		balles = b;
		this.gui = gui;
		System.out.println("Taille : " + gui.getWidth() + "*" + gui.getHeight());
		Rectangle r1 = new Rectangle(250, 250, Color.WHITE, Color.BLACK, 500, 500);
		gui.addGraphicalElement(r1);
		
//		Rectangle r2 = new Rectangle(gui.getWidth()/2, gui.getHeight()/2, Color.GREEN, Color.BLACK, gui.getWidth());
//		gui.addGraphicalElement(r2);
		
		
		
		OvalMoving o;
		for (Point p : balles.getBallsList()) {
			o = new OvalMoving((int)(p.getX()), (int)(p.getY()), Color.red, Color.red, 5, posX, posY);
			ovals.add(o);
			gui.addGraphicalElement(o);
			ovalsInit.add(new Oval((int)(p.getX()), (int)(p.getY()), Color.red, Color.red, 5));
		}
		
		
		
		
	}
	
	
	@Override
	public void next() {
		
		
		int dx;
		int dy;
		
// 		Inutilisable car fenêtre créée avec une taille aléatoire : 
//		int width = gui.getWidth();
//		int height = gui.getHeight();
		
		System.out.println("\n Position des balles :");
		
		
		for (OvalMoving o : ovals) {
			
			
			if (o.isDx() ) {
				
				if ((o.getX() + px <= 500)) {
					dx = px;
				}
				else {
					dx = -(px - (500 - o.getX()));
					o.setDx(false);
				}
			
			}
			
			else {
				if (o.getX() >= px) {
					dx = -px;
				}
				else {
					dx = o.getX() - px;
					o.setDx(true);
				}
			}
			if (o.isDy() ) {
				if ((o.getY() + py <= 500)) {
					dy = py;
				}
				else {
					dy = -(py - (500 - o.getY()));
					o.setDy(false);
				}
			
			}
			
			else {
				if (o.getY() >= py) {
					dy = -py;
				}
				else {
					dy = o.getY() - py;
					o.setDy(true);
				}
			}
		
			o.translate(dx, dy);
			
			System.out.println(o);
		}
		
	}

	@Override
	public void restart() {
		
		for (int i = 0; i < ovals.size(); i++) {
			OvalMoving o = ovals.get(i);
			Oval oInit = ovalsInit.get(i);
			o.translate(oInit.getX() - o.getX(), oInit.getY() - o.getY());
			o.setDx(true);
			o.setDy(true);
			
			
		}
		
	}
	
	

}
