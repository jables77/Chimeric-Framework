package com.chimeric.framework.physics;

import com.chimeric.framework.math.Matrix;
import com.chimeric.framework.math.Vector;

public interface Moveable {
	/**
	 * 
	 * @return
	 */
	public Vector getVelocity();
	
	/**
	 * 
	 * @return
	 */
	public Vector getAcceleration();
	
	/**
	 * Should also include position/location 
	 * @return
	 */
	public Vector getPosition();
}
