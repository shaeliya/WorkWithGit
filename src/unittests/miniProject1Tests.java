package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class miniProject1Tests {
	private Scene scene = new Scene("Test scene");
	@Test
	public void ourPictureTest() {
	Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150).setDistance(1000);
	scene.geometries.add( //
	new Triangle(new Point3D(40, 30, -300), new Point3D(40, -20, -300),
			new Point3D(0, 0,-300)) //
					.setEmmission(new Color(127,31,107)) //
					.setMaterial(new Material().setkR(0.1)),			
	new Triangle(new Point3D(0, 0, -300), new Point3D(40, -20, -300),
			new Point3D(0, -50,-300)) //
					 .setEmmission(new Color(74,21,127)) //
					 .setMaterial(new Material().setkR(0.1)),			
	new Triangle(new Point3D(0, 0, -300), new Point3D(0, -50, -300),
			new Point3D(-40, -20,-300)) //
					  .setEmmission(new Color(24,65,146)) //
					  .setMaterial(new Material().setkR(0.1)),		
	new Triangle(new Point3D(-40, 30, -300), new Point3D(0, 0, -300),
			new Point3D(-40, -20,-300)) //
						.setEmmission(new Color(23,147,185)) //
						.setMaterial(new Material().setkR(0.1)),
						
	new Sphere(new Point3D(80, 80, -300), 15) //
						.setEmmission(new Color(50,10,80)) //
						.setMaterial(new Material().setkD(0.7).setkS(0.7).setnShininess(100).setkT(0.6)),
	new Sphere(new Point3D(-80, -80, -300), 15) //
						.setEmmission(new Color(10,30,90)) //
						.setMaterial(new Material().setkD(0.7).setkS(0.7).setnShininess(100).setkT(0.6)),
		/*				
	new Triangle(new Point3D(-100, 100, -200), new Point3D(-70, 100, -300),
			new Point3D(-100, 70,-300)) //
					.setEmmission(new Color(23,147,185)) //
					.setMaterial(new Material().setkR(0.1).setkT(0.2)),
	new Triangle(new Point3D(100, -100, -200), new Point3D(70, -100, -300),
			new Point3D(100, -70,-300)) //
			        .setEmmission(new Color(70,15,125)) //
					.setMaterial(new Material().setkR(0.1).setkT(0.4)),
					*/
	new Plane(new Point3D(80,0,-400),new Vector(-1,0,5))
	  .setEmmission(new Color(74,21,127)) //right
      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)),
    new Plane(new Point3D(-80,0,-400),new Vector(1,0,5))
	  .setEmmission(new Color(23,147,185)) //left
      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)),
    new Plane(new Point3D(0,80,-400),new Vector(0,-1,5))
	  .setEmmission(new Color(24,65,146)) //up
      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)),	
    new Plane(new Point3D(0,-80,-400),new Vector(0,1,5))
	  .setEmmission(new Color(87,0,67)) //down
      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)));
	
	scene.lights.add( //
			new SpotLight(new Color(10,10,10), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
					.setkL(0.0004).setkQ(0.0000006));
	scene.lights.add(//
			new PointLight(new Color(50,50,50), new Point3D(-60,30,600))//
							.setkL(0.00001).setkQ(0.000001));
	scene.lights.add(new DirectionalLight(new Color(10,10,10), new Vector(1, 1, -1)));
scene.background=new Color(204,255,255);
	Render render = new Render() //
			.setImageWriter(new ImageWriter("pictureMP1Sphere-noChaeng", 500, 500)) //
			.setCamera(camera) //
			.setRayTracer(new RayTracerBasic(scene).setImprovement(false));
	render.renderImage();
	render.writeToImage();
	}


/**
 * Produce a picture of a sphere lighted by a spot light
 */
@Test
public void twoSpheresOnMirrors() {
	Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(2500, 2500).setDistance(10000); //

	scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
	scene.geometries.add( //
			new Sphere(new Point3D(-950, -900, -1000), 400) //
					.setEmmission(new Color(0, 0, 100)) //
					.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
			new Sphere(new Point3D(-950, -900, -1000), 200) //
					.setEmmission(new Color(100, 20, 20)) //
					.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
			new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
					new Point3D(670, 670, 3000)) //
							.setEmmission(new Color(20, 20, 20)) //
							.setMaterial(new Material().setkR(1)),
			new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
					new Point3D(-1500, -1500, -2000)) //
							.setEmmission(new Color(20, 20, 20)) //
							.setMaterial(new Material().setkR(0.5)));

	scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
			.setkL(0.00001).setkQ(0.000005));

	ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored1", 500, 500);
	Render render = new Render() //
			.setImageWriter(imageWriter) //
			.setCamera(camera) //
			.setRayTracer(new RayTracerBasic(scene).setImprovement(true));

	render.renderImage();
	render.writeToImage();
}

}
