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
		int size=im.next().get_power();
		double []a=new double[size+1];
		Polynom temp=(Polynom)pol.derivative();
		int i=1;
		double eps=0.0001;
		double from=0;
		double to=10;
		double x=from;
		while(i<size&&Math.abs(x)<to) {
			for(;x<=to;x+=eps) {
				if(Math.abs(temp.f(x))<=eps) {
					a[i]=x;
					i++;
					x=x+(eps*2);
				}
			}
			for( x=(from*-1);x<=(to*-1);x+=eps) {
				if(Math.abs(temp.f(x))<=eps) {
					a[i]=x;
					i++;
					x=x+(eps*2);
				}
			}
			from+=10;
			to+=10;
		}
		this.sort(a);
		return a;
	}
	public void graph() {
		double [] xData=this.extremum();
		xData[0]=xData[1]-5;
		xData[xData.length-1]=xData[xData.length-2]+5;
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
