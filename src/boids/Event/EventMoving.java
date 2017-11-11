package boids.Event;

import boids.Boid;
import boids.Vector;

public class EventMoving extends Event {

	private Boid boid;
	
	private Vector position;
	
	public EventMoving(long date, Boid boid, Vector position) {
		super(date);
		this.boid = boid;
		this.position = position;
	}

	@Override
	public void execute() {
		System.out.println("Boid moving :" + this.position.toString());
		boid.setNextPosition(position);
	}

}
