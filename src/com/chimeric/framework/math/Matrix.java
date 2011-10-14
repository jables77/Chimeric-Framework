package com.chimeric.framework.math;

public abstract class Matrix {
	protected float[] values;

	protected Matrix(float[] matrixData) {
		this.values = matrixData;
	}
	
	public float[] getRawArray() {
		return values;
	}
}
