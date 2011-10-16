package com.chimeric.framework.physics;

import com.chimeric.framework.math.Vector;

public class LeapFrogMover {
	private Vector tempVector;
	private Vector previousAcceleration;

	public LeapFrogMover(int dimensions) {
		tempVector = new Vector(new float[dimensions]);
		previousAcceleration = new Vector(new float[dimensions]);
	}
	
	public void move(Moveable object, float deltaTime, float force) {
		Vector acceleration = object.getAcceleration();
		Vector position = object.getPosition();
		Vector velocity = object.getVelocity();
		
		acceleration.scale(force);
		
		velocity.copyTo(tempVector);
		tempVector.scale(deltaTime);	
		position.increment(tempVector);
		
		previousAcceleration.copyTo(tempVector);
		tempVector.scale(deltaTime * deltaTime / 2.0f);
		position.increment(tempVector);
		
		previousAcceleration.copyTo(tempVector);
		tempVector.increment(acceleration);
		tempVector.scale(deltaTime / 2.0f);
		tempVector.increment(velocity);
		tempVector.copyTo(velocity);
		
		acceleration.copyTo(previousAcceleration);
	}
}
