package boids.Event;

public abstract class Event implements Comparable<Event> {
	
	protected long date;

	public Event(long date) {
		this.date = date;
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
