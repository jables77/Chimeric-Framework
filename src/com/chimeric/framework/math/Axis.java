package com.chimeric.framework.math;

public enum Axis {
	X(0),
	Y(1),
	Z(2),
	W(3);
		
	private int axisIndex;
		
	private Axis(int axisIndex) {
		this.axisIndex = axisIndex;
	}

	public int getAxisIndex() {
		return axisIndex;
	}
}
