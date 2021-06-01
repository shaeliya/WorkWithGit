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
import java.util.LinkedList;
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
	 * A Boolean field that tests whether you want to display the image with Improvement.
	 */
	private boolean isImprovement=false;
	/**
	 * Recursion depth level
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * minimum k
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;
/**
 * initial K 
 */
	private static final double INITIAL_K = 1.0;
	/**
	 * Checks if a particular point needs to be shaded
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return boolean
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection,n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return true;
		double lightDistance = light.getDistance(geopoint.point);
		for (GeoPoint gp : intersections) {
		if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0 &&
		gp.geometry.getMaterial().kT == 0)
		return false;
		}
		return true;
		}
	
	/**
	 * A function that calculates the shading more gradually
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return double  ktr
	 */
	
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		double lightDistance = light.getDistance(geopoint.point);
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return 1.0;
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
		if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0) {
		ktr *= gp.geometry.getMaterial().kT;
		if (ktr < MIN_CALC_COLOR_K) return 0.0;
		}
		}
		return ktr;
		}

	public RayTracerBasic setImprovement(boolean isImprovement) {
		this.isImprovement = isImprovement;
		return this;
	}

	/**
	 *  A function that calculates the Refracted Ray
	 * @param n
	 * @param point
	 * @param inRay
	 * @return Ray
	 */
	 private Ray constructRefractedRay(Vector n,Point3D point,Ray inRay) {
		 return new Ray (point, inRay.getDir(),n);
	 }
	 /**
	  *  A function that calculates the reflection Ray
	  * @param n
	  * @param point
	  * @param inRay
	  * @return Ray
	  */
	 private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay)  {		
				Vector v = inRay.getDir();
				double vn = v.dotProduct(n);
				if (Util.isZero(vn)) 
				{
					return null;
				}
				Vector r = v.subtract(n.scale(2 * vn));							
				return new Ray(point, r,n);
		}
	/**
	 * Returns the nearest Intersection point in the scene
	 * @param ray
	 * @return GeoPoint
	 */
	 private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> ClosestIntersectionList=scene.geometries.findGeoIntersections(ray);
     if (ClosestIntersectionList== null) {
	   return null;
      }
		GeoPoint closestPoint=ray.getClosestGeoPoint(ClosestIntersectionList);
		return closestPoint;
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
	GeoPoint closestPoint= findClosestIntersection(ray);
	if (closestPoint== null) {
		return scene.background;
	}
		return calcColor(closestPoint, ray);
	}
	/**
	 * 
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return color
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k,List <Ray> beamreflec ,List <Ray> beamrefrec) {
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		List <Color> listColor = new LinkedList();
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
		Ray reflectedRay = constructReflectedRay( n,geopoint.point,ray);
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		// מראה
		if (isImprovement==true) {
			if (level==10) {
				beamrefrec=makeBeamRay(reflectedRay,reflectedPoint);
			}
			else {
			for (int i = 0; i < 50; i++) {
				Ray tempRay= constructRefractedRay(n, geopoint.point, beamrefrec.get(i));
				beamrefrec.get(i).setDir(tempRay.getDir());
				beamrefrec.get(i).setP0(tempRay.getP0());
			}
			}
		}
		listColor.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr,beamreflec,beamrefrec).scale(kr)) ;
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
		Ray refractedRay = constructRefractedRay(n, geopoint.point, ray);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		//זכוכית
		if (isImprovement==true) {
			if (level==10) {
				beamrefrec=makeBeamRay(refractedRay,refractedPoint);
			}
			else {
			for (int i = 0; i < 50; i++) {
				Ray tempRay= constructRefractedRay(n, geopoint.point, beamrefrec.get(i));
				beamrefrec.get(i).setDir(tempRay.getDir());
				beamrefrec.get(i).setP0(tempRay.getP0());
			}
			}
		}
		
		listColor.add(calcColor(refractedPoint, refractedRay, level-1, kkt,beamreflec,beamrefrec).scale(kt));
		}
		return averageColor(listColor);
		}
	/**
	 * make Beam of Rays
	 * @param ray
	 * @param geopoint
	 * @return  List<Ray>
	 */
	private List<Ray> makeBeamRay(Ray ray, GeoPoint geopoint) {
		Plane plane = new Plane (geopoint.point,ray.getDir());
		double d= geopoint.point.findD(ray.getDir().getHead());
		int y=-4; //שורות
		int x =-4;	//עמודות
		List<Ray> listRay= new LinkedList();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				double z=ray.getDir().getHead().findZ(x, y, d); 
		        Vector vec = new Vector(x,y,z).normalize();	
		        listRay.add(new Ray(ray.getP0(),vec));
				 y++;
			}
			x++;
			y=-4;
		}
		return null;
	}

	/**
	 * return average color
	 * @param listColor
	 * @return color
	 */
	private Color averageColor(List<Color> listColor) {
		int red=0, green=0,blue=0;
		for (int i = 0; i < listColor.size(); i++) {
			red+= listColor.get(i).getColor().getRed();
			green+= listColor.get(i).getColor().getGreen();
			blue+= listColor.get(i).getColor().getBlue();
		}	
		return new Color(red/listColor.size(),green/listColor.size(),blue/listColor.size());
	}

	/**
	 * The function calculates and returns the color of a single point
	 * @param point ,ray
	 * @return scene.ambientLight.getIntensity
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		List <Ray>beamreflec= new LinkedList();//מראה
		List <Ray>beamrefrec= new LinkedList();//זכוכית
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K,beamreflec,beamrefrec)
		.add(scene.ambientLight.getIntensity());
		}
  private Color calcColor(GeoPoint intersection, Ray ray, int level, double k, List <Ray>beamreflec,List <Ray>beamrefrec) {
	  if(intersection==null) {
			return Color.BLACK;
	  }
	  Color color = intersection.geometry.getEmmission();
	  color = color.add(calcLocalEffects(intersection, ray,k));
	  return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k,beamreflec,beamrefrec));
  }
	
	/**
	 * Calculates the changes and effects due to light
	 * @param intersection
	 * @param ray
	 * @return Color
	 */

	private Color calcLocalEffects(GeoPoint intersection, Ray ray , double k) {
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
			double ktr = transparency(lightSource, l, n, intersection);
			if (ktr * k > MIN_CALC_COLOR_K) {	
				Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
