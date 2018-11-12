package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class PolynomTest {
	
	// we will not test an empty constructor as it is redundant.
	@Test
	public void testPconstrct1() {
		Polynom p1=new Polynom("3.2x^2 + 5.1x");
		assertEquals(p1.toString(),"3.2x^2 + 5.1x");
	}
	@Test
	public void testPconstrct2() {
		Polynom p0=new Polynom("3.2x^2 + 5.1x");
		Polynom p1=new Polynom(p0);
		assertEquals(p1.toString(),"3.2x^2 + 5.1x");
	}
	@Test
	public void testAdd() {
		Polynom p=new Polynom("2x^2+5x^3");
		p.add( new Monom(3,1) );
		assertEquals("5.0x^3 + 2.0x^2 + 3.0x", p.toString());
	
	}
	@Test
	public void testF() {
		Polynom p0=new Polynom("3x^2 + 5x");
		double f=p0.f(2);
		assertEquals(f,22.0);
		
	}
	@Test
	public void testSubstract() {
		Polynom p0=new Polynom("3x^2 + 5x");
		Polynom p1=new Polynom("5x");
		Polynom p2=new Polynom("3x^2");
		p0.substract(p1);
		assertEquals(p0.toString(),p2.toString());
	}
	@Test
	public void testMultiply(){
		Polynom p0=new Polynom("3x^2 + 5x");
		Polynom p1=new Polynom("5x");
		Polynom p2=new Polynom("15x^3+25x^2");
		p0.multiply(p1);
		assertEquals(p0.toString(),p2.toString());
	}
	@Test
	public void testEquals1() {
		boolean flag;
		Polynom p0=new Polynom("5x");
		Polynom p1=new Polynom("5x");
		flag=p0.equals(p1);
		assertTrue(flag);
		
	}
	@Test
	public void testEquals2() {
		boolean flag;
		Polynom p0=new Polynom("5x");
		Polynom p1=new Polynom("6x");
		flag=p0.equals(p1);
		assertFalse(flag);
		
	}
	@Test
	public void testIsZero1() {
		boolean flag;
		Polynom p0=new Polynom("5x");
		flag = p0.isZero();
		assertFalse(flag);
	}
	@Test
	public void testIsZero2() {
		boolean flag;
		Polynom p0=new Polynom("0");
		flag = p0.isZero();
		assertTrue(flag);
	}
	@Test
	public void testRoot1() {
		boolean flag;
		Polynom p= new Polynom("x^2-5x+6");
		double root=p.root(1, 3, 0.000001);
		flag = (root > (1.995) && root<2.005);
		assertTrue(flag);
	}
	@Test
	public void testRoot2() {
		boolean flag;
		Polynom p= new Polynom("x^2-5x+6");
		double root=p.root(1, 3, 0.000001);
		flag = (root == (5));
		assertFalse(flag);
	}
	@Test
	public void testCopy() {
		Polynom p1= new Polynom("x^2-5x+6");
		Polynom p2= (Polynom)p1.copy();
		assertEquals(p1.toString(),p2.toString());
		
	}
	@Test
	public void testDerivative() {
		Polynom p1= new Polynom("x^2-5x+6");
		Polynom p2= new Polynom("2x-5");
		assertEquals(p2.toString(),p1.derivative().toString());
	}
	@Test
	public void testArea() {
		Polynom p1= new Polynom("3x");
		double area=p1.area(0, 3, 0.0001);
		boolean	flag = (area>13 && area<14);
		assertTrue(flag);
	}
	@Test
	public void testSort() {
		Polynom p1=new Polynom("3x+2x^5");
		p1.sort();
		assertEquals(p1.toString(),"2.0x^5 + 3.0x");
		
	}
	@Test 
	public void testIterator() {
		Polynom p1=new Polynom ();
		Monom m1= new Monom(2,3);
		p1.add(m1);
		Iterator<Monom> i=p1.iteretor();
		if ( i.hasNext() )		{
			
			assertEquals(i.next(),m1);
		}
		
	}
}

