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
	 * size of grid
	 */
	private int sizeOfGrid=9;
	
	private double distanceForDiffusedAndGlossy = 1;
    private int numOfRaysForDiffusedAndGlossy = 9;
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
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		Color color = new Color(0,0,0);
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
		Ray reflectedRay = constructReflectedRay( n,geopoint.point,ray);
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		if(isImprovement== false) {	
		color=calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr) ;
		}
		// מראה
		if (isImprovement==true && reflectedPoint!=null) {
			try
			{
				// reflected=-1,refrected=1
			List<Ray> reflectedRays = RaysOfGrid(n,geopoint.point,reflectedRay.getDir(),-1,sizeOfGrid);
			Color tempColor1 = scene.background;
			for(Ray reflecRay: reflectedRays)
			{
				GeoPoint reflecPoint = findClosestIntersection(reflecRay);
				//
				if (reflectedPoint != null) {
					tempColor1 = tempColor1.add(calcColor(reflecPoint, reflecRay, level - 1, kkr).scale(kr));
				}
				else {
					tempColor1 = tempColor1.add(scene.background.scale(kr));
				}
			}
			
			color = color.add(tempColor1.reduce(reflectedRays.size()));
			}
			catch(Exception e)
			{}

		}
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
		Ray refractedRay = constructRefractedRay(n, geopoint.point, ray);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		if(isImprovement== false) {	
		color=calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt);
		}
		//זכוכית
		if (isImprovement==true && refractedPoint!= null) {
			try
			{
			List<Ray> refractedRays = RaysOfGrid(n,geopoint.point,refractedRay.getDir(),-1,sizeOfGrid);
			primitives.Color tempColor2 = scene.background;
			
			for(Ray refracRay: refractedRays)
			{
				GeoPoint refracPoint = findClosestIntersection(refracRay);
				
				if (refracPoint != null)
					tempColor2 = tempColor2.add(calcColor(refracPoint, refracRay, level - 1, kkt).scale(kt));
				else
					tempColor2 = tempColor2.add(scene.background.scale(kt));
			}
			
			color = color.add(tempColor2.reduce(refractedRays.size()));
			}
			catch(Exception e)
			{}

		}		
		}
			
		return color;
	}

	private List<Ray> RaysOfGrid(Vector n, Point3D point, Vector Vto, int direction, double DiffusedAndGlossy) throws Exception {
        if(direction != 1 && direction != -1)
            throw new IllegalArgumentException("direction must be 1 or -1");
        double gridSize = DiffusedAndGlossy; //from the Material 
        int numOfRowCol = isZero(gridSize)? 1: (int)Math.ceil(Math.sqrt(numOfRaysForDiffusedAndGlossy));
        Vector Vup = Vto.findRandomOrthogonal();//vector in the grid
        Vector Vright = Vto.crossProduct(Vup);//vector in the grid
        Point3D centerOfGrid = point.add(Vto.scale(distanceForDiffusedAndGlossy)); // center point of the grid
        double sizeOfCube = gridSize/numOfRowCol;//size of each cube in the grid
        List rays = new LinkedList<Ray>();
        n = n.dotProduct(Vto) > 0 ? n.scale(-direction) : n.scale(direction);//fix the normal direction
        Point3D tempcenterOfGrid = centerOfGrid;//save the center of the grid
        Vector tempRayVector;
        for (int row = 0; row < numOfRowCol; row++)
        {
        	double xAsixChange= (row - (numOfRowCol/2d))*sizeOfCube + sizeOfCube/2d;
        		for(int col = 0; col < numOfRowCol; col++)
        		{
        			double yAsixChange= (col - (numOfRowCol/2d))*sizeOfCube + sizeOfCube/2d;
        			if(xAsixChange != 0) centerOfGrid = centerOfGrid.add(Vright.scale(-xAsixChange)) ;
        			if(yAsixChange != 0) centerOfGrid = centerOfGrid.add(Vup.scale(-yAsixChange)) ;
        			tempRayVector = centerOfGrid.subtract(point);
                	if(n.dotProduct(tempRayVector) < 0 && direction == 1) //refraction
                		rays.add(new Ray(point, tempRayVector, n));
                	if(n.dotProduct(tempRayVector) > 0 && direction == -1) //reflection
                		rays.add(new Ray(point, tempRayVector, n));
                	centerOfGrid = tempcenterOfGrid;
        		}
        }
        return rays;
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
		for (int i = 0; i < sizeOfGrid; i=i+1) {
			for (int j = 0; j < sizeOfGrid; j=j+1) {
				double z=ray.getDir().getHead().findZ(x, y, d); 
		        Vector vec = new Vector(x,y,z).normalize();	
		        listRay.add(new Ray(ray.getP0(),vec));
				 y++;
			}
			x++;
			y=-4;
		}
		return listRay;
	}

	/**
	 * The function calculates and returns the color of a single point
	 * @param point ,ray
	 * @return scene.ambientLight.getIntensity
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
		.add(scene.ambientLight.getIntensity());
		}
  private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
	  if(intersection==null) {
			return Color.BLACK;
	  }
	  Color color = intersection.geometry.getEmmission();
	  color = color.add(calcLocalEffects(intersection, ray,k));
	  return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
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

public RayTracerBasic setSizeOfGrid(int sizeOfGrid) {
	this.sizeOfGrid = sizeOfGrid;
	return this;
}
	
}
