package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.List;

abstract class ListTests extends CollectionTests {
	List<Integer> list;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>) collection;
		
	}

	@Override
	void add_normalFlow_addsObject() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length + 1);
		expected[expected.length - 1] = expected[0];
		
		assertTrue(list.add(numbers[0]));
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	@Test
	void add_normalFlow_addsObjectAtIndex() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length + 1);
		System.out.println(expected.length);
		expected[expected.length - 1] = expected[0];
		System.out.println(expected.length);
		
		list.add(list.size(), numbers[0]);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	@Test
	void add_incorrectIndex_throwsIndexOutOfBoundsException() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(numbers[0], list.size() + 1));
		
	}
	
	@Test
	void get_incorrectIndex_throwsIndexOutOfBoundsException() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(list.size() + 1));	
	}
	
	@Test
	void get_normalFlow_returnsObject() {
		assertEquals(numbers[0], list.get(0));
		
	}
	
	@Test
	void set_normalFlow_returnsObject() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length);
		expected[0] = 0;
		
		list.set(0, 0);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		
	}
	
	@Test
	void set_incorrectIndex_throwsIndexOutOfBoundsException() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(list.size() + 1, 0));	
		
	}
	
	@Test
	void remove_normalFlow_removesObject() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length - 1);
		list.remove(list.size() - 1);
		
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	@Test
	void remove_incorrectIndex_throwsIndexOutOfBoundsException() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(list.size() + 1));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(-1));
	}
	
	
	@Test
	void indexOf_correctFlow_returnsIndex() {
		assertEquals(0, list.indexOf(numbers[0]));
		
	}
	
	@Test
	void indexOf_notFound_returnsIndex() {
		final Integer numNotExists = getNumberNotExists();
		
		assertEquals(-1, list.indexOf(numNotExists));
		
	}
	
	
	@Test
	void lastIndexOf_correctFlow_returnsIndex() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length);
		expected[expected.length - 1] = expected[0];
		list.set(list.size() - 1, expected[0]);
		
		assertEquals(expected.length - 1, list.lastIndexOf(expected[expected.length - 1]));
	}
	
	@Test
	void lastIndexOf_notFound_returnsIndex() {
		final Integer numNotExists = getNumberNotExists();
		
		assertEquals(-1, list.lastIndexOf(numNotExists));
		
	}
	
	@Test
	void indexOfPredicate_argumentNull_throwsNullPointerException() {
		Predicate<Integer> predicate = null;
		assertThrowsExactly(NullPointerException.class, () -> list.indexOf(predicate));

		
	}
	
	@Test
	void indexOfPredicate_correctFlow_returnsIndex() {
		int maxInd = getMaxIndex();
		
		assertEquals(maxInd, list.indexOf(e -> e % numbers[maxInd] == 0));
		
	}
	
	@Test
	void indexOfPredicate_notFound_returnsIndex() {
		final Integer numNotExists = getNumberNotExists();
		
		assertEquals(-1, list.indexOf(e -> e == numNotExists));
		
	}
	
	@Test
	void lastIndexOfPredicate_argumentNull_throwsNullPointerException() {
		Predicate<Integer> predicate = null;
		assertThrowsExactly(NullPointerException.class, () -> list.lastIndexOf(predicate));
		
	}
	
	@Test
	void lastIndexOfPredicate_correctFlow_returnsIndex() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length);
		expected[expected.length - 1] = expected[0];
		list.set(list.size() - 1, expected[0]);
		
		assertEquals(expected.length - 1, list.lastIndexOf(e -> e == expected[expected.length - 1]));
		
	}
	
	@Test
	void lastIndexOfPredicate_notFound_returnsIndex() {
		final Integer numNotExists = getNumberNotExists();
		
		assertEquals(-1, list.lastIndexOf(e -> e == numNotExists));
		
	}
	


}
