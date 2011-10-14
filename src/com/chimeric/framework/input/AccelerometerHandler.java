package com.chimeric.framework.input;

import java.util.List;

import com.chimeric.framework.math.Vector3d;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class AccelerometerHandler implements SensorEventListener {
	private final Vector3d acceleration;
	private SensorManager sensorManager;
	
	public AccelerometerHandler(Context context) {
		acceleration = new Vector3d();
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);		
	}
	
	public void attach() {
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		
		if (sensorList.size() == 0) {
			Log.e("com.chimeric.framework.input", "No accelerometer installed");
		}
		else {
			Sensor accelerometer = sensorList.get(0);
			if (!sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)) {
				Log.e("com.chimeric.framework.input", "Couldn't register accelerometer sensor listener");
			}
		}
	}
	
	public void detach() {
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		this.acceleration.copyFrom(event.values);
	}
	
	public void copyAccelerationTo(Vector3d vector) {
		this.acceleration.copyTo(vector);
	}
}
