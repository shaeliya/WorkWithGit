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
import  primitives.Ray;
import  primitives.Vector;
import  primitives.Point3D;

import  geometries.Tube;
/**
 * @author shalh
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {

		// ============ Equivalence Partitions Tests ==============
		
		Point3D point1= new Point3D(5,1,0);
		Point3D point2= new Point3D(10,8,7);
		Vector vector= new Vector(1,0,0);
		Ray ray= new Ray(point1,vector);		 
		Tube tube= new Tube(ray,7);

		//Test if the normal of the tube is normalized
		
		assertEquals("Test fails, the normal of the tube is not normalized",1,tube.getNormal(point2).length(),0.00001);

	}

}
