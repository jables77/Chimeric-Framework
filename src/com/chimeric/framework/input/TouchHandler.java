package com.chimeric.framework.input;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.chimeric.framework.math.Vector2d;
import com.chimeric.framework.resource.ResourceFactory;

public class TouchHandler
implements OnTouchListener {
	private ResourceFactory<TouchEvent> factory = new ResourceFactory<TouchEvent>() {
		@Override
		public TouchEvent createResource() {
			return new TouchEvent();
		}	
	};
	
	private int totalTouchesTrackable;
	private TouchEvent[] touches;
	
	public TouchHandler(int totalTouchesTrackable) {
		this.totalTouchesTrackable = totalTouchesTrackable;
		
		this.touches = new TouchEvent[totalTouchesTrackable];
		
		for (int i = 0; i < totalTouchesTrackable; i++) {
			this.touches[i] = new TouchEvent();
		}
	}
	
	public TouchEvent getTouchEvent(int pointerId) {
		synchronized (this) {
			return this.touches[pointerId];			
		}
	}
	
	public int getTotalTouchesTrackable() { return this.totalTouchesTrackable; }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized(this) {
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			int pointerId = event.getPointerId(pointerIndex);
			
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touches[pointerId].setTouchState(TouchStates.PRESSED);
				setCoordinates(event, pointerIndex, touches[pointerId]);				
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
				touches[pointerId].setTouchState(TouchStates.UNPRESSED);				
				setCoordinates(event, pointerIndex, touches[pointerId]);				
				break;
			case MotionEvent.ACTION_MOVE:
				int pointerCount = event.getPointerCount();
				for (int i = 0; i < pointerCount; i++) {
					pointerIndex = i;
					pointerId = event.getPointerId(pointerIndex);

					touches[pointerId].setTouchState(TouchStates.DRAGGED);
					setCoordinates(event, pointerIndex, touches[i]);
				}
				break;
			}
			
			return true;
		}
	}
	
	private void setCoordinates(MotionEvent event, int pointerIndex, Vector2d result) {
		result.setX(event.getX(pointerIndex));
		result.setY(event.getY(pointerIndex));
	}
}
