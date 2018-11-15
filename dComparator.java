package myMath;
/**
 * this class is created to help compare and sort doubles by comparing 2 doubles
 */
import java.util.Comparator;
/**
 * this is the function that compares two doubles
 * @return positive number if arg1 is bigger, negetive if arg1 is smaller and 0 if both args are the same.
 *
 */
public class dComparator implements Comparator<Double>{
	@Override
	public int compare(Double arg0, Double arg1) {
		// TODO Auto-generated method stub
		if(arg0>arg1)return -1;
		else if(arg1>arg0)return 1;
		return 0;
	}
}