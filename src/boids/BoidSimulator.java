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
	// Barycentre des positions
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
		
		// pcj = pcj / N
		perceivedCenter.divide(counter);
		
		// (pcj - bj.position) / 100 is added to b.nextVelocity
		// Division by 100 subjective -> means we move the vector 1% towards center of mass
		b.getNextVelocity().add(Vector.divide(Vector.substract(perceivedCenter, b.getPosition()), 100));
		
	}
	
	// Non collisions
	private void rule2(Boid b) {
		
		Vector oppositeForce = new Vector(0, 0);
		
		for (Boid bi : boids) {
			
			if (!b.equals(bi) && Math.abs(b.getPosition().distance(bi.getPosition())) < 100) {
				
				oppositeForce.substract(Vector.substract(b.getPosition(), bi.getPosition()));
				
			}
			
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
	
}
