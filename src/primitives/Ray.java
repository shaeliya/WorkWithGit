package primitives;

public class Ray {
Point3D p0;
Vector dir;

public Ray(Point3D p0, Vector dir) {
	super();
	dir.normalize();
	this.p0 = p0;
	this.dir = dir;
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

}
