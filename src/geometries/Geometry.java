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
/**
 * An interface called Geometry for any geometric body
 * @author shalh
 *
 */
 public abstract class Geometry implements Intersectable {
	
/**
  * returns the normal vector (vertical) to the body at this point
  */
public abstract Vector getNormal(Point3D point);
 protected Color  emmission = Color.BLACK;
 /**
  * get the Emmission
  * @return emmission
  */
public Color getEmmission() {
	return emmission;
}
/**
 * set the Emmission and return Geometry
 * @param emmission
 */
public Geometry setEmmission(Color emmission) {
	this.emmission = emmission;
	return this;
}

}
