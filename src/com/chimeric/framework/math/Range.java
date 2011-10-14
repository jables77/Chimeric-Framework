package com.chimeric.framework.math;

public class Range {
	private float minimum;
	private float maximum;
	
	public Range(float endpoint1, float endpoint2) {
		if (endpoint1 < endpoint2) {
			this.minimum = endpoint1;
			this.maximum = endpoint2;
		}
		else {
			this.minimum = endpoint2;
			this.maximum = endpoint1;
		}
	}
	
	public boolean testInclusive(float test) {
		return this.minimum <= test && test <= this.maximum;
	}
	
	public boolean testExclusive(float test) {
		return this.minimum < test && test < this.maximum;
	}
	
	public boolean testExclusiveMinimum(float test) {
		return this.minimum < test && test <= this.maximum;
	}
	
	public boolean testExclusiveMaximum(float test) {
		return this.minimum <= test && test < this.maximum;
	}
	
	/**
	 * Only does inclusive clamps
	 * @param testValue
	 * @return
	 */
	public float clampToRange(float value) {
		if (value > this.maximum) {
			value = maximum;
		}
		else if (value < this.minimum) {
			value = minimum;
		}
		
		return value;
	}
}
