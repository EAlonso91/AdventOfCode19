package day4.securecontainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enrique Alonso
 */
public class SecureContainerComplexAdjacency extends SecureContainer{
	@Override
	public boolean checkAdjacency(final int candidate) {
		int[] digits = String.valueOf(candidate).chars().map(c->c-'0').toArray();
		Map<Integer, Integer> digitCounter = new HashMap<>();
		for (int j = 0; j < digits.length; j++) {
			if(digitCounter.containsKey(digits[j])){
				int updatedCount = digitCounter.get(digits[j])+1;
				digitCounter.put(digits[j], updatedCount);
			}
			else{
				digitCounter.put(digits[j], 1);
			}
		}
		return digitCounter.containsValue(2);
	}
}
