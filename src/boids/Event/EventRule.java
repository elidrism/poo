package boids.Event;

import boids.Boid;
import boids.Vector;

public class EventRule extends Event {
	
	private Boid boid;
	private RulesManager rulesManager;


	public EventRule(long date, EventManager eventManager, Boid boid,
			RulesManager rulesManager) {
		super(date, eventManager);
		this.boid = boid;
		this.rulesManager = rulesManager;
	}


	@Override
	public void execute() {
		boid.getNextVelocity().reset();
		rulesManager.rule1(boid);
		rulesManager.rule2(boid);
		rulesManager.rule3(boid);
		rulesManager.rule4(boid);
	}

}
