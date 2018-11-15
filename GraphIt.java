package myMath;
/**
 * this class represents graphs that graph polynoms.
 * used resources from XChart by knowm.
 * see: https://github.com/knowm/XChart
 * this class implements creating new graphs, finding extremes and drawing charts.
 * @author 	Dennis Shapira,
 * 			Mark Michaely
 * 
 */
import java.util.*;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
public class GraphIt {
	/**
	 * this function is a simple builder that recieves a string and creates a polynom from it.
	 * @param s is a string that is to be created to be a polynom
	 */
	public GraphIt(String s) {
		this.setPol(new Polynom(s));
	}
	/**
	 * this function is a simple builder that recieves a string and creates a polynom from it.
	 * @param p is a polynom that that is to be graphed
	 */
	public GraphIt(Polynom p) {
		this.setPol(p);
	}
	/**
	 * this function is used to find the extremes of polynom function
	 * @param from is the parameter 
	 * @param to
	 * @return
	 */
	
	/**
	 * function is used to get an array of doubles which represents extremes in the graph
	 * @param from is the start of the search
	 * @param to is the end of the search
	 * @return an array of double that represents extremes in the graph
	 */
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
	/**
	 * this function draws the graph
	 * @param from represents where to start showing the graph
	 * @param to represents where to end showing the graph
	 */
	public void graph(double from,double to) {
		double [] extremum=this.extremum(from,to);
		ArrayList <Double> x=new ArrayList<Double>();
		for(int i=0;i<extremum.length;i++) {
			x.add(extremum[i]);
		}
		for(double i=from;i<to;i+=0.1) {
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
	/**
	 * this function is used to sort an array of doubles.
	 * @param a is an array of double which is to be sorted
	 */
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
	/**
	 * this function receives two integers representing starting point and end point of desired area. 
	 * functions returns a double that represents the area between two points
	 * @param from is the starting point of desired area
	 * @param to is the end point of desired area
	 * @return area between point from to point to
	 */
	public double area(int from,int to) {
		double eps=0.01;
		double ans=0;
		for(double i=from;i<to;i+=eps) {
			double calc=this.getPol().f(i);
			if(calc<0)ans+=calc;
		}
		return ans;
	}
	/**
	 * functions is used to get the polynom
	 * @return a polynom that is used in the graph
	 */
	public Polynom getPol() {
		return this.pol;
	}
	
	////////////////////////////private///////////////////////////
	private Polynom pol;
	private void setPol(Polynom p) {
		pol =new Polynom(p);
	}
}
