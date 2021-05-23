package primitives;
/**
 * class of Material
 * @author shalh
 *
 */
public class Material {
public double kD=0,kS=0 ,kR =0.0, kT =0.0;
public int nShininess=0;
/**
 * set kD
 * @param kD
 * @return Material
 */
public Material setkD(double kD) {
	this.kD = kD;
	return this;
}
/**
 * set kS
 * @param kS
 * @return Material
 */
public Material setkS(double kS) {
	this.kS = kS;
	return this;

}
/**
 * 
 * @param kR
 * @return Material
 */
public Material setkR(double kR) {
	this.kR = kR;
	return this;

}
/**
 * 
 * @param kT
 * @return Material
 */
public Material setkT(double kT) {
	this.kT = kT;
	return this;

}

/**
 * setn Shininess
 * @param nShininess
 * @return Material
 */
public Material setnShininess(int nShininess) {
	this.nShininess = nShininess;
	return this;

}

}
