package boids.Event;

import java.awt.Color;
import java.util.LinkedList;

import boids.Boid;
import boids.OvalMoving;
import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

public class SimulatorEvent implements Simulable {
	
	// A TERME HASHMAP triée par découpage de la carte?

	private int width;
	private int height;
	
	private LinkedList<Boid> boids = new LinkedList<Boid>();

	private RulesManager rulesManager = new RulesManager(width, height, boids);
	

	private LinkedList<OvalMoving> ovals = new LinkedList<OvalMoving>();
	private LinkedList<Oval> ovalsInit = new LinkedList<Oval>();

	private GUISimulator gui;

	protected LinkedList<Oval> getOvalsInit() {
		return ovalsInit;
	}

	protected void setBoids(LinkedList<Boid> boids) {
		this.boids = boids;
	}

	// Positivité du déplacement en X
	private boolean posX = true;
	// Positivité du déplacement Y
	private boolean posY = true;

	public SimulatorEvent(GUISimulator gui, LinkedList<Boid> boids) {
		super();
		this.gui = gui;

		this.boids = boids;

		// Rectangle r1 = new Rectangle(10000, 10000, Color.WHITE, Color.BLACK, 20000, 20000);
		// gui.addGraphicalElement(r1);

		OvalMoving o;
		for (Boid b : this.boids) {
			o = new OvalMoving((int)(b.getPosition().x), (int)(b.getPosition().y), Color.red, Color.red, 1, posX, posY);
			ovals.add(o);
			gui.addGraphicalElement(o);
			ovalsInit.add(new Oval((int)(b.getPosition().x), (int)(b.getPosition().y), Color.red, Color.red, 1));
		}


	}

	@Override
	public void next() {

		this.updateBoids();

		for (Boid b : boids) {
			System.out.println(b.getPosition());
		}
		int i = 0;
		Boid b;
		for (OvalMoving o : this.ovals){
			b = this.boids.get(i);
			i++;
			o.translate((b.getPosition().x - o.getX()),(b.getPosition().y - o.getY()));
		}
	}

	protected RulesManager getRulesManager() {
		return rulesManager;
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	  for (Boid b : boids){
			System.out.println("RESTART :" + b.getPosition());
		}
		int i = 0;
		for (OvalMoving o : this.ovals){
			Boid b = this.boids.get(i);
			Oval o2 = this.ovalsInit.get(i);
			i++;
			o.translate((o2.getX() - b.getPosition().x),(o2.getY() - b.getPosition().y));
		}


		LinkedList<Boid> init = new LinkedList<Boid>();
		i=0;
		for (Oval o : this.ovalsInit){
			Boid b1 = new Boid(o.getX(), o.getY());
			init.add(b1);
		}

		this.boids = init;



	}
	
	protected LinkedList<Boid> getBoids() {
		return boids;
	}

	protected LinkedList<OvalMoving> getOvals() {
		return ovals;
	}

	private void updateBoids() {

		for (Boid b : boids) {


			b.getNextVelocity().reset();
			rulesManager.rule1(b);
			rulesManager.rule2(b);
			rulesManager.rule3(b);

		}
		for (Boid b : boids) {

			b.getVelocity().add(b.getNextVelocity());
			b.getPosition().add(b.getVelocity());

		}

	}

}
