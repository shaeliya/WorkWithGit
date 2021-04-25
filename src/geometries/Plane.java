//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package geometries;
import java.util.List;

import  primitives.Point3D;
import primitives.Ray;
import  primitives.Vector;
import static primitives.Util.*;

/**
 * A class that create a Plane
 * @author shalh
 *
 */
public class Plane implements Geometry{
Point3D q0 ;
Vector normal;

/**
* returns the normal vector (vertical) to the body at this point
*/
public Vector getNormal() {
	
	return normal;
}

/**
 * returns the normal vector (vertical) to the body at this point
 */
public Vector getNormal(Point3D point) {
	
	return normal;
}
/**
 * constractor 
 * @param q0
 * @param normal
 */
public Plane(Point3D q0, Vector normal) {
	super();
	this.q0 = q0;
	this.normal = normal.normalized();
}
/**
 * constractor
 * @param p1
 * @param p2
 * @param p3
 */
public Plane(Point3D p1, Point3D p2,Point3D p3 ) {
	Vector vec1 = new Vector(p2.subtract(p1).getHead());
	Vector vec2= new Vector(p3.subtract(p1).getHead());
	this.normal=vec1.crossProduct(vec2).normalize();
	this.q0=p2;
}

@Override
public String toString() {
	return "Plane [q0=" + q0 + ", normal=" + normal + "]";
}

@Override
/**
 * Finds the points of intersection with the plane
 */
public List<Point3D> findIntsersections(Ray ray) {	
	try {
	Vector vec=q0.subtract(ray.getP0());
	 double t= normal.dotProduct(vec);
	 double nv=(normal.dotProduct(ray.getDir()));
	 //check if vn is zero
	 if(isZero(nv)) {
		 return null;
	 }
	 t=t/nv;
     if (t==0) {
			return null;
		}
	Point3D p = ray.getPoint(t);
	Vector vecDir= p.subtract(ray.getP0()).normalize();
	// check if vector is intersect the plane in the same direction
	if(!(ray.getDir().equals(vecDir))) {
		return null;
	}
	// check if the vectors are ortogonals
	//if(isZero(normal.dotProduct(q0.subtract(p)))) {
		//return List.of(p);
		//return null;
	//}
	else{
		//return null;
		return List.of(p);
	}
	}
	catch (IllegalArgumentException ex) {
		return null;
	}
	
	
}

}
