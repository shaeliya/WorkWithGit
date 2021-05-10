//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package renderer;

import java.util.MissingResourceException;

import elements.*;
import primitives.Color;
import primitives.Ray;
import scene.*;

/**
 * The function creates the color matrix of the image from the scene
 * @author shalh
 *
 */
public class Render {
	private ImageWriter imageWriter;
	private Scene scene;
	private Camera camera;
	private RayTracerBase rayTracerBase;
	
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}
	
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}
	
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}
	
	public Render setRayTracer(RayTracerBase rayTracerBase)  {
		this.rayTracerBase = rayTracerBase;
		return this;
	} 
	
public void renderImage() {
	try {
		if(rayTracerBase==null||camera==null||scene==null||imageWriter==null) {
				throw new MissingResourceException(null, null, null);
			}
	}
		catch (MissingResourceException e) {
			throw new UnsupportedOperationException();
		}
		for (int i = 0; i < this.imageWriter.getNx(); i++) {
			for (int j = 0; j <this.imageWriter.getNy() ; j++) {
				Ray ray=this.camera.constructRayThroughPixel(this.imageWriter.getNx(),this.imageWriter.getNy(), j, i);
				Color color=this.rayTracerBase.traceRay(ray);
				this.imageWriter.writePixel(j, i, color);
			}
		}
	}

	/**
	 * The function create a grid of lines
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter==null) {
			throw new MissingResourceException(null, null, null);
		}
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				// create the grid
				if(i%interval==0||j%interval==0) {
					imageWriter.writePixel( i, j,  color);
				}				
			}
		}
	}
	public void writeToImage() {
		if (imageWriter==null) {
			throw new MissingResourceException(null, null, null);
		}
		this.imageWriter.writeToImage();
	}

}
