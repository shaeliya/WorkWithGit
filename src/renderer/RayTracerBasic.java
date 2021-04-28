//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package renderer;


import java.util.List;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
/** ray scanner
 * Class for calculations of a ray with a scene extends from RayTracerBase class
 * @author shalh
 *
 */
public class RayTracerBasic extends RayTracerBase {
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
	
		return calcColor(point);
	}
	/**
	 * The function calculates and returns the color of a single point
	 * @param point
	 * @return scene.ambientLight.getIntensity
	 */
	private Color calcColor(GeoPoint point) {	
		return scene.ambientLight.getIntensity().add(point.geometry.getEmmission());
	}
}
