//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
package unittests;

import static org.junit.Assert.*;
import renderer.*;
import org.junit.Test;

import primitives.Color;

public class ImageWriterTest {

	@Test
	public void testWriteToImage() {
		// create image 
		ImageWriter imageW=new ImageWriter("image", 800,  500);
		Color color= new Color(255,0,0);
		Color greedColor= new Color(0,255,0);
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				// create the grid
				if(i%50==0||j%50==0) {
					imageW.writePixel( i, j,  greedColor);
				}
				else {
				imageW.writePixel( i, j,  color);
				}
			}
		}
		imageW.writeToImage();		
   }

}
