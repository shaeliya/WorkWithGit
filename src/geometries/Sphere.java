//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package geometries;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.*;
import primitives.*;
import static java.lang.Math.sqrt;

/**
 * A class that create a Sphere
 * @author shalh
 *
 */
public class Sphere extends Geometry {
Point3D center;
double radius;

/**
* returns the normal vector (vertical) to the body at this point
*/
public Vector getNormal(Point3D point) {
	
	Vector vec = point.subtract(center);
	
	return vec.normalize();
}
public Sphere(Point3D center, double radius) {
	super();
	this.center = center;
	this.radius = radius;
}

@Override
public String toString() {
	return "Sphere [center=" + center + ", radius=" + radius + "]";
}

@Override
public List<GeoPoint> findGeoIntersections(Ray ray) {
	Point3D p1=null;
	Point3D p2= null;
	GeoPoint geoPoint1 = null;
	GeoPoint geoPoint2= null; 
if(center.equals(ray.getP0())) {
	Point3D point = center.add(ray.getDir());
	GeoPoint geoPoint = new GeoPoint(this,point);
	return List.of(geoPoint) ;
	}
	Vector u= center.subtract(ray.getP0());
	double tm=ray.getDir().dotProduct(u);
	double d=sqrt(u.lengthSquared()-(tm*tm));
	if(d >= radius) {
	      return null;		
		}
		double th= sqrt(radius*radius-d*d);
		double t1=tm-th;
		double t2= tm+th;
		if (t1!=0) {
		 p1=ray.getPoint(t1);
		 geoPoint1 = new GeoPoint(this,p1);

		}
		
		if (t2!=0) {
		p2=ray.getPoint(t2);
		 geoPoint2 = new GeoPoint(this,p2);
		}
	   if (t1>0 && t2>0) {
		   if(t1!=0 && t2!=0) {
		   return List.of(geoPoint1,geoPoint2);
		   }
		}
	    if (t1>0 ) {
	    	 if(t1!=0) {
			return List.of(geoPoint1);
	    	 }
	    }
		if (t2>0 ) {
			 if(t2!=0) {
		   return List.of(geoPoint2);
		   }
		}
	
	return null;
}

}
