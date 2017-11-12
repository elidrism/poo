package boids;




public class Boid {

	private Vector position;
	private Vector nextPosition;



	private Vector velocity;
	private Vector nextVelocity;


	public Boid(int x, int y) {


		this.position = new Vector(x, y);

		this.velocity = new Vector(0, 0);
		this.nextVelocity = new Vector(0,0);
	}


	@Override
	public boolean equals(Object obj) {

		return (this.position.equals(((Boid)obj).getPosition()) && this.velocity.equals(((Boid)obj).getVelocity()));

	}




	public Vector getPosition() {
		return position;
	}


	public void setPosition(Vector position) {
		this.position = position;
	}


	public Vector getNextPosition() {
		return nextPosition;
	}
//
//
	public void setNextPosition(Vector nextPosition) {
		this.nextPosition = nextPosition;
	}


	/**
	 * @return
	 */
	public Vector getVelocity() {
		return velocity;
	}


	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}


	public Vector getNextVelocity() {
		return nextVelocity;
	}


	public void setNextVelocity(Vector nextVelocity) {
		this.nextVelocity = nextVelocity;
	}

}
