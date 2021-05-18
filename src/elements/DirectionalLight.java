package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * class of Directional Light extends Light
 * @author shalh
 *
 */
public class DirectionalLight extends Light  implements LightSource {
private Vector direction;
/**
 * constructor
 * @param intensity
 * @param direction
 */
	public DirectionalLight(Color intensity, Vector direction) {
	super(intensity);
	this.direction = direction.normalize();
}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return direction;
	}

	@Override
	public double getDistance(Point3D point) {
		
		return Double.POSITIVE_INFINITY;
	}

}
