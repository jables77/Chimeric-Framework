package com.chimeric.framework.diagnostic;

public class FramesPerSecond {
	private long currentFrameCount;
	private float totalSecondsElapsed;
	private float currentFPS;
	
	public FramesPerSecond() {
		this.reset();
	}
	
	public void reset() {
		this.currentFrameCount = 0;
		this.totalSecondsElapsed = 0;
	}
	
	public void update(float deltaTime) {
		this.currentFrameCount++;
		this.totalSecondsElapsed += deltaTime;
		
		this.currentFPS = 1.0f / deltaTime;
	}
	
	public float getAvgFPS() {
		return this.currentFrameCount / this.totalSecondsElapsed;
	}
	
	public float getCurrentFPS() {
		return this.currentFPS;
	}
}
