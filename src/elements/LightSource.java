package elements;

import primitives.*;

/**
 * interface of Light Source
 * @author shalh
 *
 */
public interface LightSource {
	/**
	 * get the Intensity of point
	 * @param p
	 * @return
	 */
	public Color getIntensity(Point3D p);
	/**
	 * get the Light
	 * @param p
	 * @return
	 */
	public Vector getL(Point3D p);
	/**
	 * get the Distance of the light
	 * @param point
	 * @return
	 */
	public double getDistance(Point3D point);

}
