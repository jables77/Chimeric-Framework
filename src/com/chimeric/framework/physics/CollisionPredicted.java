package com.chimeric.framework.physics;

import com.chimeric.framework.resource.Initializeable;

public interface CollisionPredicted<Resource extends Initializeable> {
	public void collisionPredicted(CollisionPredictedEvent<? extends Resource> e);
}
