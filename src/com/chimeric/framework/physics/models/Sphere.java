package com.chimeric.framework.physics.models;

import com.chimeric.framework.math.Vector3d;

public class Sphere extends Circle {
	public Sphere(float radius) {
		super();
		
		this.velocity = new Vector3d(0, 0, 0);
		this.position = new Vector3d(0, 0, 0);
		this.acceleration = new Vector3d(0, 0, 0);
		this.radius = radius;
	}
	
	public Vector3d getPosition() {
		return (Vector3d) this.position;
	}
	
	public Vector3d getVelocity() {
		return (Vector3d) this.velocity;
	}
	
	public Vector3d getAcceleration() {
		return (Vector3d) this.acceleration;
	}
}
