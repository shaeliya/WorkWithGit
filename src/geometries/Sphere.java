//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package geometries;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class that create a Sphere
 * @author shalh
 *
 */
public class Sphere implements Geometry {
Point3D center;
double radius;

/**
* returns the normal vector (vertical) to the body at this point
*/
public Vector getNormal(Point3D point) {
	
	Vector vec = point.subtract(center);
	
	return vec.normalize();
}
public Sphere(Point3D center, double radius) {
	super();
	this.center = center;
	this.radius = radius;
}

@Override
public String toString() {
	return "Sphere [center=" + center + ", radius=" + radius + "]";
}

}
