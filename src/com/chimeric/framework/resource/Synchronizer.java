package com.chimeric.framework.resource;

import java.util.ArrayList;
import java.util.List;


public abstract class Synchronizer<Resource extends Initializeable> {
	private List<Resource> resources = new ArrayList<Resource>();

	/**
	 * Must instantiate in derived classes.
	 */
	protected ResourceRecycler<Resource> resourceRecycler;
	protected List<Resource> resourceBuffer = new ArrayList<Resource>();

	public List<Resource> getResources() {
		synchronized(this) {
			int resourceCount = this.resources.size();
			for (int i = 0; i < resourceCount; i++) {
				resourceRecycler.recycleResource(resources.get(i));
			}
			
			resources.clear();
			resources.addAll(resourceBuffer);
			resourceBuffer.clear();
			return resources;
		}
	}
}
