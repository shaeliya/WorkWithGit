//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * A department that creates a ray
 * @author shalh
 *
 */
public class Ray {
private Point3D p0;
private Vector dir;
/**
 * Size of first moving rays for shading rays
 */
private static final double DELTA = 0.1;
/**
 * constractor
 * @param p0
 * @param dir
 */
public Ray(Point3D p0, Vector dir) {
	super();
	//dir.normalize();
	this.p0 = p0;
	this.dir = dir.normalized();
}
public Ray(Point3D p0, Vector dir,Vector n) {
	super();
	//dir.normalize();
	this.p0 = p0;
	this.dir = dir.normalized();
	double nV=n.dotProduct(dir);
	Vector delta = n.scale(nV > 0 ? DELTA : - DELTA);
    this.p0=p0.add(delta);
}


public Point3D getP0() {
	return p0;
}


public Vector getDir() {
	return dir;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Ray other = (Ray) obj;
	
	return this.p0.equals(other.p0)&& this.dir.equals(other.dir);
}
public Point3D getPoint(double t) {
Point3D p=p0.add(dir.scale(t));
return p;
	
}
/**
 * return the point with the smallest distance from the p0
 * @param Points list of intersection points 
 * @return minPoint
 */
public Point3D findClosestPoint (List<Point3D> Points ) {
	// the list is empty return null
	if (Points== null) {
		return null;
	}
	double minDistance=0;
	Point3D minPoint= null;
	for (int i = 0; i < Points.size(); i++) {
		// Initialize the minPoint to be the distance of the first point
		if (i==0) {		
		  minDistance=Points.get(i).distance(getP0());
		  minPoint=Points.get(i);
		}
		else {
	//Calculates the distance point 
	 double pointDistance=Points.get(i).distance(getP0());
	 //Checks whether the current point distance is less than the minimum point distance
	    if(pointDistance<minDistance) {
		 minDistance=pointDistance;
		 minPoint=Points.get(i);
	    }
	 
	  }		
	}
	return minPoint;
}
public GeoPoint getClosestGeoPoint (List <GeoPoint> geoPoints ) {
	
	// the list is empty return null
		if (geoPoints== null) {
			return null;
		}
		double minDistance=0;
		GeoPoint minPoint= null;
		for (int i = 0; i < geoPoints.size(); i++) {
			// Initialize the minPoint to be the distance of the first point
			if (i==0) {		
			  minDistance=geoPoints.get(i).point.distance(getP0());
			  minPoint=geoPoints.get(i);
			}
			else {
		//Calculates the distance point 
		 double pointDistance=geoPoints.get(i).point.distance(getP0());
		 //Checks whether the current point distance is less than the minimum point distance
		    if(pointDistance<minDistance) {
			 minDistance=pointDistance;
			 minPoint=geoPoints.get(i);
		    }
		 
		  }		
		}
		//update the geometry info. 
		//minPoint.geometry= geoPoints.get(0).geometry;
		return minPoint;
		}
}
