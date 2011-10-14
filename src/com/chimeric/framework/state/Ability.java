package com.chimeric.framework.state;


public interface Ability extends Effect {
	/**
	 * Amount of time until recast (seconds).
	 * <p>no wait = 0
	 */
	public float getCooldown();
	
	/**
	 * Determines if ability is continuous.
	 * @return
	 */
	public boolean isChanneled();
}
