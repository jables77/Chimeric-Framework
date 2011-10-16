package com.chimeric.framework;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.*;
import static org.hamcrest.CoreMatchers.*;

import com.chimeric.framework.math.Vector;
import com.chimeric.framework.math.VectorOperationException;

public class VectorTest {
	Vector testVector1;
	Vector testVector2;
	Vector testVector3;
	
	float[] testValues1;
	float[] testValues2;
	float[] testValues3;
	
	@Before
	public void setUp() {
		testValues2 = new float[] { 5, -8 };
		testValues1 = new float[] { -3, 7 };
		testValues3 = new float[] { -2 , 1, 9 };
		
		testVector1 = new Vector(testValues1);
		testVector2 = new Vector(testValues2);
		testVector3 = new Vector(testValues3);
	}	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetCoordinate_NegativeDimension() {
		testVector1.getCoordinate(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetCoordinate_DimensionNonExistant() {
		testVector1.getCoordinate(2);
	}

	@Test
	public void testGetCoordinate() {
		assertThat(testVector1.getCoordinate(0),  is(-3.0f));
	}

	@Test
	public void testGetDimensionCount() {
		assertThat(testVector1.getDimensionCount(), is(2));
	}

	@Test
	public void testIncrement() {
		Vector test1 = new Vector(new float[] {-3, 5} );
		Vector test2 = new Vector(new float[] {7, -9} );
		
		test1.increment(test2);
		assertThat(test1.getCoordinate(0), is(4.0f));
		assertThat(test1.getCoordinate(1), is(-4.0f));
	}

	@Test
	public void testDecrement() {
		Vector test1 = new Vector(new float[] {-3, 5} );
		Vector test2 = new Vector(new float[] {7, -9} );
		
		test1.decrement(test2);
		assertThat(test1.getCoordinate(0), is(-10.0f));
		assertThat(test1.getCoordinate(1), is(14.0f));
	}

	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		fail("Not yet implemented");
	}

	@Test
	public void testNormalize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMagnitudeSquared() {
		fail("Not yet implemented");
	}

	@Test
	public void testDotProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyToVector() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyToFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyFromVector() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyFromFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRawArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLeastCommonDimensions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLeastCommondimensions() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}
}
