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
import primitives.Ray;

/**
 * A class that create a Tube
 * @author shalh
 *
 */
public class Tube implements Geometry {

Ray axisRay;
double radius;

/**
* returns the normal vector (vertical) to the body at this point
*/
public Vector getNormal(Point3D point) {	
 Vector vec1=point.subtract(axisRay.getP0());
 double t = vec1.dotProduct(axisRay.getDir());
 Point3D o = axisRay.getP0().add(axisRay.getDir().scale(t));
 Vector vec2 = point.subtract(o);
 return vec2.normalize();
}

@Override
public String toString() {
	return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
}

public Tube(Ray axisRay, double radius) {
	super();
	this.axisRay = axisRay;
	this.radius = radius;
}

}
