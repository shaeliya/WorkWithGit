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
	
	public ImageWriter getImageWriter() {
		return imageWriter;
	}
	public void setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public RayTracerBase getRayTracerBase() {
		return rayTracerBase;
	}
	public void setRayTracerBase(RayTracerBase rayTracerBase)  {
		this.rayTracerBase = rayTracerBase;
	} 
	public void renderImage() {
		try {
			if(rayTracerBase==null||camera==null||scene==null||imageWriter==null) {
				throw new MissingResourceException(null, null, null);
			}
		}
		catch (MissingResourceException e) {
//			throw new NotImplementedException();
		}
		for (int i = 0; i < this.imageWriter.getNx(); i++) {
			for (int j = 0; j <this.imageWriter.getNy() ; j++) {
				Ray ray=this.camera.constructRayThroughPixel(this.imageWriter.getNx(),this.imageWriter.getNy(), j, i);
				Color color=this.rayTracerBase.traceRay(ray);
				// יכול ליהיות שצריך להחליף בין הפרמטרים
				this.imageWriter.writePixel(i, j, color);
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
	}
	public void writeToImage() {
		if (imageWriter==null) {
			throw new MissingResourceException(null, null, null);
		}
		this.imageWriter.writeToImage();
	}

}
