package com.chimeric.framework.audio;

public interface Music {
	void play();
	void stop();
	void pause();
	void setLooping(boolean value);
	void setVolume(float value);
	boolean isPlaying();
	boolean isStopped();
	boolean isLooping();
	void dispose();
}
