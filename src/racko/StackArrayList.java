package racko;
import java.util.ArrayList;

public class StackArrayList<T> implements StackInterface<T> {
	
	private ArrayList<T> items;
	
	public StackArrayList() {
		items = new ArrayList<T>();
	}
	
	public void push(T data) {
		items.add(data);
	}
	
	public void pop () {
		items.remove(items.size()-1);
	}
	
	public T top () {
		return items.get(items.size()-1);
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public boolean isFull() {
		return false;
	}

}
