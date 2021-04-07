//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;
/**
 * @author shalh
 *
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		// =============== Boundary Values Tests ==================
		//Empty shapes collection
		Geometries geometries1= new Geometries();
		Ray ray1= new Ray(new Point3D(1,2,3),new Vector(1,4,5));
		List IntsersectionsList1= geometries1.findIntsersections(ray1);		
		if(IntsersectionsList1!= null) {
			fail("The test failed, the function found intersection point");
			}		
		// =============== Boundary Values Tests ==================
		//No shape is cut
		Triangle triangle = new Triangle(new Point3D(1,1,0),new Point3D(1,0,0),new Point3D(0,1,0));
		Sphere sphere= new Sphere(new Point3D(1,1,-1),1);
		Plane plane= new Plane(new Point3D(0,0,-2),new Vector(0,0,2));
		Ray ray2= new Ray(new Point3D(1,2,3),new Vector(0,0,-1));
		Geometries geometries2= new Geometries(triangle,sphere);
		List IntsersectionsList2= geometries2.findIntsersections(ray2);		
		if(IntsersectionsList2!= null) {
			fail("The test failed, the function found intersection point");
			}		
		// =============== Boundary Values Tests ==================
		//Only one shape is cut
		Ray ray3= new Ray(new Point3D(1,0,-1),new Vector(-1,1,0));
		Geometries geometries3= new Geometries(triangle,sphere,plane);
		List IntsersectionsList3= geometries3.findIntsersections(ray3);		
		List trainglePoints= triangle.findIntsersections(ray3);
		List spherePoints= sphere.findIntsersections(ray3);
		List planePoints= plane.findIntsersections(ray3);
        if (trainglePoints!=null) {
	    if (trainglePoints.size()!=IntsersectionsList3.size()) {
		fail("The test failed, the ray did not cut just one shape");

	}
}
	  if (spherePoints!=null) {
		if (spherePoints.size()!=IntsersectionsList3.size()) {
			fail("The test failed, the ray did not cut just one shape");

		}
	}
		if (planePoints!=null) {
			if (planePoints.size()!=IntsersectionsList3.size()) {
				fail("The test failed, the ray did not cut just one shape");

	}
			if(IntsersectionsList3.size()==0) {
				
				fail("The test failed, the ray did not cut any shape");
			}
}
      // =============== Boundary Values Tests ==================
      //All shapes are cut
       Ray ray4= new Ray(new Point3D(0.75,0.75,1),new Vector(0,0,-1));		
       Geometries geometries4= new Geometries(triangle,sphere,plane);
		List IntsersectionsList4= geometries4.findIntsersections(ray4);		
		assertEquals("The test failed, the function did not found the expected amount of intersection point ",4,IntsersectionsList4.size(),0.1);

		// ============ Equivalence Partitions Tests ==============
		//Some shapes (but not all) are cut
		   Ray ray5= new Ray(new Point3D(0.75,0.75,1),new Vector(0,0,-1));
			Triangle triangle1 = new Triangle(new Point3D(2,1,0),new Point3D(2,0,-2),new Point3D(4,0,-2));

		   Geometries geometries5= new Geometries(triangle,sphere,plane,triangle1);
			List IntsersectionsList5= geometries5.findIntsersections(ray5);		
			assertEquals("The test failed, the function did not found the expected amount of intersection point ",4,IntsersectionsList5.size(),0.1);

	}
}
	

	

