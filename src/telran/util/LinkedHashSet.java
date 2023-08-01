package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.LinkedListIterator;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>(); 
	@SuppressWarnings("unchecked")
	private class LinkedHashSetIterator implements Iterator<T> {
		
		@SuppressWarnings("rawtypes")
		LinkedListIterator it = (LinkedListIterator) list.iterator();
		@Override
		public boolean hasNext() {
			
			return it.hasNext();
		}

		
		@Override
		public T next() {
			
			return (T) it.next();
		}
		@Override 
		public void remove() {
			
			Node<T> removedNode = it.current != null ? it.current.prev : list.tail;
			map.remove(removedNode.obj);
			it.remove();
			
		}
		
	}
	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(!map.containsKey(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			map.put(obj, node);
			list.addNode(list.size(), node);
		}
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		
		if(map.containsKey(pattern)) {
			res = true;
			Node<T> node = map.remove(pattern);
			list.removeNode(node);
		}
		return res;
	}

	@Override
	public boolean contains(Object pattern) {
		
		return map.containsKey(pattern);
	}

	@Override
	public int size() {
		
		return list.size();
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(Object pattern) {
		T res = null;
		if(contains(pattern)) {
			res = map.get(pattern).obj;
		}
		return res;
	}

}
