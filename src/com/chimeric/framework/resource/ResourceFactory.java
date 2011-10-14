package com.chimeric.framework.resource;


public interface ResourceFactory<Resource extends Initializeable> {
	public Resource createResource();
}
