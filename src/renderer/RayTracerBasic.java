//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package renderer;
import java.awt.Point;


import java.util.List;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;
import elements.*;
import primitives.*;

import scene.*;
/** ray scanner
 * Class for calculations of a ray with a scene extends from RayTracerBase class
 * @author shalh
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * Size of first moving rays for shading rays
	 */
	private static final double DELTA = 0.1;
	/**
	 * Checks if a particular point needs to be shaded
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		Point3D point = geopoint.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return true;
		double lightDistance = light.getDistance(geopoint.point);
		for (GeoPoint gp : intersections) {
		if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0)
		return false;
		}
		return true;
		}

/**
 * constructor
 * @param scene
 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}
	
	@Override
	/**
	 * The function receives a ray and returns the color of the point it cuts in the scene
	 */
	public Color traceRay(Ray ray) {
	//Calculate the points of intersection of the ray with the shapes
	List <GeoPoint> points =scene.geometries.findGeoIntersections(ray);
	//if the list is empty' return null 
	if(points==null) {
		return scene.background;
	}
	// find the Closest Point
	GeoPoint point=ray.getClosestGeoPoint(points);
	
		return calcColor(point,ray);
	}
	/**
	 * The function calculates and returns the color of a single point
	 * @param point ,ray
	 * @return scene.ambientLight.getIntensity
	 */
  private Color calcColor(GeoPoint point,Ray ray) {	
		return scene.ambientLight.getIntensity().add(point.geometry.getEmmission()).add(calcLocalEffects(point,ray));
		
}
	
	/**
	 * Calculates the changes and effects due to light
	 * @param intersection
	 * @param ray
	 * @return Color
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir ();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
		double kd = intersection.geometry.getMaterial().kD;
		double	ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
		Vector l = lightSource.getL(intersection.point);
		double nl = alignZero(n.dotProduct(l));
		if (nl * nv > 0) { 
		// sign(nl) == sing(nv)
		if (unshaded(lightSource,l,n, intersection)) {
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n, v, nShininess, lightIntensity));
		}
		}
		
		}
		return color;
		}
	/**
	 * Calculates the Specular of light on the object
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return  Color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color ip)
	{
	double p = nShininess;

	double nl = Util.alignZero(n.dotProduct(l));//the light normal

	Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
	double minusVR = -Util.alignZero(R.dotProduct(v));
	if (minusVR <= 0) {
	return Color.BLACK; // view from direction opposite to r vector
	}
	return ip.scale(ks * Math.pow(minusVR, p));
	}

/**
 * Calculates the Diffusive of the material
 * @param kd
 * @param l
 * @param n
 * @param lightIntensity
 * @return Color
 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color ip)
	{
	double nl = Util.alignZero(n.dotProduct(l));//the light normal
	if (nl < 0) nl = -nl;
	return ip.scale(nl * kd);
	}
	
}
