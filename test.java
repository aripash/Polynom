package myMath;

public class test {
	// error: regix, subtract
	public static void main(String[] args) {
		Monom m=new Monom("5*x^3");
		System.out.println(m);
		Monom m2=new Monom(2,3);
		System.out.println(m2);
		System.out.println(m2.get_coefficient()+":coefficient+power:"+m2.get_power());
		System.out.println("f(5)="+m2.f(5));
		m2.derivative();
		System.out.println("derivative "+m2);
		m2.add(new Monom(m2));
		System.out.println("after adding "+m2);
		m2.mulpitply(new Monom(m2));
		System.out.println("after multiplying "+m2);
		Polynom p=new Polynom();
		p.add(new Monom(3,5));
		p.add(new Monom(1,1));
		p.add(new Monom(1,1));
		p.add(new Monom(2,1));
		p.add(new Monom(1,2));
		System.out.println(p);
		Polynom p2=new Polynom(" (0), [x], {0x^2,2*x}, 2x,2*x^2, 2x^2,-x,-2x,2X^3,-3*x^2") ;
		System.out.println(p2);
		Polynom p4=new Polynom(" 1+ x+ x^2+2*x+ 2x+2*x^2+ 2x^2-x-2x+2X^3-3*x^2");
		System.out.println(p4);
		Polynom p3=new Polynom(p);
		System.out.println("after copying "+p3);
		System.out.println();
		System.out.println(p3);
		System.out.println(p);
		p3.add(p);
		System.out.println(p3+" after add");
		System.out.println();
		System.out.println(p);
		System.out.println(p3);
		p3.substract(p);
		System.out.println(p3+" after substraction");
		System.out.println("f(5)="+p.f(5));
		System.out.println();
		System.out.println(p3);
		System.out.println(p);
		p3.multiply(p);
		System.out.println(p3+" after multiply");
		System.out.println("equals? "+p.equals(p3));
		System.out.println("root "+p.root(-1, 2, 0.01));
		System.out.println("root "+p.root(-1, 3, 0.0001));
		System.out.println(p3.derivative()+" after derivative");
		System.out.println(p+" after derivative");
		System.out.println("area "+p.area(-1, 1, 0.00001));				
	}

}
