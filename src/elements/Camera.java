//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package elements;

import primitives.*;
import static primitives.Util.*;
import static primitives.Util.*;

public class Camera {
	private Point3D p0;
	private Vector vTo;
	private Vector vUp;
	private Vector vRight;
	private double width; 
	private double height ;
	private double distance;
	/**
	 * constractor
	 * @param p0
	 * @param vTo
	 * @param vUp
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		super();
		this.p0 = p0;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		if(!isZero(vTo.dotProduct(vUp))) {
			throw new IllegalArgumentException  ("Vectors are not orthogonal");
		}
		this.vRight=vTo.crossProduct(vUp).normalize();
	}
	public Point3D getP0() {
		return p0;
	}
	public Vector getvTo() {
		return vTo;
	}
	
	public Vector getvUp() {
		return vUp;
	}
	
	public Vector getvRight() {
		return vRight;
	}
	/**
	 * Update method for the View Plane size, which receives width and height and returns the camera object itself
	 * @param width
	 * @param height
	 * @return
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.width=width;
		this.height= height;
		return this;
	}
	/**
	 * Update method for the View Plane distance, which receives distance and returns the camera object itself
	 * @param distance
	 * @return
	 */
	public Camera setDistance(double distance) {
		this.distance= distance;
		return this;
	}
	/**
	 * Returns a ray for a single pixel
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return ray
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		// case that distance is zero
		if(distance==0) {
			return null;
		}
		Point3D pC=p0.add(vTo.scale(distance));		
		double rY=this.height/nY;
		double rX= this.width/nX;
		double xJ= (j-((double)(nX-1)/2))*rX;
		double yI=-(i-((double)(nY-1)/2))*rY;
		Point3D pIJ= pC;
		if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
		//check if the subtract of p0 and pIJ is vector zero
		if(pIJ.equals(p0)) {
			return null;
		}
		Vector vIJ=pIJ.subtract(p0);
		Ray ray= new Ray(p0,vIJ);
		return ray;
		
	}
}
