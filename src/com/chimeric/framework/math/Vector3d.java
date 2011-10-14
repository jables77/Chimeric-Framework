package com.chimeric.framework.math;


public class Vector3d extends Vector2d {
	protected Vector3d(float[] dimensions) {
		super(dimensions);
	}
	
	public Vector3d(float x, float y, float z) {
		super(new float[] {x, y, z});
	}
	
	public Vector3d() {
		this(0, 0, 0);
	}
	
	public float getZ() { return this.dimensions[2]; }
	
	public void setZ(float value) { this.dimensions[2] = value; }
	
	public void crossProduct(Vector3d other, Vector3d result) throws VectorOperationException {
		int dimensionCount = this.dimensions.length;
		
		if (dimensionCount != other.dimensions.length) {
			throw new VectorOperationException("Vectors must have the same number of dimensions to calculate cross product.");
		}

		if (dimensionCount != 3) {
			throw new VectorOperationException("Vectors must have exactly 3 dimensions to calculate cross product.");
		}
		
		//Will be faster to use actual dimensions array instead of getters
		//result.setX(this.getY() * other.getZ() - this.getZ() * other.getY());
		//result.setY(this.getZ() * other.getX() - this.getX() * other.getZ());
		//result.setZ(this.getX() * other.getY() - this.getY() * other.getX());		

		result.dimensions[0] = (this.dimensions[1] * other.dimensions[2] - this.dimensions[2] * other.dimensions[1]);
		result.dimensions[1] = (this.dimensions[2] * other.dimensions[0] - this.dimensions[0] * other.dimensions[2]);
		result.dimensions[2] = (this.dimensions[0] * other.dimensions[1] - this.dimensions[1] * other.dimensions[0]);		
	}
}
