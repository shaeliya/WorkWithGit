package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A class that create a Triangle
 * @author shalh
 *
 */
public class Triangle extends Polygon implements Geometry  {
/**
 * constractor
 * @param p1
 * @param p2
 * @param p3
 */
public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1,p2,p3);
}

@Override
public String toString() {
	return "Triangle [vertices=" + vertices + ", plane=" + plane + "]";
}

/**
* returns the normal vector (vertical) to the body at this point
*/
public Vector getNormal(Point3D point) {
	
	return null;
}

}
