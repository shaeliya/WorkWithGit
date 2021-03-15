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
import  primitives.Vector;
import  geometries.Plane;

/**
 * @author shalh
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testGetNormal() {
		
        // ============ Equivalence Partitions Tests ==============
		
		Point3D point= new Point3D(1,2,3);
		Vector vector= new Vector(2,4,7);
		Plane plane=new Plane(point,vector);
		
		//Test if the normal of the plane is normalized

		assertEquals("Test fails, the normal of the plane is not normalized",1,plane.getNormal().length(),0.0000001);
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		
        // ============ Equivalence Partitions Tests ==============

		Point3D point= new Point3D(1,2,3);
		Vector vector1= new Vector(2,4,7);
		Plane plane=new Plane(point,vector1);
		Vector vector2=plane.getNormal(point);
		
		//Test if the normal of the plane is normalized

		assertEquals("Test fails, the normal is not normalized",1,vector2.length(),0.0000001);
		
	}

}
