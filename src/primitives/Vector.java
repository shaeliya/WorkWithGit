package primitives;
import static java.lang.Math.sqrt;
public class Vector {
Point3D head;

public Vector(Point3D head)  throws IllegalArgumentException{
	super();
if(head.equals(Point3D.ZERO)){		
		
		throw new IllegalArgumentException  ("Vector zero is invalid");
	}
	this.head = head;
}

public Vector(Coordinate coorX,Coordinate coorY,Coordinate coorZ ) throws IllegalArgumentException {
Point3D point= new Point3D (coorX,coorY,coorZ);
if(point.equals(Point3D.ZERO)) {		
			
			throw new IllegalArgumentException  ("Vector zero is invalid");
		}
	this.head=point;
}
public Vector(double x,double y,double z )  throws IllegalArgumentException{
Point3D point= new Point3D (x,y,z);
if(point.equals(Point3D.ZERO)) {		
			
			throw new IllegalArgumentException  ("Vector zero is invalid");
		}
	this.head=point;
}
public Point3D getHead() {
	return head;
}

public Vector subtract (Vector vec) {
Vector vector = new Vector(this.head.subtract(vec.head).head);
 return vector;
}
public Vector add (Vector vec) {
Vector vector = new Vector(this.head.add(vec));	
return vector;
	
}
public Vector scale (double scal) {
	double coorX=this.head.x.coord*scal;
	double coorY=this.head.y.coord*scal;
	double coorZ=this.head.z.coord*scal;
	Vector vec = new Vector (coorX,coorY,coorZ);
	return vec;
} 
public double dotProduct(Vector vec) {
	double coorX= this.head.x.coord*vec.head.x.coord;
	double coorY= this.head.y.coord*vec.head.y.coord;
	double coorZ= this.head.z.coord*vec.head.z.coord;
return coorX+coorY+coorZ;
}

public Vector crossProduct (Vector vec) {
	
	double coorX= this.head.y.coord*vec.head.z.coord-this.head.z.coord*vec.head.y.coord;
	double coorY= this.head.z.coord*vec.head.x.coord-this.head.x.coord*vec.head.z.coord;
	double coorZ= this.head.x.coord*vec.head.y.coord-this.head.y.coord*vec.head.x.coord;
	Vector vector = new Vector (coorX,coorY,coorZ);
	return vector;
}
public double lengthSquared () {
	
	double length=this.head.x.coord*this.head.x.coord+this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord;
	return length;
}
public double length () {
double length = this.lengthSquared()	;
return sqrt(length);
}
public Vector normalize() {	
Coordinate coorX = new Coordinate( this.head.x.coord/this.length());	
Coordinate coorY =  new Coordinate(this.head.y.coord/this.length());	
Coordinate coorZ=  new Coordinate(this.head.z.coord/this.length());
this.head.x=coorX;
this.head.y=coorY;
this.head.z= coorZ;
return this;
}
public Vector normalized () {
Vector vec= new Vector (this.head);
vec.normalize();
return vec;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vector other = (Vector) obj;
	return this.head.equals(other.head);
}

@Override
public String toString() {
	return super.toString()+ "Vector [head=" + head + "]";
}



}
