//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package primitives;
/**
 * A department that creates a ray
 * @author shalh
 *
 */
public class Ray {
Point3D p0;
Vector dir;
/**
 * constractor
 * @param p0
 * @param dir
 */
public Ray(Point3D p0, Vector dir) {
	super();
	//dir.normalize();
	this.p0 = p0;
	this.dir = dir.normalized();
}


public Point3D getP0() {
	return p0;
}


public Vector getDir() {
	return dir;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Ray other = (Ray) obj;
	
	return this.p0.equals(other.p0)&& this.dir.equals(other.dir);
}
public Point3D getPoint(double t) {
Point3D p=p0.add(dir.scale(t));
return p;
	
}
}
