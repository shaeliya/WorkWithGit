/**
 * 
 */
package unittests;
import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50), 50) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
				new Sphere(new Point3D(0, 0, -50), 25) //
						.setEmmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Picture of a "magen david"
	 */
	@Test
	public void ourPictureTest() {
	Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150).setDistance(1000);

	scene.geometries.add( //
					new Sphere(new Point3D(0, 0, -10), 50) //
					.setEmmission(new Color(95,158,160)) //
					.setMaterial(new Material().setkD(0.7).setkS(0.7).setnShininess(100).setkT(0.3)),
					new Triangle(new Point3D(0, 20, 0), new Point3D(20, -10,0),
							new Point3D(-20, -10, 0)) //
									.setEmmission(new Color(72,61,139)) //
									.setMaterial(new Material().setkR(0.1)),
	         new Triangle(new Point3D(20, 10, 2), new Point3D(0, -20, 2),
							new Point3D(-20, 10,2)) //
									.setEmmission(new Color(72,61,139)) //
									.setMaterial(new Material().setkR(0.1)));			
	
	scene.lights.add( //
			new SpotLight(new Color(190,150,150), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
					.setkL(0.0004).setkQ(0.0000006));
scene.background=new Color(70,130,180);
	Render render = new Render() //
			.setImageWriter(new ImageWriter("ourPicture", 500, 500)) //
			.setCamera(camera) //
			.setRayTracer(new RayTracerBasic(scene));
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

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(new Point3D(60, 50, -50), 30) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.1)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
