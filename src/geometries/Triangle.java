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

}
