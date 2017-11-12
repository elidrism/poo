package boids;

import java.awt.Color;
import java.util.LinkedList;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class BoidSimulator implements Simulable {

	
	// A TERME HASHMAP triée par découpage de la carte?
	
	private LinkedList<Boid> boids = new LinkedList<Boid>();

	private GUISimulator gui;
	
	public BoidSimulator(GUISimulator gui) {
		super();
		this.gui = gui;
		
		Rectangle r1 = new Rectangle(250, 250, Color.WHITE, Color.BLACK, 500, 500);
		gui.addGraphicalElement(r1);
		
		
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}
	
	// RULES DANS BOID ?
	private void rule1(Boid b) {
		
		Vector perceivedCenter = new Vector(0, 0);
		int counter = 0;
		for (Boid bi : boids) {
			if (!b.equals(bi) && canSee(b, bi)) {
				
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
			b.getNextVelocity().add(Vector.divide(Vector.substract(perceivedCenter, b.getPosition()), 100));
			
		}
		

	}
	
	private void rule2(Boid b) {
		
		Vector oppositeForce = new Vector(0, 0);
		
		for (Boid bi : boids) {
			
			if (!b.equals(bi) && Math.abs(b.getPosition().distance(bi.getPosition())) < 100 && canSee(b, bi)) {
				
				oppositeForce.substract(Vector.substract(b.getPosition(), bi.getPosition()));
				
			}
			
		}
		
		b.getNextVelocity().add(oppositeForce);
		
	}
	
	private void rule3(Boid b) {
		
		Vector perceivedVelocity = new Vector(0, 0);
		int counter = 0;
		for (Boid bi : boids) {
			if (!b.equals(bi) && canSee(b, bi)) {
				
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
			
		}
		for (Boid b : boids) {
			
			b.getVelocity().add(b.getNextVelocity());
			b.getPosition().add(b.getVelocity());
			
		}
		 
	}
	
	// Can b see c ? 
	private boolean canSee(Boid b, Boid c) {
		
		// Jusqu'à quel angle un boid voit autour de lui
		double angleVision = Math.PI*3/2;
		// Jusqu'ou voit un boid autour de lui
		int distanceVision = 10;
		
		if (b.getPosition().distance(c.getPosition()) > distanceVision) {
			return false;
		}
		
		Vector bc = new Vector(c.getPosition().x - b.getPosition().x, c.getPosition().y - b.getPosition().y);
		
		double angle = Math.acos(Vector.scalProd(b.getVelocity(), bc)/(Vector.norme(b.getVelocity())*Vector.norme(bc)));
		
		if (angle > angleVision) 
			return false;
		else 
			return true;
		
		
	}
	
}
