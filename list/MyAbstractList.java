package list;

public abstract class MyAbstractList<E> implements MyList<E>{

	protected int size = 0;

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	protected void checkOfIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " size: " + size);
		}
	}
}
