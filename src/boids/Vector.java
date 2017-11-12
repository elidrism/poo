package boids;

import java.awt.Point;

public class Vector extends Point {

	
	
	public Vector(int x, int y) {
		
		super(x, y);
		this.reduce();
	}

	
	
	public static Vector add(Vector v1, Vector v2) {
		
		
		Vector sum = new Vector(v1.x*v2.y + v1.y*v2.x, v1.y * v2.y);
		sum.reduce();
		return sum;
		
	}
	
	public void add(Vector v) {
		
		this.x = this.x*v.y + this.y*v.x;
		this.y = this.y*v.y;
		this.reduce();
		
		
	}
	
	public static Vector substract(Vector v1, Vector v2) {
		
		
		Vector sum = new Vector(v1.x*v2.y - v1.y*v2.x, v1.y * v2.y);
		sum.reduce();
		return sum;
		
	}
	
	public void substract(Vector v) {
		
		this.x = this.x*v.y - this.y*v.x;
		this.y = this.y*v.y;
		this.reduce();
		
		
	}
	
	public void divide(int d) {
		
		this.y = this.y * d;
		this.reduce();
	}
	
	public static Vector divide(Vector v, int d) {
		
		v.y = v.y * d;
		v.reduce();
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
		
		this.x = 0;
		this.y = 0;
		
	}
	
	// TODO
	private void reduce() {
		
		int pgcd = Vector.pgcd(this.x, this.y);
		this.x = this.x/pgcd;
		this.y = this.y/pgcd;
		
	}
	
	private static int pgcd(int i, int j) {
		
		if (j == 0)
			return i;
		else 
			return Vector.pgcd(j, i%j);
		
		
	}
	
	// Problème si deux boid sont superposés, seront considérés égaux pour toujours
	@Override
	public boolean equals(Object obj) {
		
		return (this.x == ((Vector)obj).x) && (this.y == ((Vector)obj).y);
	}

	

	
	
	
}
