package com.chimeric.framework.physics;

import com.chimeric.framework.math.Vector;

public class Mechanics {
	public void leapFrogAccelerator(Vector position, Vector velocity, Vector previousAcceleration, Vector acceleration, Vector tempVector, float deltaTime) {
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
