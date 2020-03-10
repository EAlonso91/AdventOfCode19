package day4.securecontainer;

/**
 * @author Enrique Alonso
 */
public abstract class SecureContainer {

	public int passwordsFinder(String range){
		String[] ranges = range.split("-");
		if(ranges.length!=2){
			throw new RuntimeException("Invalid range input");
		}
		int lowerLimit = Integer.parseInt(ranges[0]);
		int upperLimit = Integer.parseInt(ranges[1]);
		int passwordsCounter = 0;
		for (int i = lowerLimit; i <= upperLimit; i++) {
			boolean adjacentsEquals = checkAdjacency(i);
			boolean digitsIncrease = checkIncrease(i);
			if(adjacentsEquals && digitsIncrease){
				passwordsCounter++;
			}
		}
		return passwordsCounter;
	}
	public boolean checkIncrease(final int candidate) {
		int[] digits = String.valueOf(candidate).chars().map(c->c-'0').toArray();
		for (int j = 0; j < digits.length-1; j++) {
			if(digits[j]>digits[j+1]){
				return false;
			}
		}
		return true;
	}

	public abstract boolean checkAdjacency(final int candidate);
}
