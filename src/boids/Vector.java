package boids;

import java.awt.Point;

public class Vector extends Point {



	public Vector(int x, int y) {

		super(x, y);
	}



	public static Vector add(Vector v1, Vector v2) {


		Vector sum = new Vector(v1.x + v2.x, v1.y + v2.y);
		return sum;

	}

	public void add(Vector v) {

		this.x = this.x + v.x;
		this.y = this.y + v.y;


	}

	public static Vector substract(Vector v1, Vector v2) {


		Vector sum = new Vector(v1.x - v2.x, v1.y - v2.y);
		return sum;

	}

	public void substract(Vector v) {

		this.x = this.x - v.x;
		this.y = this.y - v.y;


	}

	public void divide(int d) {

		this.x = this.x / d;
		this.y = this.y / d;
	}

	public static Vector divide(Vector v, int d) {

		v.x = v.x / d;
		v.y = v.y / d;
		return v;
	}

//	public double distance(Vector v) {
//
//		return this.distance(v);
//
//
//	}
//
//	public static double distance(Vector v1, Vector v2) {
//
//
//		return v1.distance(v2);
//
//	}

	public void set(Vector v) {

		this.x = v.x;
		this.y = v.y;

	}

	public void reset() {

		this.x = 1;
		this.y = 1;

	}

	// Problème si deux boid sont superposés, seront considérés égaux pour toujours
	@Override
	public boolean equals(Object obj) {

		return (this.x == ((Vector)obj).x) && (this.y == ((Vector)obj).y);
	}






}
