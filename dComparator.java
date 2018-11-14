package myMath;

import java.util.Comparator;

public class dComparator implements Comparator<Double>{
	@Override
	public int compare(Double arg0, Double arg1) {
		// TODO Auto-generated method stub
		if(arg0>arg1)return -1;
		else if(arg1>arg0)return 1;
		return 0;
	}
}