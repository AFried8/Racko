package racko;

public class Player {
	
	private Card[] rack;
	private String name;
	
	public Player(String name) {
		rack = new Card[10];
		this.name = name;
	}
	
	public void addCard(Card card) {
		int index = 0;
		while(rack[index] != null) {
			index++;
		}
		rack[index] = card;
	}
	
	public Card switchCard(int index, Card card) {
		Card oldCard = rack[index];
		rack[index] = card;
		return oldCard;
		
	}
	
	public String rackList() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i<rack.length; i++) {
			str.append("Slot " + (i+1) + ": \t" +  rack[i] + "\n");
		}
		return str.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public int getCardInSlot(int slotNumber) {
		return rack[slotNumber].getNumber();
	}

}
