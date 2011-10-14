package com.chimeric.framework.math;

public interface FastCombineable<Operand, Output> {
	public void fastCombine(Operand op1, Operand op2, Output result) throws CombineableException;
}
