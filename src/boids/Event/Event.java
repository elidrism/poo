package boids.Event;

public abstract class Event implements Comparable<Event> {
	
	protected long date;
	protected EventManager eventManager;

	public Event(long date, EventManager eventManager) {
		this.date = date;
		this.eventManager = eventManager;
	}

	public long getDate() {
		return this.date;
	}

	public abstract void execute();

	@Override
	public int compareTo(Event e) {
		if (e == null)
			throw new NullPointerException();

		return (int)(this.date - e.date);
	}
}
