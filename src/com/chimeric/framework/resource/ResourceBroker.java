package com.chimeric.framework.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceBroker<Resource extends Initializeable> {
	private final List<Resource> livingResources;
	private final List<Resource> undeadResources;
	private final ResourceFactory<Resource> factory;
	private final int debtCeiling;
	
	public ResourceBroker(ResourceFactory<Resource> factory, int debtCeiling) {
		this.factory = factory;
		this.debtCeiling = debtCeiling;
		this.livingResources = new ArrayList<Resource>();
		this.undeadResources = new ArrayList<Resource>();
	}
	
	public Resource creditResource() {
		synchronized(this) {
			Resource resource;
			
			if (this.undeadResources.size() > 0) {
				resource = this.undeadResources.remove(0);
			}
			else {
				resource = this.factory.createResource();
			}
			
			resource.initialize();			
			this.livingResources.add(resource);
			return resource;
		}
	}
	
	public void debitResource(Resource resource) {
		synchronized(this) {
			this.undeadResources.add(resource);
		}
	}
	
	public Resource getResource(Matcher<Resource> searchCriteria) {
		synchronized(this) {
			for (int i = this.livingResources.size() - 1; i >= 0; i--) {
				Resource testValue = this.livingResources.get(i);
				if (searchCriteria.isMatch(testValue)) {
					return testValue;
				}
			}
			
			return null;
		}
	}
	
	public Resource getResource(int index) {
		synchronized(this) {
			return this.livingResources.get(index);
		}
	}
	
	public int getResourceCount() {
		synchronized(this) {
			return this.livingResources.size();
		}
	}
	
	public void bankrupt() {
		synchronized(this) {
			Resource debit;
			
			for (int i = this.livingResources.size() - 1; i >= 0; i--) {
				debit = this.livingResources.remove(0);
				this.undeadResources.add(debit);
			}
		}
	}
	
	public <OutsideState> void synchronizedIteration(Worker<Resource, OutsideState> worker) {
		synchronized(this) {
			for (int i = this.livingResources.size() - 1; i >= 0 ; i--) {
				worker.workOn(this, this.livingResources.get(i), i);
			}
		}
	}
	
	public <OutsideState> void synchronizedIteration(Worker<Resource, OutsideState> worker, int offset) {
		synchronized(this) {
			for (int i = this.livingResources.size() - 1; i >= offset ; i--) {
				worker.workOn(this, this.livingResources.get(i), i);
			}
		}
	}

}
