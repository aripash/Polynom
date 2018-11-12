
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	/**
	 * full constructor 
	 * @param a the coefficient
	 * @param b	the power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * copy constructor
	 * @param ot the monom that this is copying
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	// ***************** add your code below **********************
	/**
	 * a constructor that transforms string into a monom
	 * @param s the string of the monom
	 */
	public Monom(String s) {
		s=s.toLowerCase();
		double a;
		int b;
		if(s.contains("x")) {
			if(s.indexOf('x')!=0) {
				if(s.indexOf('x')==1&&s.charAt(0)=='-'){
					a=-1;
				}
				else if(s.contains("*")) {
					a=Double.parseDouble(s.substring(0, s.indexOf('*')));
				}
				else {
					a=Double.parseDouble(s.substring(0, s.indexOf('x')));
				}
			}
			else {
				a=1;
			}

			if(s.contains("^")) {
				if(s.charAt(s.indexOf('^'))+1=='-') throw new RuntimeException("No negative integer"); 
				else b=Integer.parseInt(s.substring(s.indexOf("^")+1, s.length()));
			}
			else {
				b=1;
			}
		}
		else {
			a=Double.parseDouble(s);
			b=0;
		}

		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * this is a get function for coefficient
	 * @return	the coefficient of the monom
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 *  this is a get function for power
	 * @return	the power of the monom
	 */
	public int get_power() {
		return this._power;
	}
	/**
	 * this function implements the f(x)=y function
	 * @param x replaces the x in monom with a double
	 * @return double that represents the y of the monom
	 */
	public double f(double x) {
		x=Math.pow(x, this.get_power());
		return x*this.get_coefficient();
	} 
	/**
	 * this is the toString of the monom
	 * @return string in the form of a*x^b
	 */
	public String toString() {
		String ans="";
		if 		(this.isZero()) ans=ans+0;
		else 	if(this.get_power()==1) ans=this.get_coefficient()+"x";
		else 	ans=this.get_coefficient()+"x^"+this.get_power();
		return ans;
	}
	/**
	 * this function implements the f'(x) function
	 */
	public void derivative() {
		this.set_coefficient(this.get_coefficient()*this.get_power());
		this.set_power(this.get_power()-1);
	}
	/**
	 * this function adds m's coefficient to this's if their power is the same
	 * @param m the monom that you add to this
	 */
	public void add(Monom m) {
		if(m.get_power()==this.get_power())
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());
	}
	/**
	 * this function multiplies this monom with m
	 * @param m the monom that you multiply this to
	 */
	public void mulpitply(Monom m) {
		this.set_coefficient(this.get_coefficient()*m.get_coefficient());
		this.set_power(this.get_power()+m.get_power());
	}
	/**
	 * checks if the monom has 0 in the coefficient
	 * @return true if the monom is zero false else wise
	 */
	public boolean isZero() {
		return	(this.get_coefficient()==0) ;
	}
	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power;
}
