package telran.util.test;

import telran.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Collection;

public class TreeSetTest extends SortedSetTest {
	@BeforeEach
	@Override
	void setUp() {
		collection = new TreeSet<>();
		super.setUp();
	}

	@Override
	protected Collection<Integer> getCollection(Integer[] ar) {
		TreeSet<Integer> res = new TreeSet<>();
		for(Integer num: ar) {
			res.add(num);
		}
		return res;
	}

}
