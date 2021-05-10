package elements;

import primitives.*;

/**
 * abstract class of Light
 * @author shalh
 *
 */
abstract class Light {
protected Color intensity ;
/*
 * constructor
 */
protected Light(Color intensity) {
	super();
	this.intensity = intensity;
}
/*
 * get Intensity
 */
public Color getIntensity() {
	return intensity;
}

}
