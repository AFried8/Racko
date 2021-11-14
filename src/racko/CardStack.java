package racko;

import java.util.ArrayList;

public class CardStack {
	
	private StackArrayList<Card> cards;
	
	public CardStack() {
		cards = new StackArrayList<Card>();
	}
	
	public CardStack(ArrayList<Integer> cardsNumber) {
		cards = new StackArrayList<Card>();
		for(int i = 0; i<cardsNumber.size(); i++) {
			cards.push(new Card(cardsNumber.get(i)));
		}
	}
	
	public Card topCard() {
		return cards.top();
	}
	
	public void removeTopCard() {
		cards.pop();
	}
	
	public void addCard(Card card) {
		cards.push(card);
	}
	
	public void addCard(int number) {
		cards.push(new Card(number));
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}

}
