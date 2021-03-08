package geometries;
import  primitives.Point3D;
import  primitives.Vector;
/**
 * An interface called Geometry for any geometric body
 * @author shalh
 *
 */
public interface Geometry {
	
/**
  * returns the normal vector (vertical) to the body at this point
  */
public Vector getNormal(Point3D point);


}
