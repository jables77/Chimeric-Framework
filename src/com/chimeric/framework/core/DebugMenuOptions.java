package com.chimeric.framework.core;

public enum DebugMenuOptions {
	DISPLAY_FPS(0, 0, "Display FPS"),
	DISPLAY_RESOURCES(0, 1, "Display Resources being used"),
	DISPLAY_TOUCHES(0, 2, "Display Touches");
	
	public static final int DEBUG_OPTIONS = 1;
	
	private int groupId;
	private int itemId;
	private String title;
	
	private DebugMenuOptions(int groupId, int itemId, String title) {
		this.groupId = groupId;
		this.itemId = itemId;
		this.title = title;
	}
	
	public int getGroupId() { return groupId; }
	public int getItemId() { return itemId; }
	public String getTitle() { return title; }
}