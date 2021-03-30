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
import  primitives.*;
import  geometries.Sphere;
import static org.junit.Assert.*;

import java.util.List;

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
	/**
	 * Test method for {@link geometries.Sphere#findIntsersections(Ray ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		Point3D center1= new Point3D(1,0,0);
		Sphere sphere= new Sphere(center1,1);
		Point3D point1= new Point3D(-1,0,0);
         Vector vector1 = new Vector(1,0,0);
 		Point3D point2= new Point3D(1.5,0,0);
 		Point3D point3= new Point3D(3,0,0);
        Vector vector2 = new Vector(0,0,1);
 		Point3D point4= new Point3D(2,0,-1);
 		Point3D point5= new Point3D(2,0,0);
 		Point3D point6= new Point3D(2,0,1);		 
 		//Vector calculation, between the center of the sphere and the beginning of the ray
 		Vector vector3=center1.subtract(point3);
 		Vector vector4 = new Vector(1,0,1);
  		Point3D point7= new Point3D(1,0,1);
  		Point3D point8= new Point3D(1,0,-1);

		// ============ Equivalence Partitions Tests ==============
    	//	The ray cuts the sphere in two points
		Ray ray1 = new Ray(point1,vector1);
		List <Point3D> point3DList1 =sphere.findIntsersections(ray1);
		assertEquals("The test failed, the function not found two intersection point",2,point3DList1.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList1.get(0).equals(new Point3D(0,0,0)));
		assertTrue("The test failed, the function did not find what it was looking for",point3DList1.get(1).equals(new Point3D(2,0,0)));

		// ============ Equivalence Partitions Tests ==============
    	//	The ray starts in the sphere and cuts it once
		Ray ray2 = new Ray(point2,vector1);
		List <Point3D> point3DList2 =sphere.findIntsersections(ray2);
		assertEquals("The test failed, the function not found one intersection point",1,point3DList2.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList2.get(0).equals(new Point3D(2,0,0)));	
		// ============ Equivalence Partitions Tests ==============
    	//	The ray starts after the sphere and would cut it in the opposite direction.
		Ray ray3 = new Ray(point3,vector1);
		List <Point3D> point3DList3 =sphere.findIntsersections(ray3);
		if(point3DList3!= null) {
			fail("The test failed, the function found intersection point");
		}
		// ============ Equivalence Partitions Tests ==============
    	//	The ray starts outside the sphere and does not cut it
		Ray ray4 = new Ray(point3,vector2);
		List <Point3D> point3DList4 =sphere.findIntsersections(ray4);
		if(point3DList4!= null) {
			fail("The test failed, the function found intersection point");
		}
		
	    // =============== Boundary Values Tests ==================
	    //The ray begins before the sphere and launches(meshik) it.
		Ray ray5 = new Ray(point4,vector2);
		List <Point3D> point3DList5 =sphere.findIntsersections(ray5);
		if(point3DList5!= null) {
			fail("The test failed, the function found intersection point");
		}
	
		// =============== Boundary Values Tests ==================
	    //The ray begins in the sphere and launches it.
		Ray ray6 = new Ray(point5,vector2);
		List <Point3D> point3DList6 =sphere.findIntsersections(ray6);
		if(point3DList6!= null) {
			fail("The test failed, the function found intersection point");
		}
		
		// =============== Boundary Values Tests ==================
	    //The ray begins after the sphere and launches it.
		Ray ray7 = new Ray(point6,vector2);
		List <Point3D> point3DList7 =sphere.findIntsersections(ray7);
		if(point3DList7!= null) {
			fail("The test failed, the function found intersection point");
		}
		
		// =============== Boundary Values Tests ==================
	    //The ray is orthogonal to the center of the sphere
		Ray ray8 = new Ray(point3,vector2);
		Ray rayCenter = new Ray(point3,vector3);
		List <Point3D> point3DList8 =sphere.findIntsersections(ray8);
		if(point3DList8!= null) {
		fail("The test failed, the function found intersection point");
		}
	
		// =============== Boundary Values Tests ==================
	    //The ray starts from a point on the sphere and cuts it once more
		Ray ray9 = new Ray(point8,vector4);
		List <Point3D> point3DList9 =sphere.findIntsersections(ray9);
		assertEquals("The test failed, the function not found one intersection point",1,point3DList9.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList9.get(0).equals(new Point3D(2,0,0)));	
		// =============== Boundary Values Tests ==================
	    //The ray starts from a point on the sphere but not cuts it once more
		Ray ray10 = new Ray(point7,vector4);
		List <Point3D> point3DList10 =sphere.findIntsersections(ray10);
		if(point3DList10!= null) {
			fail("The test failed, the function found intersection point");
	
		// =============== Boundary Values Tests ==================
	    //The ray starts in the sphere and cuts it once	
		Ray ray11 = new Ray(point2,vector1);
		List <Point3D> point3DList11 =sphere.findIntsersections(ray11);
		assertEquals("The test failed, the function not found one intersection point",1,point3DList11.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList11.get(0).equals(new Point3D(2,0,0)));
		// =============== Boundary Values Tests ==================
	    //The ray  starts in the center of the sphere, and cuts it once
		Ray ray12 = new Ray(center1,vector1);
		List <Point3D> point3DList12 =sphere.findIntsersections(ray12);
		assertEquals("The test failed, the function not found one intersection point",1,point3DList12.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList12.get(0).equals(new Point3D(2,0,0)));
		// =============== Boundary Values Tests ==================
	    //The ray starts on the sphere and cuts it once, The vector of the ray passes through the center point
		Ray ray13 = new Ray(point8,vector2);
		List <Point3D> point3DList13 =sphere.findIntsersections(ray13);
		assertEquals("The test failed, the function not found one intersection pointt",1,point3DList13.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList13.get(0).equals(new Point3D(1,0,1)));
		// =============== Boundary Values Tests ==================
	    //The ray starts on the sphere and cuts it once, The vector of the ray could pass through the center point in the opposite direction	
		Ray ray14 = new Ray(point2,vector1);
		List <Point3D> point3DList14 =sphere.findIntsersections(ray14);
		assertEquals("The test failed, the function not found one intersection point",1,point3DList14.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList14.get(0).equals(new Point3D(2,0,0)));
		// =============== Boundary Values Tests ==================
	    //The ray starts out of the sphere and not cuts it, The vector of the ray could pass through the center point in the opposite direction	
		Ray ray15 = new Ray(point3,vector1);
		List <Point3D> point3DList15 =sphere.findIntsersections(ray15);
		if(point3DList10!= null) {
			fail("The test failed, the function found intersection point");
			}
		
		// =============== Boundary Values Tests ==================
	    //The ray starts out of the sphere and cuts it twice, The vector of the ray passes through the center point
		Ray ray16 = new Ray(point1,vector1);
		List <Point3D> point3DList16 =sphere.findIntsersections(ray16);
		assertEquals("The test failed, the function not found two intersection point",2,point3DList16.size(),0.000001);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList16.get(0).equals(new Point3D(0,0,0)));
		assertTrue("The test failed, the function did not find what it was looking for",point3DList16.get(1).equals(new Point3D(2,0,0)));
	}
} 
	}

