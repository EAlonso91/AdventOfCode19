package day4.securecontainer;

import java.util.Arrays;

/**
 * @author Enrique Alonso
 */
public class SecureContainerSimpleAdjacency extends SecureContainer{

	public boolean checkAdjacency(final int candidate) {
		int[] digits = String.valueOf(candidate).chars().map(c->c-'0').toArray();
		for (int j = 0; j < digits.length-1; j++) {
			if(digits[j]==digits[j+1]){
				return true;
			}
		}
		return false;
	}
}
