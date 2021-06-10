//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package primitives;
import static java.lang.Math.sqrt;
/**
 * A class that creates a vector
 * @author shalh
 *
 */
public class Vector {
Point3D head;
/**
 * constractor
 * @param head
 * @throws IllegalArgumentException
 */
public Vector(Point3D head)  throws IllegalArgumentException{
	super();
if(head.equals(Point3D.ZERO)){		
		
		throw new IllegalArgumentException  ("Vector zero is invalid");
	}
	this.head = head;
}
/**
 * constractor
 * @param coorX
 * @param coorY
 * @param coorZ
 * @throws IllegalArgumentException
 */
public Vector(Coordinate coorX,Coordinate coorY,Coordinate coorZ ) throws IllegalArgumentException {
Point3D point= new Point3D (coorX,coorY,coorZ);
if(point.equals(Point3D.ZERO)) {		
			
			throw new IllegalArgumentException  ("Vector zero is invalid");
		}
	this.head=point;
}
/**
 * constractor
 * @param x
 * @param y
 * @param z
 * @throws IllegalArgumentException
 */
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
/**
 * Vector subtraction (Returns New Vector)
 * @param vec
 * @return
 */
public Vector subtract (Vector vec) {
Vector vector = new Vector(this.head.subtract(vec.head).head);
 return vector;
}
/**
 * Vector addition  (Returns New Vector)
 * @param vec
 * @return
 */
public Vector add (Vector vec) {
Vector vector = new Vector(this.head.add(vec));	
return vector;
	
}
/**
 * Vector Multiplier - Scalar (Returns New Vector)
 * @param scal
 * @return
 */
public Vector scale (double scal) {
	double coorX=this.head.x.coord*scal;
	double coorY=this.head.y.coord*scal;
	double coorZ=this.head.z.coord*scal;
	Vector vec = new Vector (coorX,coorY,coorZ);
	return vec;
}
/**
 * return dot-product
 * @param vec
 * @return
 */
public double dotProduct(Vector vec) {
	double coorX= this.head.x.coord*vec.head.x.coord;
	double coorY= this.head.y.coord*vec.head.y.coord;
	double coorZ= this.head.z.coord*vec.head.z.coord;
return coorX+coorY+coorZ;
}
/**
 * Vector multiplication - Returns a new vector that stands for the two existing vectors
 * @param vec
 * @return
 */
public Vector crossProduct (Vector vec) {
	
	double coorX= this.head.y.coord*vec.head.z.coord-this.head.z.coord*vec.head.y.coord;
	double coorY= this.head.z.coord*vec.head.x.coord-this.head.x.coord*vec.head.z.coord;
	double coorZ= this.head.x.coord*vec.head.y.coord-this.head.y.coord*vec.head.x.coord;
	Vector vector = new Vector (coorX,coorY,coorZ);
	return vector;
}
/**
 * return vector length squared
 * @return
 */
public double lengthSquared () {
	
	double length=this.head.x.coord*this.head.x.coord+this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord;
	return length;
}
/**
 * return vector length
 * @return
 */
public double length () {
double length = this.lengthSquared();
return sqrt(length);
}

/**
 * Vector normalization operation that will change the vector itself
 * @return
 */
public Vector normalize() {	
Coordinate coorX = new Coordinate( this.head.x.coord/this.length());	
Coordinate coorY =  new Coordinate(this.head.y.coord/this.length());	
Coordinate coorZ=  new Coordinate(this.head.z.coord/this.length());
this.head=new Point3D(coorX,coorY,coorZ);
return this;
}
/**
 * A normalization operation that returns a new normalized vector in the same direction as the original vector
 * @return
 */
public Vector normalized () {
Vector vec= new Vector (this.head);
vec.normalize();
return vec;
}


/**
 * Make this vector to be unit vector (in the same direction)
 * 
 * @return this vector itself (normalized)
 */


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
/**
 * find Random Orthogonal func
 * return an orthogonal vector
 */
public Vector findRandomOrthogonal() throws Exception
{
    double x = this.head.x.coord;
    double y = this.head.y.coord;
    double z = this.head.z.coord;
    double Ax= Math.abs(x), Ay= Math.abs(y), Az= Math.abs(z);
    if (Ax < Ay)
        return Ax < Az ?  new Vector(0, -z, y) : new Vector(-y, x, 0);
    else
        return Ay < Az ?  new Vector(z, 0, -x) : new Vector(-y, x, 0);
}

}
