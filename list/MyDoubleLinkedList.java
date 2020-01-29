package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoubleLinkedList<E> extends MyAbstractList<E> {
	DoubleNode head;
	DoubleNode tail;
	int size;

	public MyDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	public void addFirst(E a) {
		DoubleNode newNode = new DoubleNode(a);

		if (isEmpty()) {
			tail = newNode;
		} else {
			head.previous = newNode;
		}
		newNode.next = head;
		head = newNode;
		size++;
	}

	public void addLast(E a) {
		DoubleNode newNode = new DoubleNode(a);

		if (isEmpty()) {
			head = newNode;
		} else {
			tail.next = newNode;
		}
		newNode.previous = tail;
		tail = newNode;
		size++;
	}

	@Override
	public void add(int index, E element) {
		checkOfIndex(index);

		if (index == 0) {
			addFirst(element);
		} else {
			DoubleNode prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}
			DoubleNode newNode = new DoubleNode(element);
			DoubleNode next = prev.previous;

			prev.next = newNode;
			newNode.next = next;

			next.previous = newNode;
			newNode.previous = prev;
			size++;
		}
	}

	@Override
	public void addAll(MyList<? extends E> c) {
		for (E e : c) {
			addLast(e);
		}
	}

	@Override
	public void addAll(int index, MyList<? extends E> c) {
		for (E e : c) {
			add(index, e);
		}
	}

	@Override
	public E set(int index, E element) {
		checkOfIndex(index);
		
		DoubleNode current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.data = element;
		return element;
	}

	public E removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E temp = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
		} else {
			head.previous = null;
		}
		size--;
		
		return temp;
	}

	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E temp = tail.data;
		tail = tail.previous;
		if (tail == null) {
			head = null;
		} else {
			tail.next = null;
		}
		size--;
		return temp;
	}

	@Override
	public E remove(int index) {
		checkOfIndex(index);

		if (index == 0) {
			return removeFirst();
		} else if (index == (size - 1)) {
			return removeLast();
		} else {
			DoubleNode previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			DoubleNode current = previous.next;//tail
			DoubleNode next = current.next;//null

			previous.next = next;
			next.previous = previous;
			size--;
			return current.data;
		}
	}

	@Override
	public E remove(E e) {
		int index = indexOf(e);

		if (index == -1)
			return null;
		return remove(index);
	}

	@Override
	public void removeAll(MyList<? extends E> c) {
		for (E e : c) {
			remove(e);
		}
	}

	@Override
	public E get(int index) {
		checkOfIndex(index);
		DoubleNode result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result.data;
	}

	@Override
	public int indexOf(E o) {
		/*
		int result = -1;
		DoubleNode tmp = head;
		while (tmp != null) {
			result++;
			if (tmp.data.equals(o)) 
				return result;
			tmp = tmp.next;
		}
		return result;
		*/
		
		if (size == 0) {
			return -1;
		} else {
			DoubleNode tmp = head;
			int result = 0;
			while (tmp != null) {
				if (tmp.data.equals(o)) {
					return result;
				} else {
					tmp = tmp.next;
					result++;
				}
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E o) {
		if (size == 0) {
			return -1;
		} else {
			DoubleNode tmp = tail;
			int result = size - 1;
			while (tmp != null) {
				if (tmp.data.equals(o)) {
					return result;
				} else {
					tmp = tmp.previous;
					result--;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean contains(E e) {
		if (size == 0) {
			return false;
		} else {
			DoubleNode tmp = head;
			while (tmp != null) {
				if (tmp.data.equals(e)) {
					return true;
				} else {
					tmp = tmp.next;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList<? extends E> c) {
		for (E e : c) {
			if (!contains(e))
				return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] arr = (E[]) new Object[size];
		int count = 0;
		for (E e : this) {
			arr[count++] = e;
		}

		return arr;
	}

	@Override
	public MyList<E> subList(int fromIndex, int toIndex) {
		checkOfIndex(fromIndex);
		checkOfIndex(toIndex);

		if (fromIndex > toIndex) {
			throw new IllegalArgumentException();
		}

		MyList<E> list = new MyDoubleLinkedList<>();
		int count = 0;
		for (E e : this) {
			if (fromIndex <= count && count <= toIndex) {
				list.addLast(e);
			}
		}

		return list;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			DoubleNode current = head;

			public boolean hasNext() {
				return (current != null);
			}

			@Override
			public E next() {
				E e = current.data;
				current = current.next;
				return e;
			}
		};
	}

	private class DoubleNode {
		E data;
		DoubleNode next;
		DoubleNode previous;

		public DoubleNode(E data) {
			this.data = data;
		}
	}
}
