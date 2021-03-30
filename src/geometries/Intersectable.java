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
