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
	
	return null;
}

@Override
public String toString() {
	return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
}

}
