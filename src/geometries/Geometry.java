//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
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
