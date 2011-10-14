package com.chimeric.framework.math;

public class Vector2d extends Vector {
	protected Vector2d(float[] dimensions) {
		super(dimensions);
	}

	public Vector2d(float x, float y) {
		super(new float[] {x, y});
	}
	
	public Vector2d() {
		this(0, 0);
	}
	
	public float getX() { return this.dimensions[0]; }
	
	public float getY() { return this.dimensions[1]; }
	
	public void setX(float value) { this.dimensions[0] = value; }
	
	public void setY(float value) {	this.dimensions[1] = value;	}
}
