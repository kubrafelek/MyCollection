package queue;

import list.MyLinkedList;

public class QueueLinkedList<T>  extends AbstractQueue<T> {
	
	public QueueLinkedList() {
		super(new MyLinkedList<>());
	}

}
