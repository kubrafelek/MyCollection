package stack;

public interface Stack<T> {
	
	int size();
	
	void clear();
	
	boolean isEmpty();
	
	void push(T t);
	
	T pop();
	
	T peek();
}
