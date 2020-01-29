package list;

import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

	private static final int FIRST_CAPACITY = 100;
	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[FIRST_CAPACITY];

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		data = (E[]) new Object[FIRST_CAPACITY];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addFirst(Object element) {
		enlargeIfNecesery();
		for (int i = size; i > 0; i--) {
			data[i] = data[i - 1];
		}
		data[0] = (E) element;
		size++;
	}

	@SuppressWarnings("unchecked")
	private void enlargeIfNecesery() {
		if (size == FIRST_CAPACITY) {
			// arrayi ikiye katla ve eski arrayi yeni arrayin ilk kismina kopyalka
			E[] newData = (E[]) new Object[data.length * 2];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
	}

	@Override
	public void addLast(E element) {
		enlargeIfNecesery();
		data[size] = element;
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) {
		if (index == 0) {
			addFirst(element);
			return;
		}
			
		checkOfIndex(index);
		enlargeIfNecesery();
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = (E) element;
		size++;
	}

	public E get(int index) {
		checkOfIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		if (isEmpty())
			throw new EmptyListException();
		
		checkOfIndex(index);

		E e = data[index];
		for (int j = index; j < size - 1; j++)
			data[j] = data[j + 1];
		data[size - 1] = null;
		size--;
		return e;
	}

	@Override
	public E set(int index, E element) {
		checkOfIndex(index);
		E old = data[index];
		data[index] = element;
		return old;
	}

	@Override
	public boolean contains(E e) {
		return indexOf(e) != -1;
	}

	@Override
	public int indexOf(E e) {
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i]))
				return i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (o.equals(data[i]))
				return i;
		}
		return -1;
	}

	public String toString() {
		StringBuilder output = new StringBuilder("[");

		for (int i = 0; i < size; i++) {
			output.append(data[i]);
			if (i < size - 1)
				output.append(" ,");
		}
		return output.toString() + "]";
	}

	@Override
	public E remove(E e) {
		int index = indexOf(e);
		if (index >= 0) {
			return remove(index);
		}
		return null;
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
	public E removeFirst() {
		return remove(0);
	}

	@Override
	public E removeLast() {
		return remove(size - 1);
	}

	@Override
	public void removeAll(MyList<? extends E> c) {
		for (E e : c) {
			remove(e);
		}
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
		
		E[] newData = (E[]) new Object[data.length];
		System.arraycopy(data, 0, newData, 0, data.length);
		
		return newData;
	}

	@Override
	public MyList<E> subList(int fromIndex, int toIndex) {
		checkOfIndex(fromIndex);
		checkOfIndex(toIndex);

		if (fromIndex > toIndex) {
			throw new IllegalArgumentException();
		}

		MyList<E> list = new MyArrayList<>();
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
			private int current = 0;

			@Override
			public boolean hasNext() {
				return current < size;
			}

			@Override
			public E next() {
				return data[current++];
			}
		};
	}
}
