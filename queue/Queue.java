package queue;

public interface Queue<T> {
	
	int size();
	
	void clear();
	
	boolean isEmpty();
	
	void enqueue(T t);
	
	T dequeue();
}
