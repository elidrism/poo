package boids;

import java.awt.Color;
import java.util.LinkedList;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Oval;

public class BoidSimulator implements Simulable {


	// A TERME HASHMAP triée par découpage de la carte?

	private LinkedList<Boid> boids = new LinkedList<Boid>();



	private LinkedList<OvalMoving> ovals = new LinkedList<OvalMoving>();
	private LinkedList<Oval> ovalsInit = new LinkedList<Oval>();

	private GUISimulator gui;

	public LinkedList<Boid> getboidsList() {
		return this.boids;
	}

	// Positivité du déplacement en X
	private boolean posX = true;
	// Positivité du déplacement Y
	private boolean posY = true;

	public BoidSimulator(GUISimulator gui, LinkedList<Boid> boids) {
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
		// TODO Auto-generated method stub
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

	// RULES DANS BOID ?
	private void rule1(Boid b) {

		Vector perceivedCenter = new Vector(0, 0);
		int counter = 0;
		for (Boid bi : boids) {
			if (!b.equals(bi)) {

				// pcj = pcj + bi.position
				perceivedCenter.add(bi.getPosition());
				counter++;
			}
		}

		if (counter != 0) {
			// pcj = pcj / N
			perceivedCenter.divide(counter);

			// (pcj - bj.position) / 100 is added to b.nextVelocity
			// Division by 100 subjective -> means we move the vector 1% towards center of mass
			b.getNextVelocity().add(Vector.divide(Vector.substract(perceivedCenter, b.getPosition()), 10000));

		}


	}

	private void rule2(Boid b) {

		Vector oppositeForce = new Vector(0, 0);

		for (Boid bi : boids) {

			if (!b.equals(bi) && Math.abs(b.getPosition().distance(bi.getPosition())) < 100) {

				oppositeForce.substract(Vector.substract(b.getPosition(), bi.getPosition()));

			}

		}

		b.getNextVelocity().add(oppositeForce);

	}

	private void rule3(Boid b) {

		Vector perceivedVelocity = new Vector(0, 0);
		int counter = 0;
		for (Boid bi : boids) {
			if (!b.equals(bi)) {

				// pcj = pcj + bi.position
				perceivedVelocity.add(bi.getVelocity());
				counter++;
			}
		}

		if (counter != 0) {
			// pcj = pcj / N
			perceivedVelocity.divide(counter);


			// Division by 8 subjective
			b.getNextVelocity().add(Vector.divide(Vector.substract(perceivedVelocity, b.getVelocity()), 8));

		}

	}

	private void updateBoids() {

		for (Boid b : boids) {


			b.getNextVelocity().reset();
			this.rule1(b);
			this.rule2(b);
			this.rule3(b);

		}
		for (Boid b : boids) {

			b.getVelocity().add(b.getNextVelocity());
			b.getPosition().add(b.getVelocity());

		}

	}

}
