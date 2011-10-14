package com.chimeric.framework.state;

public interface Effect {
	/**
	 * Amount of time until completion (seconds).
	 * <p>instant = 0, indefinite = Float.POSITIVE_INFINITY
	 * @return
	 */
	public float getDuration();
	
	/**
	 * The maximum effective distance (meters).
	 * <p>single target / point = 0, everywhere = Float.POSITIVE_INFINITY
	 * @return
	 */
	public float getRange();

	/**
	 * Spawns the effect.
	 * Put effect's initialization logic here.
	 * Called by framework.
	 */
	public void onCreate();
	
	/**
	 * Updates state of effect.
	 * Put effect's update logic here.
	 * Called by framework.
	 * @param deltaTime		The amount of time elapsed since last update.
	 */
	public void update(float deltaTime);
	
	/**
	 * Presents effect to user.
	 * Put effect's render logic here.
	 * Called by framework.
	 * @param deltaTime		The amount of time elapsed since last present.
	 */
	public void present(float deltaTime);

	/**
	 * Finishes the effect.
	 * Put effect's destruction logic here.
	 * Called by framework.
	 */
	public void onFinished();
}
