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
import java.util.stream.Collectors;
/**
 * An interface that defines a function that will receive a ray and return intersections with the geometry.
 * @author shalh
 *
 */
public interface Intersectable {
	
	default List<Point3D> findIntsersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}
	public List<GeoPoint> findGeoIntersections (Ray ray);     
	/**
	 *class-a point with reference to the geometry 
	 * @author shalh
	 *
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
	    
	    /**
	     * constructor 
	     * @param geometry
	     * @param point
	     */
		public GeoPoint(Geometry geometry, Point3D point) {
			super();
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}		
	    
	}

}
