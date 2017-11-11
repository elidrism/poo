package boids.Event;

import java.util.PriorityQueue;

public class EventManager {
	
	private long currentDate;
	private PriorityQueue<Event> listeEvenements = new PriorityQueue<Event> ();
	
	public EventManager() {
		this.currentDate = 0;
	}
	
	public void addEvent(Event e) {
		this.listeEvenements.add(e);
	}
	
	public void next() {
		Event e = listeEvenements.poll();
		
		if(e != null) {
			e.execute();
		}
	}
	
	public boolean isFinished() {
		return listeEvenements.isEmpty();
	}
	
	public void restart() {
		this.currentDate = 0;
		this.listeEvenements = new PriorityQueue<Event>();
		
		/* A voir si besoin de relancer automatiquement sur "restart"
		 * next();
		 */
	}

}
