package com.chimeric.framework.math;

public class FastVectorBiOps {
	public static final FastCombineable<Vector, Vector> Add = new FastCombineable<Vector, Vector>() {
		@Override
		public void fastCombine(Vector op1, Vector op2, Vector result) throws CombineableException {
			int dimensions = op1.getLeastCommonDimensions(op2);
			
			for (int i = 0; i < dimensions; i++) {
				result.dimensions[i] = op1.dimensions[i] + op2.dimensions[i];
			}
		}
	};
	
	public static final FastCombineable<Vector, Vector> Sub = new FastCombineable<Vector, Vector>() {
		@Override
		public void fastCombine(Vector op1, Vector op2, Vector result) throws CombineableException {
			int dimensions = op1.getLeastCommonDimensions(op2);
			
			for (int i = 0; i < dimensions; i++) {
				result.dimensions[i] = op1.dimensions[i] - op2.dimensions[i];
			}
		}
	};
}
