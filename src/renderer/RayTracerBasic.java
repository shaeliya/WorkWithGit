//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package renderer;
import java.util.LinkedList;
import java.util.List;
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
	 * isRunImprovement
	 */
	private boolean isRunImprovement=false;
	/**
	 * max Rec
	 */
     private int maxRec=1;
	/**
	 * set RunImprovement
	 * @param isRunImprovement
	 * @return
	 */
	public RayTracerBasic setRunImprovement(boolean isRunImprovement) {
		this.isRunImprovement = isRunImprovement;
		return this;
	}

/**
 * set MaxRec
 * @param maxRec
 * @return
 */
	public RayTracerBasic setMaxRec(int maxRec) {
		this.maxRec = maxRec;
		return this;
	}


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

	
	public RayTracerBasic setDistanceForDiffusedAndGlossy(double distanceForDiffusedAndGlossy) {
		this.distanceForDiffusedAndGlossy = distanceForDiffusedAndGlossy;
		return this;
	}



	public RayTracerBasic setNumOfRaysForDiffusedAndGlossy(int numOfRaysForDiffusedAndGlossy) {
		this.numOfRaysForDiffusedAndGlossy = numOfRaysForDiffusedAndGlossy;
		return this;
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
		if (isImprovement==true && reflectedPoint!=null) {
			try
			{
				if(this.isRunImprovement == true && level != 1) {
					int levelOfCheck = 1;
					try {
				    color=superSampling(-1,0,0,sizeOfGrid,n, geopoint, reflectedRay,reflectedPoint, levelOfCheck, level, kr,kkr);
					}
					catch  (Exception e){}
				}
				else {
				// reflected=-1,refracted=1
			List<Ray> reflectedRays = RaysOfGrid(n,geopoint.point,reflectedRay.getDir(),-1);
			Color tempColor1 = Color.BLACK;
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
		if (isImprovement==true && refractedPoint!= null) {
			try
			{
			  if(this.isRunImprovement== true && level!= 1) {
					int levelOfCheck = 1;
					try {
				    color=superSampling(1,0,0,sizeOfGrid,n, geopoint, refractedRay,refractedPoint, levelOfCheck, level, kt,kkt);
					}
					catch  (Exception e){}
				}
			  else {
			List<Ray> refractedRays = RaysOfGrid(n,geopoint.point,refractedRay.getDir(),1);
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
			}
			catch(Exception e)
			{}

		}		
		}
			
		return color;
	}
/**
 * create beam of ray
 * @param n
 * @param point
 * @param Vto
 * @param direction
 * @param DiffusedAndGlossy
 * @return
 * @throws Exception
 */
	private List<Ray> RaysOfGrid(Vector n, Point3D point, Vector Vto, int direction) throws Exception {
        if(direction != 1 && direction != -1)
            throw new IllegalArgumentException("direction must be 1 or -1");
        int numOfRowCol = isZero(sizeOfGrid)? 1: (int)Math.ceil(Math.sqrt(numOfRaysForDiffusedAndGlossy));
        Vector Vup = Vto.findRandomOrthogonal();//vector in the grid
        Vector Vright = Vto.crossProduct(Vup);//vector in the grid
        Point3D centerOfGrid = point.add(Vto.scale(distanceForDiffusedAndGlossy)); // center point of the grid
        double sizeOfCube = sizeOfGrid/numOfRowCol;//size of each cube in the grid
        List<Ray> rays = new LinkedList<Ray>();
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
/**
 * 
 * @param direction
 * @param colStart
 * @param rowStart
 * @param gridSize
 * @param n
 * @param geopoint
 * @param reflectedRay
 * @param reflectedPoint
 * @param levelOfCheck
 * @param level
 * @param kr
 * @param kkr
 * @return Color
 */
private Color superSampling(int direction, int colStart, int rowStart ,int gridSize,Vector n,GeoPoint geopoint,Ray reflectedRay,GeoPoint reflectedPoint,int levelOfCheck,int level, double kr,double kkr ) {
	// קרניים 4 יצירת
   	    boolean flag= true;
	    List<Ray> reflectedRays = RaysOfGrid(n,geopoint.point,reflectedRay.getDir(),direction,colStart,rowStart,gridSize,levelOfCheck);
		List <Color>tempColor1 = new LinkedList<Color>();
		List <Point3D> points= new LinkedList<Point3D>();
		Color color = Color.BLACK;
		if(reflectedPoint== null) {
			return this.scene.background;
		}
		for(Ray reflecRay: reflectedRays)// צבעים חישוב
		{
			GeoPoint reflecPoint = findClosestIntersection(reflecRay);
			points.add(reflecPoint.point);
			if (reflectedPoint != null) {
			tempColor1.add(new Color(calcColor(reflecPoint, reflecRay, level - 1, kkr).scale(kr)));
			}
			else {
				tempColor1 .add(scene.background.scale(kr));
			}
		}
		for (int i = 1; i < tempColor1.size(); i++) {// שווים הצבעים אם בדיקה
             if(tempColor1.get(i) != tempColor1.get(i-1)) {
                flag=false;//שונים הצבעים אם 
			}
		} 
		if (levelOfCheck==maxRec) {// צבעים חיבור
			Color sumColor=Color.BLACK;
			for(Color c: tempColor1) {
				color.add(c);
			}			
		   return color.add(sumColor.reduce(reflectedRays.size()));
		}
		if (flag== true) {
			if(tempColor1== null ) {
				return Color.BLACK;
			}
			return tempColor1.get(0);
		}
		Vector Vup = reflectedRay.getDir().findRandomOrthogonal();//vector in the grid
        Vector Vright = reflectedRay.getDir().crossProduct(Vup);//vector in the grid
		List <Point3D> centerPoint = findCenterNewPixels(points,Vup,Vright);
		Ray r1=new Ray( geopoint.point,centerPoint.get(0).subtract(geopoint.point));
		Ray r2=new Ray( geopoint.point,centerPoint.get(1).subtract(geopoint.point));
		Ray r3=new Ray( geopoint.point,centerPoint.get(2).subtract(geopoint.point));
		Ray r4=new Ray( geopoint.point,centerPoint.get(3).subtract(geopoint.point));
		GeoPoint g1 = findClosestIntersection(r1);
		GeoPoint g2 = findClosestIntersection(r2);
		GeoPoint g3 = findClosestIntersection(r3);
		GeoPoint g4 = findClosestIntersection(r4);
		return new Color(superSampling(direction,0,0,gridSize/2, n, geopoint, r1, g1, levelOfCheck--, level,  kr, kkr).reduce(4)//
	                                  .add(superSampling(direction,0,0,gridSize/2,n, geopoint, r2, g2, levelOfCheck--, level,  kr, kkr).reduce(4))//
	                                  .add(superSampling(direction,0,0,gridSize/2,n, geopoint, r3, g3, levelOfCheck--, level,  kr, kkr).reduce(4)//
	                                  .add(superSampling(direction,0,0,gridSize/2,n, geopoint, r4, g4, levelOfCheck--, level,  kr, kkr).reduce(4))));
}
/**
 * 
 * @param points
 * @param vUp
 * @param vRight
 * @return List<Point3D>
 */
private List<Point3D> findCenterNewPixels(List<Point3D> points, Vector vUp, Vector vRight)
{
	double w = points.get(0).distance(points.get(3));
	double h = points.get(0).distance(points.get(2));
	List<Point3D> centerPoints = new LinkedList<Point3D>();
	centerPoints.add(points.get(0).add(vRight.scale(w/-4)).add(vUp.scale(h/-4)));
	centerPoints.add(points.get(3).add(vRight.scale(w/4)).add(vUp.scale(h/-4)));
	centerPoints.add(points.get(2).add(vRight.scale(w/-4)).add(vUp.scale(h/4)));
	centerPoints.add(points.get(1).add(vRight.scale(w/4)).add(vUp.scale(h/4)));
	return centerPoints;
}

/**
 * 
 * @param n
 * @param point
 * @param Vto
 * @param direction
 * @param colStart
 * @param rowStart
 * @param gridSize
 * @param levelOfCheck
 * @return
 */
private List<Ray> RaysOfGrid(Vector n, Point3D point, Vector Vto, int direction,int colStart, int rowStart ,int gridSize,int levelOfCheck){
    if(direction != 1 && direction != -1)
        throw new IllegalArgumentException("direction must be 1 or -1");
    int numOfRowCol = isZero(gridSize)? 1: (int)Math.ceil(Math.sqrt(numOfRaysForDiffusedAndGlossy));
    Vector Vup = Vto.findRandomOrthogonal();//vector in the grid
    Vector Vright = Vto.crossProduct(Vup);//vector in the grid
    Point3D centerOfGrid = point.add(Vto.scale(distanceForDiffusedAndGlossy)); // center point of the grid
    double sizeOfCube = sizeOfGrid/numOfRowCol;//size of each cube in the grid
    if(this.isRunImprovement== true) {
        sizeOfCube = gridSize/(numOfRowCol/(Math.pow(2, levelOfCheck-1)));//size of each cube in the grid
    }
    List rays = new LinkedList<Ray>();
    n = n.dotProduct(Vto) > 0 ? n.scale(-direction) : n.scale(direction);//fix the normal direction
    Point3D tempcenterOfGrid = centerOfGrid;//save the center of the grid
    Vector tempRayVector;
    for (int row = rowStart; row < numOfRowCol; row++)
    {
    	double xAsixChange= (row - (numOfRowCol/2d))*sizeOfCube + sizeOfCube/2d;
    		for(int col = colStart; col < numOfRowCol; col++)
    		{
    			if((row==rowStart && col==colStart || row==rowStart && col==numOfRowCol-1 ||row==numOfRowCol-1 && col==colStart ||row==numOfRowCol-1 && col==numOfRowCol-1 )||isRunImprovement == false) {
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
    }
    return rays;
}



	
}