package myMath;

import java.util.Comparator;
/**
 * this class is created to help compare and sort polynom by comparing 2 monoms
 *
 */
public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	
	/**
	 * this is the function that compares two monoms
	 * @return positive number if args0 is bigger negetive if smaller and 0 if they are the same
	 */
public int compare(Monom arg0, Monom arg1) {
		int p= -(arg0.get_power()-arg1.get_power());
		if(p==0) {
			if(arg0.get_coefficient()-arg1.get_coefficient()>0)return 1;
			if(arg0.get_coefficient()-arg1.get_coefficient()<0)return -1;
			return 0;
		}
		return p;
	}
}
