package com.chimeric.framework.physics;

import com.chimeric.framework.math.Range;
import com.chimeric.framework.math.Vector;
import com.chimeric.framework.physics.models.Circle;

public class ReactionResolver {
	Vector testPosition;
	Vector velocityBefore;
	Vector velocityAfter;
	
	public ReactionResolver(int dimensions) {
		testPosition = new Vector(new float[dimensions]);
		velocityBefore = new Vector(new float[dimensions]);
		velocityAfter = new Vector(new float[dimensions]);
	}
	
	public void react(Circle circle, float restorationCoefficient, float timeOfCollision, float deltaTime) {
		float beforeCollision = timeOfCollision;
		float afterCollision = deltaTime - beforeCollision;
		
		testPosition.copyFrom(circle.getPosition());
		
		velocityBefore.copyFrom(circle.getVelocity());
		velocityBefore.normalize();
		velocityAfter.copyFrom(velocityBefore);

		velocityBefore.scale(timeOfCollision);
		testPosition.increment(velocityBefore);
		
		velocityAfter.scale(afterCollision * restorationCoefficient);
		testPosition.decrement(velocityAfter);
		
		velocityAfter.copyTo(circle.getVelocity());
		testPosition.copyTo(circle.getPosition());
	}
}
