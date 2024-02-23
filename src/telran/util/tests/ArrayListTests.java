package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTests extends ListTests {
	
	@BeforeEach
	@Override
	void setUp() {
		collection = new ArrayList<>();
		super.setUp();
	}



	@Override
	void addAll_normalFlow_added() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length * 2);
		System.arraycopy(numbers, 0, expected, numbers.length, numbers.length);
		
		ArrayList<Integer> addList = new ArrayList();
		
		for (var num: numbers) {
			addList.add(num);
		}

		collection.addAll(addList);
		
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
	}

	@Override
	void removeAll_normalFlow_notRemoved() {
		ArrayList<Integer> removeList = new ArrayList();
		
		collection.removeAll(removeList);
		assertArrayEquals(numbers, collection.toArray(new Integer[0]));
	}

	@Override
	void removeAll_normalFlow_removedSome() {
		Integer[] expected = Arrays.copyOf(numbers, numbers.length - 1);
		ArrayList<Integer> removeList = new ArrayList();
		removeList.add(numbers[numbers.length - 1]);
		
		collection.removeAll(removeList);
		
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
	}

	@Override
	void removeAll_normalFlow_removedAll() {
		ArrayList<Integer> removeList = new ArrayList();
		
		for (var num: numbers) {
			removeList.add(num);
		}
		collection.removeAll(removeList);
		
		assertArrayEquals(new Integer[0], collection.toArray(new Integer[0]));	
	}
	
	private ArrayList<Integer> getArrayList() {
		ArrayList<Integer> res = new ArrayList<>();
		
		for (var num: numbers) {
			res.add(num);
		}
		return res;
	}

}
