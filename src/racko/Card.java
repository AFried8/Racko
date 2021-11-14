package racko;

public class Card {
	
	private int number;
	
	public Card(Integer number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return String.valueOf(number);
	}

}
