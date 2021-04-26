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

//תאורה סביבתית
public class AmbientLight {

	private Color intensity ;
	/**
	 * Constructure
	 * @param iA light of original filling
	 * @param kA Coefficient of attenuation of filler light
	 */
	public AmbientLight(Color iA, double kA) {
		super();
		this.intensity=iA.scale(kA);
	}
	public Color getIntensity() {
		return intensity;
	}
	
}
