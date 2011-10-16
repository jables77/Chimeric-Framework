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
	 * @param dimensionIndex
	 * @param value
	 */
	public void setCoordinate(int dimensionIndex, float value) {
		this.dimensions[dimensionIndex] = value;
	}
	
	public void set(float value) {
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i] = value;
		}
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
		for (int i = 0; i < this.dimensions.length; i++) {
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
		for (int i = 0; i < this.dimensions.length; i++) {
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
		for (int i = 0; i < this.dimensions.length; i++) {
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
		for (int i = 0; i < this.dimensions.length; i++) {
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
	
	public float dotProduct(Vector other) {
		float result = 0;
		for (int i = 0; i < this.dimensions.length; i++) {
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
		for (int i = 0; i < this.dimensions.length; i++) {
			destination.dimensions[i] = this.dimensions[i];
		}
	}
	
	/**
	 * 
	 * @param destination
	 */
	public void copyTo(float[] destination) {
		for (int i = 0; i < this.dimensions.length; i++) {
			destination[i] = this.dimensions[i];
		}		
	}
	
	/**
	 * 
	 * @param source
	 */
	public void copyFrom(Vector source) {
		for (int i = 0; i < this.dimensions.length; i++) {
			this.dimensions[i] = source.dimensions[i];
		}
	}
	
	public void copyFrom(float[] source) {
		for (int i = 0; i < this.dimensions.length; i++) {
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