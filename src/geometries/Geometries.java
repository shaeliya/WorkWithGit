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
	
	List<Intersectable> intersectableList;
	@Override
	/**
	 * Realization of the interface Intersectable
	 */
	public List<Point3D> findIntsersections(Ray ray) {
		
		List<Point3D> intersectionPointsList= new LinkedList<Point3D>();
		for (int i = 0; i < intersectableList.size(); i++) {
			List<Point3D> intersectionPointsTemp=intersectableList.get(i).findIntsersections(ray);
			if(intersectionPointsTemp!= null) {
				intersectionPointsList.addAll(intersectionPointsTemp);
			}
		}
		if(isZero(intersectionPointsList.size())) {
		return null;
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
