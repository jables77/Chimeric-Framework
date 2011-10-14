package com.chimeric.framework.math;

import com.chimeric.framework.resource.Initializeable;

import android.util.FloatMath;

/**
 * @author JB
 *
 */
public class Vector implements Initializeable {
	protected float[] dimensions;
	
	/**
	 * 
	 * @param dimensions
	 */
	public Vector(float[] dimensions) {
		this.dimensions = dimensions;
	}
	
	/**
	 * 
	 * @param dimensionIndex
	 * @return
	 */
	public float getCoordinate(int dimensionIndex) {
		return this.dimensions[dimensionIndex];
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDimensionCount() {
		if (dimensions != null) {
			return this.dimensions.length;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	public Vector increment(Vector vector) {
		int dimensionsToIncrement = getLeastCommonDimensions(vector);
		
		for (int i = 0; i < dimensionsToIncrement; i++) {
			this.dimensions[i] += vector.dimensions[i];
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	public Vector decrement(Vector vector) {
		int dimensionsToDecrement = getLeastCommonDimensions(vector);
		
		for (int i = 0; i < dimensionsToDecrement; i++) {
			this.dimensions[i] -= vector.dimensions[i];
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	public Vector multiply(Vector vector) {
		int dimensionsToMultiply = getLeastCommonDimensions(vector);
		
		for (int i = 0; i < dimensionsToMultiply; i++) {
			this.dimensions[i] *= vector.dimensions[i];
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	public Vector divide(Vector vector) {
		int dimensionsToDivide = getLeastCommonDimensions(vector);
		
		for (int i = 0; i < dimensionsToDivide; i++) {
			this.dimensions[i] /= vector.dimensions[i];
		}
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector normalize() {
		float magnitude = FloatMath.sqrt(this.getMagnitudeSquared());
		
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i] /= magnitude;
		}
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getMagnitudeSquared() {
		if (this.dimensions == null) {
			return 0;
		}
		
		float result = 0;
		for (int i = 0; i < this.dimensions.length; i++) {
			result += (this.dimensions[i] * this.dimensions[i]);
		}
		
		return result;
	}
	
	public float dotProduct(Vector other) throws VectorOperationException {
		int dimensionCount = this.dimensions.length;
		
		if (dimensionCount != other.dimensions.length) {
			throw new VectorOperationException("Vectors must have the same number of dimensions to calculate dot product.");
		}
		
		float result = 0;
		for (int i = 0; i < dimensionCount; i++) {
			result += (this.dimensions[i] * other.dimensions[i]);
		}		
		
		return result;
	}
	
	/**
	 * Scales the magnitude of the vector.
	 * @param scalar
	 * @return
	 */
	public Vector scale(float scalar) {
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i] *= scalar;
		}
		return this;
	}
	
	/**
	 * Copies vector value from this vector to another vector.
	 * Will only copy dimensions if they exist in the output vector.
	 * @param destination
	 */
	public void copyTo(Vector destination) {
		int dimensionsToCopy = getLeastCommonDimensions(destination);
		
		for (int i = 0; i < dimensionsToCopy; i++) {
			destination.dimensions[i] = this.dimensions[i];
		}
	}
	
	public void copyTo(float[] destination) {
		int dimensionsToCopy = getLeastCommondimensions(destination);
		
		for (int i = 0; i < dimensionsToCopy; i++) {
			destination[i] = this.dimensions[i];
		}		
		
	}
	
	public void copyFrom(Vector source) {
		int dimensionsToCopy = getLeastCommonDimensions(source);
		
		for (int i = 0; i < dimensionsToCopy; i++) {
			this.dimensions[i] = source.dimensions[i];
		}
	}
	
	public void copyFrom(float[] source) {
		int dimensionsToCopy = getLeastCommondimensions(source);
		
		for (int i = 0; i < dimensionsToCopy; i++) {
			this.dimensions[i] = source[i];
		}		
	}
	
	/**
	 * The actual internal array used for the vector.
	 * @return
	 */
	public float[] getRawArray() {
		return this.dimensions;
	}
	
	public int getLeastCommonDimensions(Vector other) {
		int otherSize = other.dimensions.length;
		int localSize = this.dimensions.length;
		
		return (localSize <= otherSize) ? localSize : otherSize;
	}
	
	public int getLeastCommondimensions(float[] rawValues) {
		int otherSize = rawValues.length;
		int localSize = this.dimensions.length;
		
		return (localSize <= otherSize) ? localSize : otherSize;
	}

	@Override
	public void initialize() {
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i] = 0;
		}
	}
}