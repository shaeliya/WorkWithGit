//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package scene;

import elements.AmbientLight;
import geometries.*;
import primitives.*;

public class Scene {


	String name;
	Color background= new Color(0,0,0);
	AmbientLight ambientLight=new AmbientLight (new Color(0,0,0),0);
	Geometries geometries= null;
	/**
	 * Constructure
	 * @param name 
	 */
	public Scene(String name) {	
		super();
		this.name = name;
		geometries= new Geometries();
	}
	
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;

	}
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;

	}
	
}
