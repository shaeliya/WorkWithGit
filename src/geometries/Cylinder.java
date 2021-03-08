package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * A class that create a cylinder
 * @author shalh
 *
 */
public class Cylinder extends Tube implements Geometry {
	
	double height;
	
/**
 * returns the normal vector (vertical) to the body at this point
 */
	
public Vector getNormal(Point3D point) {
	
	return null;
}

@Override
public String toString() {
	return "Cylinder [height=" + height + ", axisRay=" + axisRay + ", radius=" + radius + "]";
}

}
