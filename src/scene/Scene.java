//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//ID: 322290180
//mayapasha56@gmail.com
//=========================
package scene;

import elements.AmbientLight;
import geometries.*;
import primitives.*;

public class Scene {


	public String name;
	public Color background= new Color(0,0,0);
	public AmbientLight ambientLight=new AmbientLight (new Color(0,0,0),0);
	public Geometries geometries= null;
	/**
	 * Constructure
	 * @param name -Scene name 
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
