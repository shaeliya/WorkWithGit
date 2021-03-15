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

import static org.junit.Assert.*;

import org.junit.Test;
import  primitives.Point3D;
import  geometries.Triangle;
/**
 * @author shalh
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		 // ============ Equivalence Partitions Tests ==============
		
		Point3D point1= new Point3D(1,2,3);
		Point3D point2= new Point3D(5,1,0);
		Point3D point3= new Point3D(1,0,0);
		Triangle triangle= new Triangle(point1,point2,point3);
		
		//Test if the normal of the triangle is normalized
		
		assertEquals("Test fails, the normal of the triangle is not normalized",1,triangle.getNormal(point1).length(),0.000001);

	
	}

}
