package racko;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Racko {
	
	private int playersNumber;
	private CardStack unusedCards;
	private CardStack usedCards;
	private Player[] players;
	
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	
	public Racko() {
		//Create an array of numbers from 1-60 and shuffle
		ArrayList<Integer> cardNumbers = new ArrayList<>();
		for(int i =0; i<60; i++) {
			cardNumbers.add(i, i+1);
		}
		Collections.shuffle(cardNumbers);
		//Create deck based on random array of numbers 1-60
		unusedCards = new CardStack(cardNumbers);	
		usedCards = new CardStack();
		setupPlayers();
	}
		
	
	
	public void setupPlayers () {
		playersNumber = getNumberOfPlayers();
		players = new Player[playersNumber];
		for(int i = 0; i<playersNumber; i++) {
			System.out.println("Enter the name of player " + (i+1));
			String name = input.nextLine();
			players[i] = new Player(name);
		}
	}
	
	public int getNumberOfPlayers() {
		System.out.println("How many people are playing?");
		int playersNumber;
		do {
		playersNumber = input.nextInt();
		if(playersNumber <2 || playersNumber >4) {
			System.out.println("Racko requires 2-4 players");
			}
		}
		while(playersNumber <2 || playersNumber >4);
		input.nextLine();
		return playersNumber;
	}
	
	public void dealCards() {
		for(Player player: players) {
			for(int i=0; i<10; i++) {
				player.addCard(unusedCards.topCard());
				unusedCards.removeTopCard();
			}
		}	
	}
	
	public void startGame() {
		manageTurns(players, playersNumber);
	}
	
	public void manageTurns(Player[] players, int playersNumber) {
		boolean win = false;
		boolean gameEnded = false;
		while(!gameEnded) {
			for(int i = 0; i<playersNumber; i++) {
					win = turn(players[i]); //Current player goes his turn
					if(win) {				//If that player just won
						gameEnded = true;
						break;				//Break out of loop so next player doesn't go
					}
			}
		}
	}
	
	public boolean turn(Player player) {
		Card currCard;
		String choice = "n";
		
		System.out.println("\n" + player.getName()+", it is your turn. This is your rack");
		System.out.println(player.rackList());
		if(!usedCards.isEmpty()) {				//If there are used cards in the stockpile
			choice = chooseNewOrUsed(choice);
		}
		else {
			System.out.println("No available used cards");
		}
		
		if(choice.equalsIgnoreCase("u")) {			//If user chose to user the card in stockpile
			currCard = fetchUsedCard();
			currCard = switchCard(player, currCard);
			usedCards.addCard(currCard);
		}
		else {										//If user chose to deal a new card
			currCard = fetchNewCard();
			String cardChoice = discardOrUse();
			implementNewCard(cardChoice, player, currCard);
		}
		
		boolean win = checkForWin(player);			//Check if player won this round
		if(win) {
			System.out.println(player.getName() + " won! \nGame over");
		}
		return win;
	}
	
	public String chooseNewOrUsed(String choice) {
		System.out.println("Available used card: " + usedCards.topCard());
		System.out.println("Would you like to take the available used card or take a new card from the deck?");
		do{
			System.out.println("Type 'u' for used card and 'n' for new card");
			choice = input.nextLine();
		}
		while(!choice.equalsIgnoreCase("u") && !choice.equalsIgnoreCase("n"));
		return choice;
	}
		
	public Card fetchUsedCard(){
		Card currCard = usedCards.topCard();
		usedCards.removeTopCard();
		System.out.println("Your card is " + currCard);
		return currCard;
	}
	
	public Card fetchNewCard() {
		System.out.println("Picking new card...");
		if(unusedCards.isEmpty()) {					//If the stack of unused cards finished
			reloadDeck();		//Turn over the used cards stack
		}
		Card currCard = unusedCards.topCard();
		unusedCards.removeTopCard();
		System.out.println("Your card is " + currCard);
		return currCard;
	}
	
	public String discardOrUse() {
		String cardChoice;
		do {
		System.out.println("To discard this card, type 'discard' To use this card, type 'use'");
		cardChoice = input.nextLine();
		}
		while(!cardChoice.equalsIgnoreCase("discard") && !cardChoice.equalsIgnoreCase("use"));
		return cardChoice;
	}
	
	public void implementNewCard(String cardChoice, Player player, Card currCard) {
		if(cardChoice.equals("use")) {
			currCard = switchCard(player, currCard);
			usedCards.addCard(currCard);
		}
		else {
			System.out.println("Okay " + currCard + " discarded");
			usedCards.addCard(currCard);
		}
	}
	
	public Card switchCard(Player player, Card currCard) {
		System.out.println("Please enter the slot number where you wish to place this card");
		int slotNumber = input.nextInt();
		while(slotNumber < 1 || slotNumber >10) {
			System.out.println("The slot number can only be between 1 and 10");
			slotNumber = input.nextInt();
		}
		currCard = player.switchCard(slotNumber-1, currCard);
		System.out.println("Done! Your rack is now \n" + player.rackList());
		input.nextLine();
		return currCard;
	}
	
	public void reloadDeck() {
		//loads the cards in the used pile into the unused pile. Equivalent of flipping over the deck
		while(!usedCards.isEmpty()) {
			unusedCards.addCard(usedCards.topCard());
			usedCards.removeTopCard();			
		}
		
	}
	
	public boolean checkForWin(Player player) {
		//Checks that each successive number is lower than the one before it
		for(int i=0; i<9; i++) {
			if(player.getCardInSlot(i) < player.getCardInSlot(i+1)) {
				return false;
			}
		}
		return true;
	}
}