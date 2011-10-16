package com.chimeric.framework.physics;

import android.util.FloatMath;

import com.chimeric.framework.physics.models.Circle;
import com.chimeric.framework.resource.ResourceBroker;
import com.chimeric.framework.resource.Worker;

public class CollisionManager {
	private Worker<? extends Circle, OuterLoopState> outerLoop;
	private Worker<? extends Circle, InnerLoopState> innerLoop;
	
	private OuterLoopState outerState;
	private InnerLoopState innerState;
	
	private CollisionPredictor collisionPredictor;
	private CollisionPredicted<? extends Circle> onCollisionPredicted;
	
	public CollisionManager() {
		this.collisionPredictor = new CollisionPredictor(3);
		this.outerState = new OuterLoopState();
		this.innerState = new InnerLoopState();
		
		outerLoop = new Worker<Circle, OuterLoopState>() {
			private OuterLoopState state;
			
			@Override
			public void workOn(ResourceBroker<Circle> broker, Circle itemOfWork, int index) {
				innerState.circle1 = itemOfWork;
				innerLoop.setState(innerState);
				//broker.synchronizedIteration(innerLoop, index + 1);
			}
			
			@Override
			public void setState(OuterLoopState value) {
				this.state = value;
			}
		};
		
		innerLoop = new Worker<Circle, InnerLoopState>() {
			private InnerLoopState state;
			
			@Override
			public void workOn(ResourceBroker<Circle> broker, Circle itemOfWork, int index) {
				float timeOfCollision = collisionPredictor.timeOfCollision(state.circle1, itemOfWork);
				if (timeOfCollision <= state.deltaTime) {
					CollisionPredictedEvent<?> event = new CollisionPredictedEvent<Circle>(state.circle1, itemOfWork, timeOfCollision);
					//onCollisionPredicted.collisionPredicted(event);
				}
			}

			@Override
			public void setState(InnerLoopState value) {
				this.setState(value);
			}
			
		};
	}
	
	public <T extends Circle> void checkCircleCircleCollisions(ResourceBroker<T> broker, float deltaTime, CollisionPredicted<T> onCollisionPredicted) {
		this.onCollisionPredicted = onCollisionPredicted;
		
		outerState.deltaTime = deltaTime;
		outerLoop.setState(outerState);
		//broker.synchronizedIteration(outerLoop);
	}
	
	class OuterLoopState {
		float deltaTime;
	}
	
	class InnerLoopState {
		float deltaTime;
		Circle circle1;
	}
}
