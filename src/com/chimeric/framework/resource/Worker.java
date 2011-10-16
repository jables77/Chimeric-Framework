package com.chimeric.framework.resource;

public interface Worker<Resource extends Initializeable, OutsideState> {	
	public void workOn(ResourceBroker<Resource> resourceBroker, Resource resource, int index);
	public void setState(OutsideState value);
}