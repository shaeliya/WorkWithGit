//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//ID: 322290180
//mayapasha56@gmail.com
//=========================
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

public class Scene {


	public String name;
	public Color background= new Color(0,0,0);
	public AmbientLight ambientLight=new AmbientLight (new Color(0,0,0),0);
	public Geometries geometries= null;
	public List<LightSource> lights= new LinkedList<LightSource>() ;
	/**
	 * Constructure
	 * @param name -Scene name 
	 */
	public Scene(String name) {	
		super();
		this.name = name;
		geometries= new Geometries();
	}
	/**
	 * set Lights
	 * @param lights
	 * @return Scene
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
/**
 * set Background
 * @param background
 * @return Scene
 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	/**
	 * set AmbientLight
	 * @param ambientLight
	 * @return Scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;

	}
	/**
	 * set Geometries
	 * @param geometries
	 * @return Scene
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;

	}
	
}
