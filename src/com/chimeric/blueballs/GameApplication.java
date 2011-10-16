package com.chimeric.blueballs;

import android.app.Application;
import com.chimeric.framework.input.*;
import com.chimeric.framework.physics.CollisionManager;
import com.chimeric.framework.physics.CollisionPredictor;

public class GameApplication extends Application {
	// Handlers are used for all the screens, not just one screen
	private AccelerometerHandler accelerometerHandler;
	private TouchHandler touchHandler;
	private CollisionPredictor collisionPredictor;
	
	private CollisionManager collisionManager;
	
	public AccelerometerHandler getAccelerometerHandler() { return this.accelerometerHandler; }
	public TouchHandler getTouchHandler() { return this.touchHandler; }
	public CollisionPredictor getCollisionPredictor() { return this.collisionPredictor; }
	public CollisionManager getCollisionManager() { return this.collisionManager; }
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		this.accelerometerHandler = new AccelerometerHandler(this);
		this.touchHandler = new TouchHandler(10);
		this.collisionPredictor = new CollisionPredictor(3);
		this.collisionManager = new CollisionManager();
	}
}
