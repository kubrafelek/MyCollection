package stack;

import list.MyList;

public abstract class AbstractStack<T> implements Stack<T> {

	private MyList<T> list;

	public AbstractStack(MyList<T> list) {
		this.list = list;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(T t) {
		list.addFirst(t);
	}

	@Override
	public T pop() {
		return list.remove((size() - 1));
	}

	@Override
	public T peek() {
		return list.get(list.size() - 1);
	}
}
