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

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.*;
import primitives.*;

/**
 * @author shalh
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		Ray ray1 = new Ray( new Point3D(1,0,0),new Vector(1,0,0));
    //============ Equivalence Partitions Tests =============
   //The point in the middle of the list is closest to the beginning of the ray		
		List <Point3D> listPoint1= new  LinkedList<Point3D>();
		listPoint1.add(new Point3D(4,0,0));
		listPoint1.add(new Point3D(2,0,0));
		listPoint1.add(new Point3D(10,0,0));
		assertTrue("Test fails, the function did not find the correct point",ray1.findClosestPoint(listPoint1).equals(new Point3D(2,0,0)));
	 // =============== Boundary Values Tests ==================
     // Empty list (method should return null value)
		List <Point3D> listPoint2= null;
		if(ray1.findClosestPoint(listPoint2)!= null) {
			fail("The test failed,the function found a point even though the list is empty");
		}
		 // =============== Boundary Values Tests ==================
	     // The first point is closest to the beginning of the ray
		List <Point3D> listPoint3= new  LinkedList<Point3D>();
		listPoint1.add(new Point3D(2,0,0));
		listPoint1.add(new Point3D(4,0,0));
		listPoint1.add(new Point3D(10,0,0));
		assertTrue("Test fails, the function did not find the correct point",ray1.findClosestPoint(listPoint1).equals(new Point3D(2,0,0)));
		
		 // =============== Boundary Values Tests ==================
	     // The last point is closest to the beginning of the ray
		List <Point3D> listPoint4= new  LinkedList<Point3D>();
		listPoint1.add(new Point3D(4,0,0));
		listPoint1.add(new Point3D(10,0,0));
		listPoint1.add(new Point3D(2,0,0));
		assertTrue("Test fails, the function did not find the correct point",ray1.findClosestPoint(listPoint1).equals(new Point3D(2,0,0)));
	}
	/**
	 * Test method for {@link primitives.Ray#GetClosestGeoPoint(java.util.List)}.
	 */
	@Test
	public void testGetClosestGeoPoint () {
		Ray ray1 = new Ray( new Point3D(1,0,0),new Vector(1,0,0));
    //============ Equivalence Partitions Tests =============
    //The point in the middle of the list is closest to the beginning of the ray		
	//	Plane plane= new  Plane(new Point3D(2,0,0), new Vector(1,0,0));
	//	assertTrue("Test fails, the function did not find the correct point",ray1.getClosestGeoPoint(plane.findGeoIntersections(ray1)).equals(new GeoPoint(plane,new Point3D (2,0,0))));
	 // =============== Boundary Values Tests ==================
     // Empty list (method should return null value)
	////	List <Point3D> listPoint2= null;
	//	if(ray1.findClosestPoint(listPoint2)!= null) {
	//		fail("The test failed,the function found a point even though the list is empty");
	//	}
		 // =============== Boundary Values Tests ==================
	     // The first point is closest to the beginning of the ray
		Sphere sphere=new Sphere(new Point3D(3,0,0),1);
	//	assertTrue("Test fails, the function did not find the correct point",ray1.getClosestGeoPoint(sphere.findGeoIntersections(ray1)).equals(new GeoPoint(sphere,new Point3D(2,0,0))));
		
		
		 // =============== Boundary Values Tests ==================
	     // The last point is closest to the beginning of the ray
		Geometries geometries= new Geometries();
		Plane plane1= new  Plane(new Point3D(5,0,0), new Vector(1,0,0));
		geometries.add(plane1);
		geometries.add(sphere);
		List <GeoPoint> geoPointList= geometries.findGeoIntersections(ray1);
		GeoPoint geoPoint = ray1.getClosestGeoPoint(geoPointList);
		assertTrue("Test fails, the function did not find the correct point",geoPoint.equals(new GeoPoint(sphere,new Point3D (2,0,0))));
	}
	
}
