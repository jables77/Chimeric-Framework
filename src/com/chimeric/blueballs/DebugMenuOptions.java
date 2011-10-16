package com.chimeric.blueballs;

public enum DebugMenuOptions {
	DISPLAY_FPS(DebugMenuOptions.DEBUG_OPTIONS, 0, "Display FPS"),
	DISPLAY_RESOURCES(DebugMenuOptions.DEBUG_OPTIONS, 1, "Display Resources being used"),
	DISPLAY_TOUCHES(DebugMenuOptions.DEBUG_OPTIONS, 2, "Display Touches"),
	RESET_BALLS(DebugMenuOptions.DEBUG_OPTIONS, 3, "Reset Balls");
	
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