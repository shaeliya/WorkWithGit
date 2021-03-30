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

import java.util.List;

import org.junit.Test;
import  primitives.Point3D;
import primitives.Ray;
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
	/**
	 * Test method for {@link geometries.Plane#findIntsersections(Ray ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		Point3D point1= new Point3D(0,0,-1);
		Point3D point2= new Point3D(1,0,-1);
		Point3D point3= new Point3D(0,0,1);
		Point3D point4= new Point3D(0,0,-2);
		Point3D point5= new Point3D(1,0,-2);
		Vector vector1= new Vector(0,0,1);
		Vector vector2= new Vector(1,0,0);
		Vector vector3= new Vector(1,0,-1);
		Vector vector4= new Vector(1,2,1);
		Plane plane= new Plane(point1,vector1);
		
		// ============ Equivalence Partitions Tests ==============
		//	Ray intersects the plane
		Ray ray1 = new Ray (point5,vector4);
		List <Point3D>point3DList1=plane.findIntsersections(ray1);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList1.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList1.get(0).equals(new Point3D(2,2,-1)));
		// ============ Equivalence Partitions Tests ==============
		//	Ray does not intersect the plane
		//יש לנו כאן בעיה
		Ray ray2 = new Ray (point3,vector4);
		List <Point3D>point3DList2=plane.findIntsersections(ray2);
		if(point3DList2!= null) {
			fail("The test failed, the function found intersection point");
			}
	    // =============== Boundary Values Tests ==================
		//the ray is parallel and included in the plane
		// צריך למצוא דרך לבדוק שכל הנקודות נמצאות במישור 
		Ray ray3 = new Ray (point1,vector3);				
		List <Point3D>point3DList3=plane.findIntsersections(ray3);
		// =============== Boundary Values Tests ==================
		//the ray is parallel and not included in the plane
		Ray ray4 = new Ray (point3,vector2);
		List <Point3D>point3DList4=plane.findIntsersections(ray4);
		if(point3DList4!= null) {
			fail("The test failed, the function found intersection point");
			}
		
		// =============== Boundary Values Tests ==================
	    //	Ray is orthogonal to the plane ,before the plane
		Ray ray5 = new Ray (point4,vector1);	
		List <Point3D>point3DList5=plane.findIntsersections(ray5);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList5.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList5.get(0).equals(new Point3D(0,0,-1)));
		// =============== Boundary Values Tests ==================
		//Ray is orthogonal to the plane ,in the plane
		Ray ray6 = new Ray (point1,vector1);	
		List <Point3D>point3DList6=plane.findIntsersections(ray6);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList6.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList6.get(0).equals(new Point3D(0,0,-1)));
		// =============== Boundary Values Tests ==================
		//Ray is orthogonal to the plane ,after the plane
		// אותה בעיה כמו בשורה 91
		Ray ray7 = new Ray (point3,vector1);
		List <Point3D>point3DList7=plane.findIntsersections(ray7);
		if(point3DList7!= null) {
			fail("The test failed, the function found intersection point");
			}
		// =============== Boundary Values Tests ==================
		//Ray is neither orthogonal nor parallel to the plane, and begins in the plane
		Ray ray8 = new Ray (point2,vector3);
		List <Point3D>point3DList8=plane.findIntsersections(ray8);
		if(point3DList8!= null) {
			fail("The test failed, the function found intersection point");
			}
		// =============== Boundary Values Tests ==================
		//Ray is neither orthogonal nor parallel to the plane and begins in
		//the same point which appears as reference point in the plane 
		Ray ray9 = new Ray (point1,vector3);
		List <Point3D>point3DList9=plane.findIntsersections(ray9);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList9.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList9.get(0).equals(point1));
	}

}
