package geometries;
import  primitives.Point3D;
import  primitives.Vector;

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
	this.normal = normal;
}
/**
 * constractor
 * @param p1
 * @param p2
 * @param p3
 */
public Plane(Point3D p1, Point3D p2,Point3D p3 ) {
	//Vector vec1 = new Vector(p2.subtract(p1).getHead());
	//Vector vec2= new Vector(p3.subtract(p1).getHead());
	//this.normal=vec1.crossProduct(vec2);
	this.normal=null;
	this.q0=p2;
}

}
