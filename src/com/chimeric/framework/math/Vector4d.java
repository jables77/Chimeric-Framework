package com.chimeric.framework.math;

public class Vector4d extends Vector3d {
	protected Vector4d(float[] dimensions) {
		super(dimensions);
	}
	
	public Vector4d(float x, float y, float z, float w) {
		super(new float[] {x, y, z, w});
	}
	
	public Vector4d() {
		this(0, 0, 0, 0);
	}
	
	public float getW() { return this.dimensions[3]; }
	
	public void setW(float value) { this.dimensions[3] = value; }
}
