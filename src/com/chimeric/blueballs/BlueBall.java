package com.chimeric.blueballs;

import android.graphics.Color;
import android.util.FloatMath;

import com.chimeric.framework.math.Axis;
import com.chimeric.framework.math.Vector3d;
import com.chimeric.framework.physics.LeapFrogMover;
import com.chimeric.framework.physics.Moveable;
import com.chimeric.framework.physics.models.Sphere;
import com.chimeric.framework.resource.Initializeable;
import com.chimeric.framework.state.Updateable;

public class BlueBall
extends Sphere
implements Updateable, Initializeable {
	private LeapFrogMover mover;	
	private int color;
	private float force;
	private float lifeTime;
	
	private int ownedByPointerId;
	
	public BlueBall(float radius) {
		super(radius);
		
		this.mover = new LeapFrogMover(3);
		this.force = 15;
	}

	public float getForce() { return this.force; }
	public void setForce(float value) { this.force = value; }

	public int getColor() { return this.color; }
	public void setColor(int value) { this.color = value; }
	
	public int getOwnedByPointerId() { return this.ownedByPointerId; }
	public void setOwnedByPointerId(int value) { this.ownedByPointerId = value; }
	
	@Override
	public void update(float deltaTime) {
		this.mover.move(this, deltaTime, this.force);
		
		this.lifeTime -= deltaTime;
		if (this.lifeTime <= 0) {
			//this.kill();
		}
	}

	@Override
	public void initialize() {
		this.position.initialize();
		this.velocity.initialize();
		this.acceleration.initialize();
		this.color = Color.BLUE;
		this.lifeTime = 3;
	}
}
