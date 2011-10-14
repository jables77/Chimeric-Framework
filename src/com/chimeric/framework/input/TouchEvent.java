package com.chimeric.framework.input;

import com.chimeric.framework.math.Vector2d;

/**
 * @author JB
 *
 */
public class TouchEvent
extends Vector2d
{ //implements Initializeable {
	private TouchStates touchState;
	
	public TouchEvent() {
		super();
		initialize();
	}

	public void setTouchState(TouchStates value) { this.touchState = value; }
	
	public TouchStates getTouchState() { return this.touchState; }
	
	public void consume() {
		this.touchState = TouchStates.CONSUMED;
	}
	
//	@Override
	public void initialize() {
		this.dimensions[0] = -1;
		this.dimensions[1] = -1;
		this.touchState = TouchStates.UNPRESSED;
	}
}
