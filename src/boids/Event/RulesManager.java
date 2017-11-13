package boids.Event;

import java.util.LinkedList;

import boids.Boid;
import boids.Vector;

public class RulesManager {
	
	public RulesManager(int width, int height, LinkedList<Boid> boids) {
		this.width = width;
		this.height = height;
		this.boids = boids;
	}

	private int width;
	private int height;
	private LinkedList<Boid> boids = new LinkedList<Boid>();

	
	// RULES DANS BOID ?
		// Barycentre des positions
		public void rule1(Boid b) {

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
				b.getNextVelocity().add(Vector.divide(Vector.substract(perceivedCenter, b.getPosition()), 10000));

			}


		}

		// Non collisions
		public void rule2(Boid b) {

			Vector oppositeForce = new Vector(0, 0);

			for (Boid bi : boids) {

				if (!b.equals(bi) && Math.abs(b.getPosition().distance(bi.getPosition())) < 100 && canSee(b, bi)) {

					
					oppositeForce.substract(Vector.substract(bi.getPosition(), b.getPosition()));
					

				}

			}

			b.getNextVelocity().add(oppositeForce);

		}

		public void rule3(Boid b) {

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

		
		public void rule4(Boid b) {
			
			Vector oppositeForce = new Vector(0, 0);
			
			int distanceToWall = 10;
			if (b.getPosition().x < distanceToWall) {
				
				oppositeForce.substract(Vector.substract(new Vector(0, 4*b.getPosition().y), b.getPosition()));
				
			}
			else if (b.getPosition().x > width - distanceToWall ) {
				
				oppositeForce.substract(Vector.substract(new Vector(4*width, 4*b.getPosition().y), b.getPosition()));

			}
			if (b.getPosition().y < distanceToWall) {
				
				oppositeForce.substract(Vector.substract(new Vector(4*b.getPosition().x, 0), b.getPosition()));
				
			}
			else if (b.getPosition().y > height - distanceToWall ) {
				
				oppositeForce.substract(Vector.substract(new Vector(4*b.getPosition().x, 4*height), b.getPosition()));

			}
				
			
			
			b.getNextVelocity().add(oppositeForce);
			
		}

		// Can b see c ?
		private boolean canSee(Boid b, Boid c) {

			// Jusqu'à quel angle un boid voit autour de lui
			double angleVision = Math.PI*3/4;
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
