package primitives;
import static java.lang.Math.sqrt;

public class Point3D {

	Coordinate x;
	Coordinate y;
	Coordinate z;
	
	 public static Point3D ZERO= new Point3D(0,0,0);

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point3D(double x, double y, double z) {
		super();
		Coordinate cor1= new Coordinate(x);
		Coordinate cor2= new Coordinate(y);
		Coordinate cor3= new Coordinate(z);
		this.x = cor1;
		this.y = cor2;
		this.z = cor3;
	}
	
 public Vector subtract (Point3D point) {	
	double Px= this.x.coord-point.x.coord;
	double Py=this.y.coord- point.y.coord;
	double Pz= this.z.coord-point.z.coord;	
	Point3D p3D= new Point3D(Px,Py,Pz);
	return new Vector(p3D);

 }
 public Point3D add (Vector vec) {
	 double coorX= vec.head.x.coord+this.x.coord;
	 double coorY= vec.head.y.coord+this.y.coord;
	 double coorZ= vec.head.z.coord+this.z.coord;
	 Point3D point=new Point3D(coorX,coorY,coorZ);
	 return  point;
 }
 public double distanceSquared(Point3D point) {
		double coorX = point.x.coord-this.x.coord;
		double coorY= point.y.coord-this.y.coord;
		double coorZ= point.z.coord-this.z.coord;	
		double disSquared = coorX*coorX+coorY*coorY+coorZ*coorZ;
	 return disSquared;
 } 
 public double distance(Point3D point) {
	 double disSquared=distanceSquared(point);
	 double dis=sqrt( disSquared);
	 return dis;
 }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		
		return this.x.equals(other.x)&& this.y.equals(other.y)&& this.z.equals(other.z);
	}
	@Override
	public String toString() {
		
		return super.toString()+ "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	

}
