package list;

import java.util.Iterator;

public interface MyList<E> extends Iterable<E>{

	int size();

	boolean isEmpty();

	void clear();

	String toString();

	void addFirst(E e);

	void addLast(E e);

	void add(int index, E element);

	void addAll(MyList<? extends E> c);

	void addAll(int index, MyList<? extends E> c);
	
	E set(int index, E element);

	E remove(int index);

	E remove(E e);

	E removeFirst();

	E removeLast();

	void removeAll(MyList<? extends E> c);

	E get(int index);

	boolean contains(E e);

	boolean equals(Object o);

	int indexOf(E e);

	int lastIndexOf(E e);

	boolean containsAll(MyList<? extends E> c);

	E[] toArray();

	MyList<E> subList(int fromIndex, int toIndex);

	Iterator<E> iterator();
}
