package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	// ********** add your code below ***********
	private ArrayList<Monom> polyList=new ArrayList<Monom>();
	/**
	 * empty constructor
	 */
	public Polynom() {}
	/**
	 * copy constructor
	 * @param p1 the polynom that this is copying
	 */
	public Polynom(Polynom p1) {
		this.polyList=((Polynom)(p1.copy())).polyList;
	}
	/**
	 * constructor that transforms string into a polynom
	 * @param s the string that turns into polynom
	 */
	public Polynom(String s) {
		s=s.toLowerCase();
		s=s.replaceAll(" ", "");
		s=s.replaceAll("[,]", "+");
		s=s.replaceAll("[-]", "+-");
		s=s.replaceAll("[+]"+"[+]", "+");
		s=s.replaceAll("[\\[\\](){}]", "");
		if(s.charAt(0)=='+')s=s.substring(1, s.length());
		String [] str=s.split("[+]");
		for(int i=0;i<str.length;i++) {
			this.add(new Monom(str[i]));
		}
	}
	/**
	 * adds a new monom into the polynom
	 * @param m1 the new monom thats being add to the polynom
	 */
	public void add(Monom m1) {
		int pow=m1.get_power();
		if(pow>=0&&m1.get_coefficient()!=0) {
			Iterator<Monom> i=this.iteretor();
			boolean flag=false;
			while(i.hasNext()&&!flag) {
				Monom mon=i.next();
				if(mon.get_power()==pow) {
					mon.add(m1);
					flag=true;
				}
			}
			if(!flag) {
				polyList.add(m1);
				this.sort();
			}
			this.sort();
		}	
	}
	/**
	 * adds a class that implaments polynom_able to the polynom
	 * @param p1 the added class
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> i=p1.iteretor();
		while(i.hasNext()) {
			Monom mon=i.next();
			this.add(mon);
		}
	}
	/**
	 * function  that implements the f(x)=y function on the polynom
	 * @param x replaces x in the polynom with a number 
	 * @return calculates the y
	 */
	public double f(double x) {
		Iterator<Monom> i=this.iteretor();
		double ans=0;
		while(i.hasNext()) {
			ans=i.next().f(x);
		}
		return ans;
	}
	/**
	 * Subtracts p1 from this
	 * @param p1 a class that implements polynom_able
	 */
	public void substract(Polynom_able p1) {
		Polynom pol=new Polynom((Polynom)p1);
		pol.multiply(new Polynom("-1"));
		this.add(pol);
	}
	/**
	 * multiplies this with p1
	 * @param p1 a class that implements polynom_able
	 */
	public void multiply(Polynom_able p1) {
		if(!this.isZero()) {
			Iterator<Monom> i1;
			Iterator<Monom> i2=p1.iteretor();
			Polynom temp=new Polynom();
			while(i2.hasNext()){
				i1=this.iteretor();
				Monom mon2=new Monom(i2.next());
				while(i1.hasNext()){
					Monom mon1=new Monom(i1.next());
					mon1.mulpitply(mon2);
					temp.add(mon1);
				}
			}
			this.polyList=temp.polyList;
		}
	}
	/**
	 * checks if this is the same polynom as p1
	 * @param p1 a class that implements polynom_able
	 * @return if they are the same true else false
	 */
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> i1=this.iteretor();
		Iterator<Monom> i2=p1.iteretor();
		Boolean flag=true;
		Monom_Comperator comp=new Monom_Comperator();
		while((i1.hasNext()&&i2.hasNext())&&(flag)) {
			if(comp.compare(i1.next(),i2.next())!=0)flag=false;
		}
		return flag;
	}
	/**
	 * checks if the polynom is empty
	 * @return if the polynom is empty true else flase
	 */
	public boolean isZero() {
		return polyList.isEmpty();
	}
	/**
	 * searches for a number that turns the polynom to zero
	 * @param x0 the start of the search
	 * @param x1 the end of the search
	 * @param eps the length of the step
	 * @return the number that turns the polynom to zero
	 */
	public double root(double x0, double x1, double eps) {
		if(eps!=0) {
		if(eps<0) eps=Math.abs(eps);
		if(x0<=x1) {
			for(double x=x0;x<x1;x+=eps) {
				if(Math.abs(this.f(x))<=eps)return x;
			}
			throw new RuntimeException("No root");
		}
		return this.root(x1, x0, eps);
	}
		throw new RuntimeException("eps is zero");
	}
	/**
	 * copying polynom this
	 * @return new polynom that is the exact copy of the performing polynom
	 */
	public Polynom_able copy() {
		Iterator<Monom> i=this.iteretor();
		Polynom temp=new Polynom();
		while(i.hasNext()) {
			Monom mon=new Monom(i.next());
			temp.add(mon);
		}
		return temp;

	}
	/**
	 * implements the f'(x) function on the polynom
	 * @return polynom after the derivative action
	 */
	public Polynom_able derivative() {
		Iterator<Monom> i=this.iteretor();
		Polynom pol=new Polynom();
		while(i.hasNext()) {
			Monom m=new Monom(i.next());
			m.derivative();
			pol.add(m);
		}
		return pol;
	}
	/**
	 * using the Riemann's Integral to search for the area between the polynom the x axis x0 and x1
	 * @param x0 the start of the area
	 * @param x1 the end of the area
	 * @param eps the length of the rectangle 
	 * @return the desired area
	 */
	public double area(double x0, double x1, double eps) {
		if(eps!=0) {
		if(eps<0) eps=Math.abs(eps);
		if(x0<=x1) {
			double area=0;
			for(double x=x0;x<(x1-eps);x+=eps) {
				double y=this.f(x);
				if(y>0) {
					area+=(eps*y);
				}
			}
			return area;
		}
		else return this.area(x1, x0, eps);
	}
	throw new RuntimeException("eps is zero");
	}
	/**
	 * creates iterator for the list of polynoms
	 * @return iterator type monom for the list in polynom
	 */
	public Iterator<Monom> iteretor() {
		return polyList.listIterator();
	}
	/**
	 * toString function of polynom
	 * @return string that represents the polynom
	 */
	public String toString() {
		this.sort();
		String s="";
		if (this.getArrayList().isEmpty()) 
			s=s+"0";
		else {
			Iterator<Monom> it=this.iteretor();
			while (it.hasNext())
			{
				Monom m=new Monom (it.next());
				s=s+m.toString()+" + ";
			}
			s=s.substring(0, s.length()-3);
		}
		return s;
	}
	/**
	 * get function for the list of polynom
	 * @return arraylist of monoms 
	 */
	public ArrayList<Monom> getArrayList() {
		return this.polyList;
	}
	/**
	 * sorts the list of monoms in polynom
	 */
	public void sort() {
		polyList.sort(new Monom_Comperator());
		Iterator<Monom> it=this.iteretor();
		if (!this.isZero()) {
			while (it.hasNext()) {
				Monom m=new Monom(it.next());
				if(m.isZero()) it.remove();
			}
		}
	}

}
