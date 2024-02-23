package telran.util;

import java.util.Arrays;

public class Temp {
	
	public static void main(String[] args) {
		Integer[] numbers = {10, -20, 8, 14, 30, 12, 100};
		int index = numbers.length / 2;
		Integer[] expected = new Integer[numbers.length - 1];
		System.arraycopy(numbers, 0, expected, 0, index);
		System.arraycopy(numbers, index + 1, expected, index, numbers.length - index - 1);
		System.out.println(Arrays.toString(expected));
		
	}

}
