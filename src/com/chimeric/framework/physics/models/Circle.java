package com.chimeric.framework.physics.models;

import com.chimeric.framework.physics.Moveable;
import com.chimeric.framework.math.Vector;
import com.chimeric.framework.math.Vector2d;

public class Circle implements Moveable {
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	
	protected float radius;
	
	public Circle(float radius) {
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);
		this.position = new Vector2d(0, 0);
		this.radius = radius;
	}
	
	protected Circle() {
		
	}
	
	public float getRadius() {
		return radius;
	}
	
	@Override
	public Vector2d getVelocity() {
		return (Vector2d) this.velocity;
	}

	@Override
	public Vector2d getAcceleration() {
		return (Vector2d) this.acceleration;
	}

	@Override
	public Vector2d getPosition() {
		return (Vector2d) this.position;
	}
}
