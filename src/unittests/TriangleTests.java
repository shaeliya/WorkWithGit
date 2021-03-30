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

import java.util.*;

import org.junit.Test;
import  primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import  geometries.*;
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
	
	/**
	 * Test method for {@link geometries.Triangle#findIntsersections(Ray ray)}.
	 */
	@Test		
	public void testFindIntsersections() {
		Point3D point1= new Point3D(1,0,0);
		Point3D point2= new Point3D(0,1,0);
		Point3D point3= new Point3D(1,1,0);
		Vector vector1= new Vector(0,0,-1);
		Point3D point4= new Point3D(0.5,0.75,1);
		Point3D point5= new Point3D(0.25,0.25,1);
		Point3D point6= new Point3D(1.25,1.25,1);
		Point3D point7= new Point3D(0.5,0.5,1);
		Point3D point8= new Point3D(1,1,1);
		Point3D point9= new Point3D(1,5,1);
		Triangle triangle=new Triangle(point1,point2,point3);		
	    // ============ Equivalence Partitions Tests ==============
		//Ray inside triangle
		Ray ray1 = new Ray (point4,vector1);
		List <Point3D>point3DList1 = triangle.findIntsersections(ray1);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList1.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList1.get(0).equals(new Point3D(0.5,0.75,0)));
		// ============ Equivalence Partitions Tests ==============
		//Ray outside and against edge(zela)
		Ray ray2 = new Ray (point5,vector1);
		List <Point3D>point3DList2 = triangle.findIntsersections(ray2);
		if(point3DList2!= null) {
			fail("The test failed, the function found intersection point");
			}
		// ============ Equivalence Partitions Tests ==============
		//Ray outside against vertex(kodkod)
		Ray ray3 = new Ray (point6,vector1);
		List <Point3D>point3DList3 = triangle.findIntsersections(ray3);
		if(point3DList3!= null) {
			fail("The test failed, the function found intersection point");
			}	
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane on edge
		Ray ray4= new Ray (point7,vector1);
		List <Point3D>point3DList4 = triangle.findIntsersections(ray4);
		if(point3DList4!= null) {
			fail("The test failed, the function found intersection point");
			}
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane In vertex
		Ray ray5= new Ray (point8,vector1);
		List <Point3D>point3DList5 = triangle.findIntsersections(ray5);
		if(point3DList5!= null) {
			fail("The test failed, the function found intersection point");
			}
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane On edge's continuation
		Ray ray6= new Ray (point9,vector1);
		List <Point3D>point3DList6 = triangle.findIntsersections(ray6);
		if(point3DList6!= null) {
			fail("The test failed, the function found intersection point");
			}
	}

}
