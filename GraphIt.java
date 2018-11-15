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
	public double[] extremum(double from,double to) {
		Iterator<Monom> im=pol.iteretor();
		int size=im.next().get_power()-1;
		double []a=new double[size];
		Polynom temp=(Polynom)pol.derivative();
		int i=0;
		double eps=0.001;
		Boolean flag=true;
		while(flag) {
			try {
				a[i]=temp.root(from, to, eps);
				from=a[i]+eps*10;
				i++;
			}
			catch(Exception e) {
				flag=false;
			}
		}
		if(a.length!=i) {
			double[]k=new double[i];
			for(int j=0;j<i;j++) {
				k[j]=a[j];
			}
			return k;
		}
		return a;
	}
	public void graph(double a,double b) {
		double [] extremum=this.extremum(a,b);
		ArrayList <Double> x=new ArrayList<Double>();
		for(int i=0;i<extremum.length;i++) {
			x.add(extremum[i]);
		}
		for(double i=a;i<b;i+=0.1) {
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
		for(int i=0;i<extremum.length;i++) {
			double [] xpoint= {extremum[i]};
			double calc=pol.f(extremum[i]);
			double [] ypoint= {calc};
			chart.addSeries("("+((int)(extremum[i]*100))/100.0+","+((int)(calc*100))/100.0+")", xpoint, ypoint);
		}
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
