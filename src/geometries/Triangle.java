package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Polygon implements Geometry  {

public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1,p2,p3);
}

public Vector getNormal(Point3D point) {
	
	return null;
}

}
