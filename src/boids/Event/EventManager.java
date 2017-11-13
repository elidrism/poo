package boids.Event;

import java.util.LinkedList;
import java.util.PriorityQueue;

import boids.Boid;
import boids.OvalMoving;
import gui.GUISimulator;
import gui.Oval;

public class EventManager extends SimulatorEvent {
	
	private long currentDate;
	private PriorityQueue<Event> listEvents = new PriorityQueue<Event> ();

	
	public EventManager(LinkedList<Boid> boids, GUISimulator gui) {
		super(gui, boids);
		this.currentDate = 0;
	}
	
	public void addEvent(Event e) {
		this.listEvents.add(e);
	}
	
	@Override
	public void next() {
		this.updateBoids();
		this.currentDate++;
		Event event;

		while (! this.isFinished() && listEvents.peek().getDate() <= this.currentDate) {
			event = listEvents.poll();
			event.execute();
		}
		
		Boid boid;
		Oval oval;
		for(int i = 0; i < super.getBoids().size(); i++) {
			boid = super.getBoids().get(i);
			oval = super.getOvals().get(i);
			oval.translate((boid.getPosition().x - oval.getX()),(boid.getPosition().y - oval.getY()));
		}
	}
	

	public boolean isFinished() {
		return listEvents.isEmpty();
	}
	
	@Override
	public void restart() {
		this.currentDate = 0;
		this.listEvents = new PriorityQueue<Event>();
		
		for (Boid b : super.getBoids()){
			System.out.println("RESTART :" + b.getPosition());
		}
		int i = 0;
		for (OvalMoving o : super.getOvals()){
			Boid b = super.getBoids().get(i);
			Oval o2 = super.getOvalsInit().get(i);
			i++;
			o.translate((o2.getX() - b.getPosition().x),(o2.getY() - b.getPosition().y));
		}


		LinkedList<Boid> init = new LinkedList<Boid>();
		i=0;
		for (Oval o : super.getOvalsInit()){
			Boid b1 = new Boid(o.getX(), o.getY());
			init.add(b1);
		}

		super.setBoids(init);
		
		/* A voir si besoin de relancer automatiquement sur "restart"
		 * next();
		 */
	}
	
	private void updateBoids() {
		for (Boid b : super.getBoids()) {
			long dateRuleEvent = currentDate++;
			EventRule eventRule = new EventRule(dateRuleEvent, this, b, super.getRulesManager());
			this.addEvent(eventRule);
		}
		for (Boid b : super.getBoids()) {
			long dateMovingEvent = currentDate+=2;
			EventMoving eventMoving = new EventMoving(dateMovingEvent, this, b, b.getNextVelocity());
			this.addEvent(eventMoving);
		}
	}
}
