package com.chimeric.framework.math;

/**
 * Immutable class for storing 2 dimensions.
 * @author JB
 *
 * @param <T>
 */
public class Size2d<T> {
	private T width;
	private T height;
	
	public Size2d(T width, T height) {
		this.width = width;
		this.height = height;
	}
	
	public T getWidth() { return this.width; }
	public T getHeight() { return this.height; }
}
