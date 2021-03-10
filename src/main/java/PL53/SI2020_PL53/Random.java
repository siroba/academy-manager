package PL53.SI2020_PL53;

public class Random extends java.util.Random{
	// Serial ID assigned by eclipse
	private static final long serialVersionUID = 2663648093962058538L;
	
	/**
	 * Generates a random name with the specified length constraints.
	 * 
	 * @param minLength
	 * @param maxLength
	 * @return name
	 */
	public String name(int minLength, int maxLength) {
		String name = "";
		char vocals[] = new char[] { 'a', 'e', 'i', 'o', 'u' };

		char prev = 0;

		for (int i = 0; i < this.nextInt(maxLength - minLength) + minLength; i++) {
			int letter;

			if (prev != 0 && !isVocal(prev))
				if (this.nextFloat() < 0.2)
					letter = this.nextInt(25) + 97;
				else
					letter = vocals[this.nextInt(5)];
			else
				letter = this.nextInt(25) + 97;

			if (i == 0)
				letter -= 32;

			prev = (char) letter;

			name += (char) letter;
		}

		return name;
	}
	
	/**
	 * Generates a random phone number (starting with 6 or 7, Spanish prefixes)
	 * 
	 * @return
	 */
	public String phone() {
		String phone = Math.random()>0.5?"6":"7";
		
		for(int i = 0; i<4; i++) {
			int n = this.nextInt(99);
			
			if(n<0) phone += "0";
			
			phone += n + " ";
		}
		
		return phone;
	}

	/**
	 * This is a lazy implementation to check if a character is a vocal.
	 * 
	 * @param character
	 * @return
	 */
	private static boolean isVocal(char c) {
		char vocals[] = new char[] { 'a', 'e', 'i', 'o', 'u' };

		for (char v : vocals)
			if (c == v)
				return true;

		return false;
	}
}
