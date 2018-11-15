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
		double eps=0.001;
		double from=0;
		double to=10;
		while(i<size) {
			double x=from;
			double x2=from;
			while(x<=to) {
				try {
					a[i]=pol.root(x, x+1, eps);
					x+=a[i]+eps;
					i++;
				}
				catch(Exception e) {
					x++;
				}
				try {
					a[i]=pol.root((x2*-1), ((x2+1)*-1), eps);
					x2+=Math.abs(a[i])+eps;
					i++;
				}
				catch(Exception e2) {
					x2++;
				}
			}
			from=to;
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
		for(double i=extremum[0]-10;i<extremum[extremum.length-1]+10;i+=0.1) {
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
		XYChart chart = QuickChart.getChart("Polynom", "X", "Y", "Polynom \n"+pol.toString(), xData, yData);
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
	public double area(int a,int b) {
		double eps=0.01;
		double ans=0;
		for(double i=a;i<b;i+=eps) {
			double calc=pol.f(i);
			if(calc<0)ans+=calc;
		}
		return ans;
	}
}
