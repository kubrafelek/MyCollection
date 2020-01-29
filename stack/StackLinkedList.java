package stack;

import list.MyLinkedList;

public class StackLinkedList<T> extends AbstractStack<T> {

	public StackLinkedList() {
		super(new MyLinkedList<>());
	}

}
