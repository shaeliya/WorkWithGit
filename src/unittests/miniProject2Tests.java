package unittests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class miniProject2Tests {
	
	private Color randomcolors()  {
		  Random random = new Random();
	     int choice =random.nextInt(10);
	     if (choice==0) {//yellowGreen
	    	 return new Color(154,205,50);
	     }
	     if (choice==1) {//beige
	    	 return new Color(245,245,120);
	     }
	     if (choice==2) {//salmon
	    	 return new Color(250,128,114);
	     }
	     if (choice==3) {//crimson
	    	 return new Color(220,20,60);
	     }
	     if (choice==4) {//orange red	
	    	 return new Color(255,69,0);
	     }
	     if (choice==5) {//gold
	    	 return new Color(255,215,0);
	     }
	     if (choice==6) {//turquoise
	    	 return new Color(64,224,208);
	     }
	     if (choice==7) {//darkMagenta
	    	 return new Color(139,0,139);
	     }
	     if (choice==8) {//navy
	    	 return new Color(0,0,128);
	     }
	     if (choice==9) {//darkCyan
	    	 return new Color(0,139,139);
	     }
	     return scene.background;
	  }

private Scene scene = new Scene("Test scene");
	@Test
	public void ourPictureTest() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);
		psifasMeshulashim(10,75,75,-1);
		scene.lights.add( //
				new SpotLight(new Color(10,10,10), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));
		scene.lights.add(//
				new PointLight(new Color(50,50,50), new Point3D(-60,30,600))//
								.setkL(0.00001).setkQ(0.000001));
		scene.lights.add(new DirectionalLight(new Color(10,10,10), new Vector(1, 1, -1)));
	scene.background=new Color(204,255,255);
		Render render = new Render().setDebugPrint().setMultithreading(3) //
				.setImageWriter(new ImageWriter("pictureMP2Psifas", 500, 500)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene).setImprovement(true).setRunImprovement(true).setMaxRec(4));
		render.renderImage();
		render.writeToImage();
		}
	
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
		Render render = new Render().setDebugPrint().setMultithreading(3) //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene).setImprovement(true).setRunImprovement(true).setMaxRec(4));

		render.renderImage();
		render.writeToImage();
	}

	private void psifasMeshulashim(int num, int vpSizeX, int vpSizeY, int z) {
		for (int i = -vpSizeX; i < vpSizeX; i=i+num) {
			for (int j = -vpSizeY; j < vpSizeY; j=j+ num) {
				scene.geometries.add(//
						new Triangle(new Point3D(i,j+num,z), new Point3D(i+num,j+num,z),new Point3D(i+num,j,z))
						   .setEmmission(randomcolors())
					      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)),
						new Triangle(new Point3D(i,j+num,z), new Point3D(i,j,z),new Point3D(i+num,j,z))
					      .setEmmission(randomcolors())
					      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)));
			}
					
		}
		} 
	}
