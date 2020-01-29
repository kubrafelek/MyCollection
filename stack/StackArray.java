package stack;

import list.MyArrayList;

public class StackArray<T>  extends AbstractStack<T>{

	public StackArray() {
		super(new MyArrayList<>());
	}

}
