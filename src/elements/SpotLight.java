package elements;
import static primitives.Util.*;
import primitives.*;
/**
 * class Spot Light extends from PointLight
 * @author shalh
 *
 */
public class SpotLight extends PointLight{
private Vector direction;
/**
 * constructor
 * @param intensity
 * @param direction
 */
	public SpotLight(Color intensity,Point3D position, Vector direction) {
		super(intensity,position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
	double pl=super.getL(p).dotProduct(direction);
	if (pl <= 0)
        return Color.BLACK;
	Color iL=super.getIntensity(p).scale( pl);
	return iL;
}

}
