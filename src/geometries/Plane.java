package geometries;
import  primitives.Point3D;
import  primitives.Vector;

//מישור
public class Plane implements Geometry{
Point3D q0 ;
Vector normal;

public Vector getNormal() {
	
	return normal;
}

public Vector getNormal(Point3D point) {
	
	return normal;
}
public Plane(Point3D q0, Vector normal) {
	super();
	this.q0 = q0;
	this.normal = normal;
}

public Plane(Point3D p1, Point3D p2,Point3D p3 ) {
	//Vector vec1 = new Vector(p2.subtract(p1).getHead());
	//Vector vec2= new Vector(p3.subtract(p1).getHead());
	//this.normal=vec1.crossProduct(vec2);
	this.normal=null;
	this.q0=p2;
}

}
