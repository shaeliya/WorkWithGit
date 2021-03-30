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

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    /**
	 * Test method for {@link geometries.Triangle#findIntsersections(Ray ray)}.
	 */
	
	public void testFindIntsersections() {
		Point3D point1= new Point3D(1,0,0);
		Point3D point2= new Point3D(0,1,0);
		Point3D point3= new Point3D(-1,0,0);
		Point3D point4= new Point3D(0,-1,0);		
		Point3D point5= new Point3D(0,0,-0.25);
		Point3D point6= new Point3D(1.25,1.25,1);
		Point3D point7= new Point3D(2,0,1);	
		Point3D point8= new Point3D(0.5,0.5,1);		
		Point3D point9= new Point3D(1,0,1);
		Point3D point10= new Point3D(4,-3,0);
		Vector vector1= new Vector(1,1,1);
		Vector vector2= new Vector(0,0,-1);
		Polygon polygon= new Polygon(point1,point2,point3,point4);
	    // ============ Equivalence Partitions Tests ==============
		//Ray inside polygon
		Ray ray1 = new Ray (point5,vector1);
		List <Point3D>point3DList1 = polygon.findIntsersections(ray1);
		assertEquals("The test failed, the function found more than one intersection point",1,point3DList1.size(),0.1);
		assertTrue("The test failed, the function did not find what it was looking for",point3DList1.get(0).equals(new Point3D(0.25,0.25,0)));
		// ============ Equivalence Partitions Tests ==============
		//Ray outside against edge
		Ray ray2 = new Ray (point6,vector2);
		List <Point3D>point3DList2 = polygon.findIntsersections(ray2);
		if(point3DList2!= null) {
			fail("The test failed, the function found intersection point");
			}
		// ============ Equivalence Partitions Tests ==============
		//Ray outside against vertex
		Ray ray3 = new Ray (point7,vector2);
		List <Point3D>point3DList3 = polygon.findIntsersections(ray3);
		if(point3DList3!= null) {
			fail("The test failed, the function found intersection point");
			}	
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane On edge
		Ray ray4= new Ray (point8,vector2);
		List <Point3D>point3DList4 = polygon.findIntsersections(ray4);
		if(point3DList4!= null) {
			fail("The test failed, the function found intersection point");
			}		
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane In vertex
		Ray ray5= new Ray (point9,vector2);
		List <Point3D>point3DList5 = polygon.findIntsersections(ray5);
		if(point3DList5!= null) {
			fail("The test failed, the function found intersection point");
			}				
		// =============== Boundary Values Tests ==================
		//the ray begins before the plane On edge's continuation
		Ray ray6= new Ray (point10,vector2);
		List <Point3D>point3DList6 = polygon.findIntsersections(ray6);
		if(point3DList6!= null) {
			fail("The test failed, the function found intersection point");
			}		
	}
}
