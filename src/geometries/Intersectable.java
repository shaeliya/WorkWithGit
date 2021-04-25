//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package geometries;
import primitives.*;
import java.util.*;
/**
 * An interface that defines a function that will receive a ray and return intersections with the geometry.
 * @author shalh
 *
 */
public interface Intersectable {
	
	List<Point3D> findIntsersections(Ray ray);
}
