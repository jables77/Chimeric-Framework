package com.chimeric.framework.physics.models;

import com.chimeric.framework.math.Vector;
import com.chimeric.framework.math.Vector2d;
import com.chimeric.framework.physics.Moveable;

public class Rectangle implements Moveable {
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	
	protected float width;
	protected float height;
	
	public Rectangle(float width, float height) {
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);
		this.position = new Vector2d(0, 0);
		this.width = width;
		this.height = height;
	}
	
	protected Rectangle() {
		
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	@Override
	public Vector getVelocity() {
		return this.velocity;
	}

	@Override
	public Vector getAcceleration() {
		return this.acceleration;
	}

	@Override
	public Vector getPosition() {
		return this.position;
	}

	@Override
	public void initialize() {
		this.position.set(0);
		this.velocity.set(0);
		this.acceleration.set(0);
	}
}
