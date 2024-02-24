package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	Node<T> head;
	Node<T> tail;
	int size;

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	private void addNode(int index, Node<T> node) {

		if (index == size) {
			addTail(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;

	}

	private void addMiddle(int index, Node<T> node) {

		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		node.next = nextNode;
		nextNode.prev = node;
		prevNode.next = node;
		node.prev = prevNode;

	}

	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;

	}

	private void addTail(Node<T> node) {
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {
			Node<T> next = head;
			boolean wasNext = false;
			Node<T> prev;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				wasNext = true;
				T res = next.obj;
				prev = next;
				next = next.next;
				return res;
			}

			public void remove() {
				if (prev == null) {
					throw new IllegalStateException();
				}

				T res = size < 2 ? removeLast(prev)
						: prev == head ? removeHead(prev) : prev == tail ? removeTail(prev) : removeMiddle(prev);
				size--;
				prev = null;
			}

		};

	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}

	private Node<T> getNode(int index) {

		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public T set(int index, T obj) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		T res = node.obj;
		node.obj = obj;

		return res;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		size--;

		return node == head ? removeHead(node) : node == tail ? removeTail(node) : removeMiddle(node);
	}

	private T removeHead(Node<T> node) {
		head = head.next;
		head.prev.next = null;
		head.prev = null;
		return node.obj;
	}

	private T removeTail(Node<T> node) {
		tail = tail.prev;
		tail.next.prev = null;
		tail.next = null;
		return node.obj;
	}

	private T removeMiddle(Node<T> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
		return node.obj;
	}

	private T removeLast(Node<T> node) {
		head = null;
		tail = null;
		return node.obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object pattern) {
		return indexOf(e -> Objects.equals(e, (T) pattern));
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object pattern) {
		return lastIndexOf(e -> Objects.equals(e, (T) pattern));

	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int ind = 0;
		var el = head;

		while (el != null && !predicate.test(el.obj)) {
			el = el.next;
			ind++;
		}

		return ind == size ? -1 : ind;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int ind = size - 1;
		var el = tail;

		while (el != null && !predicate.test(el.obj)) {
			el = el.prev;
			ind--;
		}
		return ind;
	}

}
