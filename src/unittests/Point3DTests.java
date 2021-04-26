//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
/**
 * 
 */

/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;

/**
 * @author shalh
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		Point3D p1= new Point3D (2,0,0);
		Point3D p2= new Point3D (1,0,0);
		assertEquals("Test fails, the distance is incorrect",1,p1.distance(p2),0.000001);

	}

}
