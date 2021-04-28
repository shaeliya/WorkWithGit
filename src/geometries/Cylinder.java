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
import primitives.Ray;
import primitives.Vector;
/**
 * A class that create a cylinder
 * @author shalh
 *
 */
public class Cylinder extends Tube  {
	
	public Cylinder(Ray axisRay, double radius) {
		super(axisRay, radius);
		// TODO Auto-generated constructor stub
	}

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
