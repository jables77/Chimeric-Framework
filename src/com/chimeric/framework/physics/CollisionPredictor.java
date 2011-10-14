package com.chimeric.framework.physics;

import android.util.FloatMath;
import android.util.Log;

import com.chimeric.framework.math.CombineableException;
import com.chimeric.framework.math.Vector;
import com.chimeric.framework.math.VectorOperationException;
import com.chimeric.framework.physics.models.*;
import com.chimeric.framework.math.FastVectorBiOps;

public class CollisionPredictor {
	private Vector relativeVelocity;
	private Vector relativePosition;
	
	public static final float OMEGA = 1e-5f;
	public static final float EPSILON = 1e-9f;
	
	public CollisionPredictor(int dimensions) {
		relativeVelocity = new Vector(new float[dimensions]);
		relativePosition = new Vector(new float[dimensions]);
	}
	
	public float timeOfCollisionUniformObjects(Circle object1, Circle object2) {
		float t = Float.NaN;
		try {
			float radiusSum = object1.getRadius() + object2.getRadius();
			float radiusSumSquared = radiusSum * radiusSum;
			
			FastVectorBiOps.Sub.fastCombine(object1.getPosition(), object2.getPosition(), relativePosition);
			float c = relativePosition.dotProduct(relativePosition) - radiusSumSquared;
			
			if (c <= EPSILON) {
			//Circles are touching or intersecting
				return 0;
			}
						
			FastVectorBiOps.Sub.fastCombine(object1.getVelocity(), object2.getVelocity(), relativeVelocity);

			float a = relativeVelocity.dotProduct(relativeVelocity);
			
			if (a < OMEGA) {
			// Circles are moving in synch
				return Float.NaN;
			}
			
			float b = relativePosition.dotProduct(relativeVelocity);
			if (b <= 0) {
			//Circles are moving apart
				return Float.POSITIVE_INFINITY;
			}
			
			float d = b * b - a * c;
			if (d > 0) {
			//Circles will not intersect
				return Float.NaN;
			}
			
			t = (b - FloatMath.sqrt(d)) / a;
		} catch (CombineableException e) {
			Log.e("com.chimeric.framework.physics.CollisionManager", e.getMessage());
		} catch (VectorOperationException e) {
			Log.e("com.chimeric.framework.physics.CollisionManager", e.getMessage());
		}
		
		return t;			
	}
}
