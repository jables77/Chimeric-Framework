package com.chimeric.framework.physics;

import com.chimeric.framework.resource.Initializeable;

public class CollisionPredictedEvent<Resource extends Initializeable> {
	private float timeOfCollision;
	private Resource resource1;
	private Resource resource2;
	
	public CollisionPredictedEvent(Resource resource1, Resource resource2, float timeOfCollision) {
		this.timeOfCollision = timeOfCollision;
		this.resource1 = resource1;
		this.resource2 = resource2;
	}
	
	public float getTimeOfCollision() { return this.timeOfCollision; }	
	public Resource getResource1() { return this.resource1; }
	public Resource getResource2() { return this.resource2; }
}
