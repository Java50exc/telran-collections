package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

abstract class CollectionTests {
	Integer[] numbers = { 10, -20, 8, 14, 30, 12, 100 };
	protected Collection<Integer> collection;

	@BeforeEach
	void setUp() {

		for (var n : numbers) {
			collection.add(n);
		}

	}

	@Test
	abstract void add_normalFlow_addsObject();

	@Test
	void remove_normalFlow_removesFirstObject() {
		Integer[] expected = Arrays.copyOfRange(numbers, 1, numbers.length);
		
		assertTrue(collection.remove(numbers[0]));
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
	}

	@Test
	void remove_normalFlow_removesLastObject() {
		Integer[] expected = Arrays.copyOfRange(numbers, 0, numbers.length - 1);
		
		assertTrue(collection.remove(numbers[numbers.length - 1]));
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
	}

	@Test
	void remove_normalFlow_removesMiddleObject() {
		int index = numbers.length / 2;
		Integer[] expected = new Integer[numbers.length - 1];
		System.arraycopy(numbers, 0, expected, 0, index);
		System.arraycopy(numbers, index + 1, expected, index, numbers.length - index - 1);
		
		assertTrue(collection.remove(numbers[index]));
		assertArrayEquals(expected, collection.toArray(new Integer[0]));

	}

	@Test
	void remove_objectNotExists_notRemovedObject() {
		Integer numNotExists = 0;
		while (getFromArray(numNotExists)) {
			numNotExists = (int)(Math.random() * 100);
		}
		
		assertFalse(collection.remove(numNotExists));
		assertArrayEquals(numbers, collection.toArray(new Integer[0]));
	}


	@Test
	void remove_argumentDifferentType_throwsClassCastException() {
		assertThrowsExactly(ClassCastException.class, () -> collection.remove("123"));
	}

	
	@Test
	void toArray_argumentNull_throwsNullPointerException() {
		assertThrowsExactly(NullPointerException.class, () -> collection.toArray(null));
	}


	@Test
	void toArray_argumentArrayLongerThanCollection_returnsArray() {
		assertArrayEquals(numbers, collection.toArray(new Integer[numbers.length + 100]));
	}

	@Test
	void toArray_argumentArrayShorterThanCollection_returnsArray() {
		assertArrayEquals(numbers, collection.toArray(new Integer[0]));
	}

	@Test
	void removeIf_argumentNull_throwsNullPointerException() {
		assertThrowsExactly(NullPointerException.class, () -> collection.removeIf(null));
	}

	@Test
	void removeIf_normalFlow_notRemoved() {
		assertFalse(collection.removeIf(e -> false));
		assertArrayEquals(numbers, collection.toArray(new Integer[0]));
	}

	
	@Test
	void removeIf_normalFlow_removedSome() {
		Integer maxInd = getMaxIndex();
		Integer[] expected = new Integer[numbers.length - 1];
		System.arraycopy(numbers, 0, expected, 0, maxInd);
		System.arraycopy(numbers, maxInd + 1, expected, maxInd, expected.length - maxInd);
		
		assertTrue(collection.removeIf(e -> e % numbers[maxInd] == 0));
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
	}

	@Test
	void removeIf_normalFlow_removedAll() {
		assertTrue(collection.removeIf(e -> true));
		assertArrayEquals(new Integer[0], collection.toArray(new Integer[0]));	
	}

	@Test
	void size_normalFlow_emptyCollection() {
		collection.removeIf(e -> true);
		
		assertEquals(0, collection.size());
	}

//	@Test
//	void size_normalFlow_filledCollection() {
//		
//	}
//
//	@Test
//	abstract void size_normalFlow_reallocatedCollection();

	@Test
	void size_normalFlow_afterRemoving() {
		collection.remove(numbers[0]);
		
		assertEquals(numbers.length - 1, collection.size());
	}

	@Test
	void addAll_argumentNull_throwsNullPointerException() {
		assertThrowsExactly(NullPointerException.class, () -> collection.addAll(null));
	}

	@Test
	void addAll_normalFlow_notAdded() {
	}

	@Test
	abstract void addAll_normalFlow_added();

	@Test
	abstract void removeAll_argumentNull_throwsNullPointerException();

	@Test
	abstract void removeAll_normalFlow_notRemoved();

	@Test
	abstract void removeAll_normalFlow_removedSome();

	@Test
	abstract void removeAll_normalFlow_removedAll();

//	boolean add(T obj);
//	boolean remove(Object pattern);
//	T[] toArray(T[] array);
//	boolean removeIf(Predicate<T> predicate);
//	int size();
//	boolean addAll(Collection<T> collection);
//	boolean removeAll(Collection<T> collection);
	
	private boolean getFromArray(int number) {
		for(var n : numbers) {
			if (n == number)
				return true;
		}
		return false;
	}
	
	private Integer getMaxIndex() {
		Integer maxInd = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			maxInd = numbers[i] > numbers[maxInd] ? i : maxInd;
		}
		return maxInd;
	}

}
