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
import primitives.*;
import static primitives.Util.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point3D> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point3D... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (int i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
	}

	@Override
	public Vector getNormal(Point3D point) {
		return plane.getNormal();
	}
	
	
	/**
	 * Auxiliary function that checks if the normal of both edges creates an acute angle with the ray
	 * @param index1
	 * @param index2
	 * @param sign
	 * @param ray
	 * @return double
	 */
	private double isInsidePolygon(int index1,int index2 ,double sign,Ray ray) {
		//create vector1
		Vector v1=(vertices.get(index1).subtract(ray.getP0()));
		//create vector2
		Vector v2=(vertices.get(index2).subtract(ray.getP0()));
		//create a normal from v1 and v2
		Vector n1=( v1.crossProduct(v2)).normalize();
		//find the dot Product of the normal and the ray
		double vn=n1.dotProduct(ray.getDir());
		if(alignZero(vn)==0) {
			return 0;
		}
		//Initialize the mark
		if (index1==0) {
			if(vn>0) {
				return 1;
			}
			if(vn<0) {
				return -1;
			}
			return 0;
		}
		if(index1>0) {			
			if(sign>0 && vn>0) {
				return 1;
			}
			if(sign<0 && vn<0) {
				return -1;
			}
			return 0;
		}
		return 0;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		if(plane.findGeoIntersections(ray)==null) {
			return null;
		}
		double sign=isInsidePolygon(0,1,0,ray);
		
		for (int i = 2; i < vertices.size(); i++) {		
			
			double result1= isInsidePolygon(i-1,i,sign,ray);
			if(sign!=result1||result1==0) {
				return null;
			}			
		}	
		double result2=isInsidePolygon(vertices.size()-1,0,sign,ray);
		 if(sign!=result2||result2==0) {
				return null;
			}			
	 List <GeoPoint> geoPoint= plane.findGeoIntersections(ray);
	 for (int i = 0; i < geoPoint.size(); i++) {
		 geoPoint.get(i).geometry= this;
		
	}
		return geoPoint;		
	}
}
