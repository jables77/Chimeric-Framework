package com.chimeric.framework.physics.models;

import com.chimeric.framework.math.Vector3d;

public class Box extends Rectangle {
	private float depth;
	
	public Box(float width, float height, float depth) {
		super();
		
		this.velocity = new Vector3d(0, 0, 0);
		this.position = new Vector3d(0, 0, 0);
		this.acceleration = new Vector3d(0, 0, 0);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public float getDepth() {
		return this.depth;
	}
}
