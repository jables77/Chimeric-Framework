package com.chimeric.framework.math;

/**
 * Immutable class for storing 2 dimensions.
 * @author JB
 *
 * @param <T>
 */
public class Size3d<T> extends Size2d<T> {
	private T depth;
	
	public Size3d(T width, T height, T depth) {
		super(width, height);
		
		this.depth = depth;
	}
	
	public T getDepth() { return this.depth; }
}
