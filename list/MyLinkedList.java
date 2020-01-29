package list;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private Node head;
	private Node tail;

	public MyLinkedList() {
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override
	public void addFirst(E e) {
		// Karmasiklik O(1)
		Node newNode = new Node(e);

		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head;// B artık A'yı point ediyor
			head = newNode;// head güncellendi
		}
		size++;
	}

	@Override
	public void addLast(E e) {
		// Karmasiklik O(1)
		Node newNode = new Node(e);

		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	@Override
	public void add(int index, E element) {
		checkOfIndex(index);

		if (index == 0) {
			addFirst(element);
		} else {
			Node previous = head;
			for (int i = 0; i < index - 1; i++)
				previous = previous.next;

			Node newNode = new Node(element);
			newNode.next = previous.next;
			previous.next = newNode;
			/*
			 * Node temp = current.next; current.next = new Node(element);
			 * (current.next).next = temp;
			 */
			size++;
		}
	}

	/*
	 * diger listedeki elemanları bu liste ekle
	 */
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
	public E set(int index, E e) {
		checkOfIndex(index);

		Node current = head;
		for (int i = 0; i < index; i++)
			current = current.next;

		E temp = current.data;
		current.data = e;

		return temp;
	}

	@Override
	public E removeFirst() {
		if (isEmpty())
			throw new EmptyListException();

		E temp = head.data;
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
		}
		size--;

		return temp;
	}

	@Override
	public E removeLast() {
		if (isEmpty())
			throw new EmptyListException();

		E temp = tail.data;

		if (size == 1) {// removeFirst() can be
			head = tail = null;
		} else {
			Node previous = head;
			while (previous.next != tail) {
				previous = previous.next;
			}
			previous.next = null;
			tail = previous;
		}

		size--;
		return temp;

	}

	@Override
	public E remove(int index) {
		checkOfIndex(index);

		if (index == 0)
			return removeFirst();

		if (index == size - 1)
			return removeLast();

		Node previous = head;
		// liste içinde c yi işaret eden previous.next elemanı bulmaya çalışıyor
		for (int i = 0; i < index - 1; i++)
			previous = previous.next;

		// bulunan elaman temp de konur
		E temp = previous.next.data;
		previous.next = previous.next.next;// previous.next current'in tutulmasıyla-çünkü listeden silinecek-boş kaldığı
											// için current.next olur
		size--;
		return temp;
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
	public boolean contains(Object o) {
		if (indexOf(o) > -1)
			return true;
		return false;
	}

	@Override
	public int indexOf(Object o) {
		Node current = head;

		for (int i = 0; i < size; i++) {
			if (current.data.equals(o))
				return i;
			current = current.next;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		int lastIndex = -1;
		Node current = head;

		for (int i = 0; i < size; i++) {
			if (current.data.equals(o))
				lastIndex = i;
			current = current.next;
		}
		return lastIndex;
	}

	@Override
	public E get(int index) {
		checkOfIndex(index);

		Node current = head;
		for (int i = 0; i < index; i++)
			current = current.next;

		return current.data;
	}

	@Override
	public boolean containsAll(MyList<? extends E> c) {
		// cok eski yotem
//		Iterator<? extends E> iter = c.iterator();
//		while (iter.hasNext()) {
//			if (!contains(iter.next()))
//				return false;
//		}

		for (E e : c) {
			if (!contains(e))
				return false;
		}
		return true;
	}

	public E[] toArray() {
		// generic array yaratıldı
		@SuppressWarnings("unchecked")
		E[] arr = (E[]) new Object[size];

		// e null olmadığı sürece datalar arraye eklenir
		int count = 0;
		for (E e : this) {
			arr[count++] = e;
		}

		return arr;

//		int count = 0;
//		Node e = head;
//		while (e != null) {
//			arr[count] = e.data;
//			// e hareket ettirilir
//			e = e.next;
//			count++;
//		}
//		return arr;
	}

	@Override
	public MyList<E> subList(int fromIndex, int toIndex) {
		checkOfIndex(fromIndex);
		checkOfIndex(toIndex);

		if (fromIndex > toIndex) {
			throw new IllegalArgumentException();
		}

		MyList<E> list = new MyLinkedList<>();
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
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				E temp = current.data;
				current = current.next;
				return temp;
			}
		};
	}

	@Override
	public String toString() {
		// Karmasiklik O(n)
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node current = head;
		while (current != null) {
			sb.append(current.data);
			sb.append(", ");
			current = current.next;
		}
		if (sb.length() > 1)
			sb.delete(sb.length() - 2, sb.length());
		sb.append("]");

		return sb.toString();
	}

	private class Node {
		private E data;
		private Node next;

		private Node(E data) {
			this.data = data;
		}		
	}

}
