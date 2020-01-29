package queue;

import list.MyArrayList;

public class QueueArray<T> extends AbstractQueue<T> {
	
	public QueueArray() {
		super(new MyArrayList<>());
	}

}
