package telran.util;

import java.util.Iterator;
import java.util.Objects;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_TABLE_LENGTH = 16;
	private LinkedList<T>[] hashTable;
	private float factor = 0.75f;
	private int size;
	@SuppressWarnings("unchecked")
	public HashSet(int tableLength) {
		hashTable = new LinkedList[tableLength];
	}
	public HashSet() {
		this(DEFAULT_TABLE_LENGTH);
	}
	@Override
	public boolean add(T obj) {
		if ((float)size / hashTable.length >= factor) {
			hashTableRecreation();
		}
		int index = getIndex(obj);
		LinkedList<T> list = null;
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		list = hashTable[index];
		boolean res = false;
		if(!list.contains(obj)) {
			res = true;
			list.add(obj);
			size++;
		}
		return res;
	}

	private void hashTableRecreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for(LinkedList<T> list: hashTable) {
			if(list != null) {
				for(T obj: list) {
					tmp.add(obj);
				}
			}
		};
		
		hashTable = tmp.hashTable;
		
	}
	private int getIndex(Object obj) {
		int hashCode = obj.hashCode();
		
		return Math.abs(hashCode) % hashTable.length;
	}
	@Override
	public boolean remove(Object pattern) {
		int index = getIndex(pattern);
		boolean res = false;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			res = list.remove(pattern);
			if(res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object pattern) {
		int index = getIndex(pattern);
		boolean res = false;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			res = list.contains(pattern);
		}
		return res;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Object pattern) {
		int index = getIndex(pattern);
		T res = null;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			Iterator<T> it = list.iterator();
			while(it.hasNext() && res == null) {
				T obj = it.next();
				if(Objects.equals(pattern, obj)) {
					res = obj;
				};
			}
		}
		return res;
	}

}
