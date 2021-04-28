//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package renderer;
import primitives.*;

import scene.*;
/**ray scanner
 * Class for calculations of a ray with a scene
 * @author shalh
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;
/**
 * constructor
 * @param scene
 */
	public RayTracerBase(Scene scene) {
		super();
		this.scene = scene;
	}
	/**
	 * The function receives a ray and returns the color of the point it cuts in the scene
	 * @param ray
	 * @return Color
	 */
	public abstract Color traceRay (Ray ray) ;
}
