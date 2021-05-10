package primitives;
/**
 * class of Material
 * @author shalh
 *
 */
public class Material {
public double kD=0,kS=0;
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
 * setn Shininess
 * @param nShininess
 * @return Material
 */
public Material setnShininess(int nShininess) {
	this.nShininess = nShininess;
	return this;

}

}
