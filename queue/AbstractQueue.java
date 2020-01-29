package queue;

import list.MyList;
import queue.Queue;

public abstract class AbstractQueue<T> implements Queue<T> {
	private MyList<T> list;

	public AbstractQueue(MyList<T> list) {
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
	public void enqueue(T t) {
		list.addLast(t);
	}

	@Override
	public T dequeue() {
		return list.get(0);
	}
}
