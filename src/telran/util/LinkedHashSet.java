package telran.util;

import java.util.Iterator;

public class LinkedHashSet<T> implements Set<T> {
	HashMap<T, LinkedList.Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();
	@Override
	public boolean add(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		
		return list.iterator();
	}

	@Override
	public T get(Object pattern) {
		// TODO Auto-generated method stub
		return null;
	}

}
