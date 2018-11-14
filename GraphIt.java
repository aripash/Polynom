package myMath;

import java.util.*;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
public class GraphIt {
	private Polynom pol;
	public GraphIt(String s) {
		pol=new Polynom(s);
	}
	public double[] extremum() {
		Iterator<Monom> im=pol.iteretor();
		int size=im.next().get_power()-1;
		double []a=new double[size];
		Polynom temp=(Polynom)pol.derivative();
		int i=0;
		double eps=0.00001;
		double from=0;
		double to=10;
		double x=from;
		while(i<size&&Math.abs(x)<to) {
			for(;x<=to;x+=eps) {
				if(Math.abs(temp.f(x))<=eps) {
					a[i]=x;
					i++;
					x++;
				}
			}
			for( x=(from*-1);x<=(to*-1);x+=eps) {
				if(Math.abs(temp.f(x))<=eps) {
					a[i]=x;
					i++;
					x++;
				}
			}
			from+=10;
			to+=10;
		}
		this.sort(a);
		return a;
	}
	public void graph() {
		double [] extremum=this.extremum();
		ArrayList <Double> x=new ArrayList<Double>();
		for(int i=0;i<extremum.length;i++) {
			x.add(extremum[i]);
		}
		for(double i=extremum[0]-3;i<extremum[extremum.length-1]+3;i+=0.01) {
			x.add(i);
		}
		x.sort(new dComparator());
		double [] xData=new double[x.size()];
		Iterator<Double> inte=x.iterator();
		for(int i=0;i<x.size();i++) {
			xData[i]=inte.next();
		}
		double [] yData=new double[xData.length];
		for(int i=0;i<yData.length;i++) {
			yData[i]=pol.f(xData[i]);
		}
		XYChart chart = QuickChart.getChart("Polynom", "X", "Y", "y(x)", xData, yData);
		new SwingWrapper(chart).displayChart();
	}
	public void sort(double []a) {
		for(int i=0;i<a.length-1;i++) {
			double min=a[i];
			for(int j=i+1;j<a.length-1;j++) {
				if(a[j]<min) {
					double temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
	}
}
