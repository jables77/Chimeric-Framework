package com.chimeric.framework.resource;

public interface Matcher<T> {
	public boolean isMatch(T valueToTest);
}
