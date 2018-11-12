package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MonomTest {

	@Test 
	public void testConstructor1() {
		Monom m=new Monom(2,4);
		assertEquals(m.toString(), 2.0 + "x^" + 4);
	}
	@Test
	public void testConstructor2() {
		Monom m1=new Monom(2,4);
		Monom m2=new Monom(m1);
		assertEquals(m2.toString(), m1.toString());
	}
	@Test
	public void testConstructor3() {
		Monom m=new Monom("2x^4");
		assertEquals("2.0x^4", m.toString());
	}
	@Test
	public void testgetc() {
		Monom m=new Monom(2,4);
		assertEquals(2.0, m.get_coefficient());
	}
	@Test
	public void testgetp() {
		Monom m=new Monom(2,4);
		assertEquals(4, m.get_power());
	}
	@Test
	public void testf() {
		Monom m=new Monom(2,4);
		assertEquals(32.0, m.f(2));
	}
	@Test
	public void testToString() {
		Monom m=new Monom(2,4);
		assertEquals("2.0x^4",m.toString());
	}
	@Test
	public void testDerivitive() {
		Monom m=new Monom(2,4);
		m.derivative();
		assertEquals("8.0x^3",m.toString());
	}
	@Test
	public void testAdd() {
		Monom m=new Monom(2,4);
		m.add(m);
		assertEquals("4.0x^4", m.toString());
	}
	@Test
	public void testMultiply() {
		Monom m=new Monom(2,4);
		m.mulpitply(m);
		assertEquals("4.0x^8", m.toString());
	}
	@Test
	public void testZero1() {
		Monom m=new Monom(2,4);
		assertFalse(m.isZero());
	}
	@Test
	public void testZero2() {
		Monom m=new Monom(0,0);
		assertTrue(m.isZero());
	}
	@Test
	public void testComperator1() {
		Monom m1=new Monom(2,4);
		Monom m2=new Monom(3,4);
		Monom_Comperator comp=new Monom_Comperator();
		assertEquals(-1, comp.compare(m1, m2));
	}
	@Test
	public void testComperator2() {
		Monom m1=new Monom(2,4);
		Monom m2=new Monom(1,4);
		Monom_Comperator comp=new Monom_Comperator();
		assertEquals(1, comp.compare(m1, m2));
	}
	@Test
	public void testComperator3() {
		Monom m1=new Monom(2,4);
		Monom m2=new Monom(2,4);
		Monom_Comperator comp=new Monom_Comperator();
		assertEquals(0, comp.compare(m1, m2));
	}
}
