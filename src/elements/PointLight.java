package elements;

import primitives.*;
import static primitives.Util.*;

/**
 * class of Point Light extends from Light 
 * @author shalh
 *
 */
public class PointLight extends Light implements LightSource {
private Point3D position;
private double kC=1,kL,kQ;
/**
 * constructor
 * @param intensity
 * @param position
 * @param kC
 * @param kL
 * @param kQ
 */
	public PointLight(Color intensity, Point3D position) {
	super(intensity);
	this.position = position;
}

	@Override
	public Color getIntensity(Point3D p) {

		double d= p.distance(position) ;
		if(isZero((kC+(kL*d)+(kQ*(d*d))))) {
			throw new IllegalArgumentException("To divide by zero is invalid");
		}
		Color iL=intensity.scale(1/(kC+(kL*d)+(kQ*(d*d))));
		return iL;
	}


	@Override
	public Vector getL(Point3D p) {
		if (p.equals(position)) {
	         return null;
	}
		Vector vec=p.subtract(position).normalize();
	return vec;
	}



	public void setPosition(Point3D position) {
		this.position = position;
	}

	
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}


	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}


	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public double getDistance(Point3D point) {
		
		return position.distance(point);
	}
	

}
