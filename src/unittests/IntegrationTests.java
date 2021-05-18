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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;

public class IntegrationTests {

	@Test
	public void cameraSphereIntegration()
    {
		//test 1: test checks the intsersections of the view plane with the sphere in the middle pixel
		// Creating a sphere
		Sphere sphere1 = new Sphere(new Point3D(0,0,-3),1);
		//Creating a camera
		Camera camera1= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera1.setViewPlaneSize(3,3);
		camera1.setDistance(1);
		List <Point3D>point3DList1=findingIntersections(3,3,camera1,sphere1);
	    assertEquals("Test fails,No two intersections were found",2,point3DList1.size(),0.000001);	
	     // test 2 :test checks intersection points of a sphere that contains the view plane and the camera is in front of it (not inside the sphere) 	
	     // Creating a sphere
	     Sphere sphere2 = new Sphere(new Point3D(0,0,-2.5),2.5);
	     //Creating a camera
	     Camera camera2= new Camera (new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0));
	     camera2.setViewPlaneSize(3,3);
	     camera2.setDistance(1);
	     List <Point3D>point3DList2=findingIntersections(3,3,camera2,sphere2);
	     assertEquals("Test fails,No 18 intersections were found",18,point3DList2.size(),0.000001);
	     // test 3 :test checks the intersection points of the sphere with the view plane except for the corners 
	     // Creating a sphere
	     Sphere sphere3 = new Sphere(new Point3D(0,0,-2),2);
	     //Creating a camera
	     Camera camera3= new Camera (new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0));
	     camera3.setViewPlaneSize(3,3);
	     camera3.setDistance(1);
	     List <Point3D>point3DList3=findingIntersections(3,3,camera3,sphere3);
	     assertEquals("Test fails,No 10 intersections were found",10,point3DList3.size(),0.000001);
	     // test 4 :test checks intersection points of a sphere that  the view plane and the camera is the sphere 	
	     // Creating a sphere
	     Sphere sphere4 = new Sphere(new Point3D(0,0,-2),4);
	     //Creating a camera
	     Camera camera4= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
	     camera4.setViewPlaneSize(3,3);
	     camera4.setDistance(1);
	     List <Point3D>point3DList4=findingIntersections(3,3,camera4,sphere4);
	     assertEquals("Test fails,No 9 intersections were found",9,point3DList4.size(),0.000001);	     
	    // test 5:test checks intersection points of a sphere that behind the camera 	
	     // Creating a sphere
	     Sphere sphere5 = new Sphere(new Point3D(0,0,1),0.5);
	     //Creating a camera
	     Camera camera5= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
	     camera5.setViewPlaneSize(3,3);
	     camera5.setDistance(1);
	     List <Point3D>point3DList5=findingIntersections(3,3,camera5,sphere5);
	     if(point3DList5!= null) {
				fail("The test failed, the function found intersection point");
				}	     
}
	@Test
	public void cameraPlaneIntegration()
    {
		//test 1:test checks that the camera cuts the plane(that parallel to the view plane) through each pixel in the view plane
		// Creating a plane
		Plane plane1 = new Plane(new Point3D(0,0,-3),new Vector(0,0,1));
		//Creating a camera
		Camera camera1= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera1.setViewPlaneSize(3,3);
		camera1.setDistance(1);
		List <Point3D>point3DList1=findingIntersections(3,3,camera1,plane1);
		assertEquals("Test fails,No 9 intersections were found",9,point3DList1.size(),0.000001);
		//test 2:test checks that the camera cuts the plane through each pixel in the view plane
		// Creating a plane
		Plane plane2 = new Plane(new Point3D(0,0,-3),new Vector(0,-1,2));
		//Creating a camera
		Camera camera2= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera2.setViewPlaneSize(3,3);
		camera2.setDistance(1);
		List <Point3D>point3DList2=findingIntersections(3,3,camera2,plane2);
		assertEquals("Test fails,No 9 intersections were found",9,point3DList2.size(),0.000001);
		//test 3:test checks that the camera cuts the plane through each pixel, except for the bottom line in the view plane
		// Creating a plane
		Plane plane3 = new Plane(new Point3D(0,0,-3),new Vector(0,-1,1));
		//Creating a camera
		Camera camera3= new Camera (new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera3.setViewPlaneSize(3,3);
		camera3.setDistance(1);
		List <Point3D>point3DList3=findingIntersections(3,3,camera3,plane3);
		assertEquals("Test fails,No 6 intersections were found",6,point3DList3.size(),0.000001);	 
    }
	
	@Test
	public void cameraTriangleIntegration()
    { 
		
		//test 1: test checks the intersections of the view plane with the triangle in the middle pixel
		// Creating a triangle
		Triangle triangle1 = new Triangle(new Point3D(1,-1,-2),new Point3D(0,1,-2),new Point3D(-1,-1,-2));
		//Creating a camera
		Camera camera1= new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera1.setViewPlaneSize(3,3);
		camera1.setDistance(1);
		List <Point3D>point3DList1=findingIntersections(3,3,camera1,triangle1);
		assertEquals("Test fails,No 1 intersections were found",1,point3DList1.size(),0.000001);
		
		//test 2: test checks the intersections of the view plane with the triangle in the middle pixel in the top and middle row 
		// Creating a triangle
		Triangle triangle2 = new Triangle(new Point3D(1,-1,-2),new Point3D(0,20,-2),new Point3D(-1,-1,-2));
		//Creating a camera
		Camera camera2= new Camera(new Point3D(0,0,0),new Vector(0,0,-1),new Vector(0,1,0));
		camera2.setViewPlaneSize(3,3);
		camera2.setDistance(1);
		List <Point3D>point3DList2=findingIntersections(3,3,camera2,triangle2);
		assertEquals("Test fails,No 2 intersections were found",2,point3DList2.size(),0.000001);
    }
	
	/**
	 * Auxiliary function for finding intersection points
	 * @param h
	 * @param w
	 * @param camera
	 * @param geo
	 * @return point3DList
	 */
	private List <Point3D> findingIntersections(int h, int w, Camera camera,Geometry geo){
	List <Point3D>point3DList=null;
	for (int i = 0; i < h; i++) {			
		for (int j = 0; j < w; j++) {
			Ray ray=camera.constructRayThroughPixel(3,3,j,i);	
			List <Point3D>PointsFound=geo.findIntsersections(ray);
			if(PointsFound!= null) {
				if(point3DList==null) {
					point3DList=new LinkedList<Point3D>();
				}
			point3DList.addAll(PointsFound);
		}
	  }
	 }
	return point3DList;
    }
}