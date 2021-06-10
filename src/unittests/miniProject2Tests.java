package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class miniProject2Tests {
private Scene scene = new Scene("Test scene");
	@Test
	public void ourPictureTest() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);
		psifasMeshulashim(5,150,150);
		scene.lights.add( //
				new SpotLight(new Color(10,10,10), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));
		scene.lights.add(//
				new PointLight(new Color(50,50,50), new Point3D(-60,30,600))//
								.setkL(0.00001).setkQ(0.000001));
		scene.lights.add(new DirectionalLight(new Color(10,10,10), new Vector(1, 1, -1)));
	scene.background=new Color(204,255,255);
		Render render = new Render().setDebugPrint().setMultithreading(3) //
				.setImageWriter(new ImageWriter("pictureMP2Sphere", 500, 500)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
		}
	private void psifasMeshulashim(int num, int vpSizeX, int vpSizeY) {
		for (int i = -vpSizeX; i < vpSizeX; i=i+num) {
			for (int j = -vpSizeY; j < vpSizeY; j=j+ num) {
				scene.geometries.add(//
						new Triangle(new Point3D(i,j+num,-1), new Point3D(i+num,j+num,-1),new Point3D(i+num,j,-1))
						   .setEmmission(new Color(255,0,0))
					      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)),
						new Triangle(new Point3D(i,j+num,-1), new Point3D(i,j,-1),new Point3D(i+num,j,-1))
					      .setEmmission(new Color(0,0,67))
					      .setMaterial(new Material().setkD(0.9).setkS(0.1).setnShininess(50).setkR(0.5)));
			}
					
		}
		} 
	}
