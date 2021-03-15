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
package unittests;
import  primitives.Point3D;
import  geometries.Sphere;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author shalh
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		 // ============ Equivalence Partitions Tests ==============
		Point3D point1= new Point3D(1,2,3);
		Point3D point2= new Point3D(3,2,3);
		Sphere sphere= new Sphere(point1,2);
		//Test if the normal of the Sphere is normalized
		assertEquals("Test fails, the normal of the Sphere is not normalized",1,sphere.getNormal(point2).length(),0.000001);
	}

}
