package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

enum Colour {Hearts, Spades, Clubs, Diamonds};
enum face{Jack, Queen, King}

class Deck {
	private int size = 52;
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	public int getSize() {
		return this.size;
	}
	public void reduceSize() {
		this.size--;
	}
	
	public Deck() {
		for(Colour c: Colour.values()) {
			createColour(c);
		}
	}
	
	private void createColour(Colour colour) {
		for(int i = 1; i <= (this.size / 4); i++) {
			if(i == 11) {
				Card card = new Card(10, colour);
				card.face = face.Jack;
				this.cards.add(card);
			} else if (i == 12) {
				Card card = new Card(10, colour);
				card.face = face.Queen;
				this.cards.add(card);
			} else if (i == 13) {
				Card card = new Card(10, colour);
				card.face = face.King;
				this.cards.add(card);
			} else {
				Card card = new Card(i, colour);
				card.face = null;
				this.cards.add(card);
			}
		}
	}
}
class Card {
	private int value;
	private Colour colour;
	public face face;
	public Card(int value, Colour colour) {
		if(value < 1 || value > 10) {
			throw new IllegalArgumentException("Allowed values are 1 to 10");
		}
		this.value = value;
		this.colour = colour;
	}
	public int getValue() {
		return this.value;
	}
	public Colour getColour() {
		return this.colour;
	}
}

class Game {
	public ArrayList<Card> players_cards = new ArrayList<Card>();
	public int players_points;
	public int banks_points;
	public ArrayList<Card> banks_cards = new ArrayList<Card>();
	int sum_player_cards = 0;
	int sum_bank_cards = 0;
}

public class ObjectOrientedDesign {
	enum turn {player, bank};
	public static void fromDeckToSet(Game game, Deck deck, turn turn) {
		int temp;
		Random rand = new Random();
		temp = rand.nextInt(deck.getSize() - 1);
		if(turn == objectOrientedDesign.ObjectOrientedDesign.turn.player) {
			game.players_cards.add(deck.cards.get(temp));
			if(deck.cards.get(temp).getValue() == 1) {
				if((game.sum_player_cards + deck.cards.get(temp).getValue()) > 21) {
					game.sum_player_cards += 1;
				} else {
					game.sum_player_cards += 11;
				}
			} else {
				game.sum_player_cards += deck.cards.get(temp).getValue();
			}
		} else {
			game.banks_cards.add(deck.cards.get(temp));
			if(deck.cards.get(temp).getValue() == 1) {
				if((game.sum_bank_cards + deck.cards.get(temp).getValue()) > 21) {
					game.sum_bank_cards += 1;
				} else {
					game.sum_bank_cards += 11;
				}
			} else {
				game.sum_bank_cards += deck.cards.get(temp).getValue();
			}
		}
		deck.cards.remove(temp);
		deck.reduceSize();
	}
	
	public static void blackjackGame(Game game, Deck deck) {
		Collections.shuffle(deck.cards);
		Scanner scanner = new Scanner(System.in);
		while(game.sum_player_cards < 21 && game.sum_bank_cards < 21) {
			System.out.print("Do you want another card? ");
			String input = scanner.nextLine();
			if(input.equals("No") || input.equals("no")) {
				break;
			} 
			fromDeckToSet(game, deck, turn.player);
			fromDeckToSet(game, deck, turn.bank);
			System.out.println("Your current cards are: ");
			for(Card c: game.players_cards) {
				if(c.face != null) {
					System.out.print(c.face + " of "  + c.getColour() + " ");
				} else {
					System.out.print(c.getValue() + " "  + c.getColour() + " ");
				}
			}
			System.out.println(", and the sum of your cards is: " + game.sum_player_cards);
		}
		scanner.close();
		if(game.sum_player_cards == 21) {
			System.out.println("Player wins!");
			return;
		} else if(game.sum_player_cards > 21) {
			System.out.println("Bank wins!");
			return;
		} else while (game.sum_player_cards > game.sum_bank_cards){
			fromDeckToSet(game, deck, turn.bank);
		}
		if(game.sum_bank_cards <= 21 && game.sum_bank_cards > game.sum_player_cards) {
			System.out.println("Bank wins!");
			return;
		} else {
			System.out.println("Player wins!");
			return;
		}
	}
	public static void main(String[] args) {
		Deck myDeck = new Deck();
		Game game = new Game();
		blackjackGame(game, myDeck);
		System.out.println("Your cards: ");
		for(Card c: game.players_cards) {
			if(c.face != null) {
				System.out.print(c.face + " of "  + c.getColour() + " ");
			} else {
				System.out.print(c.getValue() + " "  + c.getColour() + " ");
			}
		}
		System.out.println();
		System.out.println("Banks cards: ");
		for(Card c: game.banks_cards) {
			if(c.face != null) {
				System.out.print(c.face + " of "  + c.getColour() + " ");
			} else {
				System.out.print(c.getValue() + " "  + c.getColour() + " ");
			}
		}
	}
}