//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package geometries;
import static primitives.Util.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
/**
 * The class creates a collection of shapes
 * @author shalh
 *
 */
public class Geometries implements Intersectable {
	
	public List<Intersectable> intersectableList;
	@Override
	/**
	 * Realization of the interface Intersectable
	 */
	public List<Point3D> findIntsersections(Ray ray) {
		List<Point3D> intersectionPointsList= null;
		for (int i = 0; i < intersectableList.size(); i++) {
			List<Point3D> intersectionPointsTemp=intersectableList.get(i).findIntsersections(ray);
			if(intersectionPointsTemp!= null) {
				if(intersectionPointsList==null) {
				intersectionPointsList= new LinkedList<Point3D>();
				}
				intersectionPointsList.addAll(intersectionPointsTemp);
				
			}
		}
		return intersectionPointsList;
	}
	/**
	 * constructor
	 */
	public Geometries() {
		super();
		this.intersectableList = new LinkedList();		
	}
	/**
	 * constructor
	 */
	public Geometries(Intersectable... geometries) {
		super();
		this.intersectableList = List.of(geometries);

}
	public void add(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; i++) {
			this.intersectableList.add(geometries[i]);
		}
		
	}
}
