package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = obj;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public boolean remove(Object pattern) {
		@SuppressWarnings("unchecked")
		T obj = (T) pattern;
		int oldSize = size;

		for (int i = 0; i < size; i++) {
			if (obj.equals(array[i])) {
				remove(i);
			}
		}
		return oldSize > size;
	}

	@Override
	public T[] toArray(T[] ar) {
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;
		for (T obj : this) {
			res[index++] = obj;
		}
		if (res.length > size) {
			res[size] = null;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				remove(i);
			}
		}
		return oldSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean addAll(Collection<T> collection) {
		int oldSize = size;

		for (var el : collection) {
			add(el);
		}
		return size > oldSize;
	}

	@Override
	public boolean removeAll(Collection<T> collection) {
		int oldSize = size;

		for (var el : collection) {
			remove(el);
		}
		return oldSize > size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new Iterator<T>() {
			private int next = 0;

			@Override
			public boolean hasNext() {
				return next < size;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return array[next++];
			}
			
		};
	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);

		size++;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index, array, index + 1, size - index);
		array[size] = null;
		array[index] = obj;
	}

	@Override
	public T get(int index) {
		indexValidation(index, false);

		return array[index];
	}

	@Override
	public T set(int index, T obj) {
		indexValidation(index, false);
		T prev = get(index);
		array[index] = obj;
		return prev;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}

	}

	@Override
	public int indexOf(T pattern) {

		return findElement(pattern, 0, size(), true);
	}

	@Override
	public int lastIndexOf(T pattern) {

		return findElement(pattern, size - 1, -1, false);
	}

	private int findElement(T pattern, int start, int end, boolean asc) {
		while (start != end) {
			if (get(start).equals(pattern)) {
				return start;
			}
			start = asc ? start + 1 : start - 1;
		}
		return -1;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {

		return findElement(predicate, 0, size(), true);
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {

		return findElement(predicate, size - 1, -1, false);
	}

	private int findElement(Predicate<T> filter, int start, int end, boolean asc) {
		while (start != end) {
			if (filter.test(get(start))) {
				return start;
			}
			start = asc ? start + 1 : start - 1;
		}
		return -1;
	}

}
