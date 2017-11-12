package boids;

import java.awt.Color;

import java.util.LinkedList;

import gui.GUISimulator;

public class TestBoids {


	public static void main(String[] args) {

		LinkedList<Boid> boids = new LinkedList<Boid>();
		GUISimulator gui = new GUISimulator(20000, 20000, Color.BLACK);
		boids.add(new Boid(243, 0));
		boids.add(new Boid(0, 257));
		boids.add(new Boid(100, 225));
		boids.add(new Boid(300,420));
		boids.add(new Boid(210,40));
		boids.add(new Boid(30,420));
		boids.add(new Boid(30,4));
		boids.add(new Boid(306,23));
		boids.add(new Boid(3,160));
		gui.setSimulable(new BoidSimulator(gui, boids));
	}
}
