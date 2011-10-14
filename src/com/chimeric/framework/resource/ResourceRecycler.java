package com.chimeric.framework.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Use when creation of object must succeed whenever possible, no population limit
 * @author JB
 *
 * @param <Resource>
 */
public class ResourceRecycler<Resource extends Initializeable> {
	private final List<Resource> resources;
	private final ResourceFactory<Resource> factory;
	private final int maxSize;
	
	public ResourceRecycler(ResourceFactory<Resource> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.resources = new ArrayList<Resource>(maxSize);
	}
	
	public Resource getReuseableResource() {
		Resource resource = null;
		
		if (this.resources.size() == 0) {
			resource = factory.createResource();
		}
		else {
			resource = this.resources.remove(this.resources.size() - 1);
		}
		
		resource.initialize();
		return resource;
	}
	
	public void recycleResource(Resource resource) {
		if (this.resources.size() < this.maxSize) {
			this.resources.add(resource);
		}
	}
}
