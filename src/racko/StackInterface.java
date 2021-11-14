package racko;

public interface StackInterface<T> {
	
	public T top();
	public void push(T data);
	public void pop();
	public boolean isEmpty();
	public boolean isFull();
}
