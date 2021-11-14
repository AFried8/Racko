package racko;

public class PlayRacko {

	public static void main(String[] args) {
		
		System.out.println("Let's play racko!");
		System.out.println("The goal the of game is to get your rack into descending order");
		System.out.println("Your highest slot should contain the highest number, and the cards should decrease from there");
		System.out.println("Good Luck");
		
		Racko game = new Racko();
		game.dealCards();
		game.startGame();
		

	}

}
