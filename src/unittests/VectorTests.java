//========================
//Shalhevet Eliyahu 
//ID:211661160
//shalhevet2001@gmail.com
//Maya Pasha
//322290180
//mayapasha56@gmail.com
//=========================
/**
 *  
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static primitives.Util.*;
import org.junit.Test;
import primitives.Vector;
/**
 * @author shalh
 * Unit tests for primitives.Vector class
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		
		   Vector v1 = new Vector(1, 2, 3);
	       Vector v2 = new Vector(1, 2, 3);	   
	       Vector v6 = new Vector(1, 2, 4);
	       Vector v4= new Vector(2,4,7);
	       Vector v5=v4.subtract(v2);
	       try{
			 // =============== Boundary Values Tests ==================
	       Vector v3 = v1.subtract(v2);        
	       
	       //Creating a zero vector by subtracting similar vectors 

	       assertEquals("Test fail, the vector is zero",0.0,v3.length(),0.00001);
	       }
	       catch(IllegalArgumentException ex)
			{
		        assertTrue("test fail, the vector is not zero",true);

			}
	       
	       //============ Equivalence Partitions Tests =============
	       
           //Creating a vector by subtracting two different vectors
	       
	       assertEquals("Test fail, the length is inccorrect",v6.length(),v5.length() ,0.00001);
	       assertTrue("test fail- The add operation did not work well ",v5.equals(v6));

		
		
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {		

			   Vector v1 = new Vector(-1, -2, -3);
		       Vector v2 = new Vector(1, 2, 3);	   	    
		       Vector v6 = new Vector(3, 6, 10);
		       Vector v4= new Vector(2,4,7);
		       Vector v5=v4.add(v2);
		       try{ 
		    	   
		    	   Vector v3 = v1.add(v2);        
			// =============== Boundary Values Tests ==================
		     //Creating a zero vector by adding vectors 
		       assertEquals("Test fail, the vector is zero",0.0,v3.length(),0.00001); 
		       }
		       catch(IllegalArgumentException ex)
				{
			        assertTrue("test fail, the vector is not zero",true);
				}
		       //============ Equivalence Partitions Tests =============
		       //Creating a vector by adding two different vectors
		       assertEquals("Test fail,the length is inccorrect",v6.length(),v5.length() ,0.00001);	
		       assertTrue("test fail- The add operation did not work well ",v5.equals(v6));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		
	 Vector v1 = new Vector(1, 2, 3);	
	 double num= 0.0;
	
	 double num2= 7;
	 Vector v4=v1.scale(num2);
	 Vector v5= new Vector(7,14,21);
	 try {
		 
		 //Multiplying the zero vector by a scalar
		 
	 Vector v3 = new Vector(0, 0, 0);
	 
     fail("Cannot create vector zero");
	 } 
	 
     catch(IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}	
       try {
		 	 
		// =============== Boundary Values Tests ==================	 
	        Vector v2= v1.scale(num);	 
		 //Vector multiplication by zero scalar
		 
		 assertEquals("test fail, the vector is zero",0.0,v2.length(),0.00001);
           }
       catch(IllegalArgumentException ex)
	       {
		assertTrue("test fail, the vector is not zero",true);
	       }	
	 
	 //============ Equivalence Partitions Tests =============	 
	 //Multiplying a vector by a scalar	 
	 assertEquals("test fail, the vector is zero",v5.length(),v4.length(),0.00001);
     assertTrue("test fail- The add operation did not work well ",v4.equals(v5));


	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		try {
			
			 Vector v1 = new Vector(0, 0, 0);
			 Vector v2 = new Vector(1, 2, 3);
			 double num= v1.dotProduct(v2);
			 
			 fail("Failed test,  DotProduct cannot be done on zero vector");
		}
		catch(IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}			
		 Vector v3 = new Vector(1, 2, 3);
		 Vector v4 = new Vector(5, 2, 1);
		 Double result1 = v3.dotProduct(v4);		
		//DotProduct two vectors and get an incorrect result		 
		 assertEquals("Failed test, the Dot Product get a incorrect result ",12,result1,0.00001);		
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        	assertTrue("test fail, the vector is not zero", true);
        }
        

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v1 = new Vector(1, 2, 3);
        double len1= v1.lengthSquared();

         // ============ Equivalence Partitions Tests ==============
        
        
        //Calculate the vector length squared and expect to get your correct length
        
        assertEquals("The vector length squared is incorrect", 14,len1,0.00000001);
        
        // =============== Boundary Values Tests ==================
        // test zero vector from LengthSquared
        try {
    		Vector v2 = new Vector(0, 0, 0);
    		double len2 = v2.lengthSquared();
    		fail("The zero vector length cannot be calculated");
        }
        catch (IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}	
        
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		
		Vector v1 = new Vector(1, 2, 2);
        double len1= v1.length();

         // ============ Equivalence Partitions Tests ==============
        
        
        //Calculate the vector length and expect to get your correct length
        
        assertEquals("The vector length is incorrect", 3,len1,0.00000001);
        
        // =============== Boundary Values Tests ==================
        // test zero vector from Length
        try {
    		Vector v2 = new Vector(0, 0, 0);
    		double len2 = v2.length();
    		fail("The zero vector length cannot be calculated");
        }
        catch (IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}	
        
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		
		Vector v1 = new Vector(1, 2, 2);
        v1.normalize();
         // ============ Equivalence Partitions Tests ==============
         //Normalize the vector and return the vector itself
        assertEquals("Vector did not normalize", 1,v1.length(),0.00000001);
        
        // =============== Boundary Values Tests ==================
        // test zero vector from Normalize
        try {
    		Vector v2 = new Vector(0, 0, 0);
    		 v2.normalize();
    		fail("The zero vector cannot be Normalize");
        }
        catch (IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}	
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		
		Vector v1 = new Vector(1, 2, 2);
		Vector v3 =v1.normalized();
         // ============ Equivalence Partitions Tests ==============
         //Create a new vector and normalize it
        assertEquals("Vector did not normalized", 1,v3.length(),0.00000001);
        
        // =============== Boundary Values Tests ==================
        // test zero vector from Normalized
        try {
    		Vector v2 = new Vector(0, 0, 0);
    		Vector v4 = v2.normalized();
    		fail("The zero vector cannot be Normalized");
        }
        catch (IllegalArgumentException ex)
		{
			assertTrue("test fail, the vector is not zero",true);
		}	
	}

}
