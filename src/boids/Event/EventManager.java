package boids.Event;

import java.util.PriorityQueue;

import gui.Simulable;

public class EventManager implements Simulable {
	
	private long currentDate;
	private PriorityQueue<Event> listEvents = new PriorityQueue<Event> ();
	
	
	public EventManager() {
		this.currentDate = 0;
	}
	
	public void addEvent(Event e) {
		this.listEvents.add(e);
	}
	
	public void next() {
		this.incrementDate();
		this.updateDisplay();
	}
	
	private void updateDisplay() {
		// TODO Auto-generated method stub
		
	}

	public boolean isFinished() {
		return listEvents.isEmpty();
	}
	
	public void restart() {
		this.currentDate = 0;
		this.listEvents = new PriorityQueue<Event>();
		
		/* A voir si besoin de relancer automatiquement sur "restart"
		 * next();
		 */
	}
	
	private void incrementDate() {
		this.currentDate++;
		Event event;

		while (! this.isFinished() && listEvents.peek().getDate() <= this.currentDate) {
			event = listEvents.poll();
			event.execute();
		}
	}
	
	private void draw() {
		
	}

}
