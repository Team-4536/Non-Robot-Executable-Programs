import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;

public class BlackJack {
	
	private enum Cards{ACE(11, 1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
						EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
						
		private int value;
		private int altValue;
		
		Cards(int val) {
			
			this.value = val;
		}
		
		Cards(int val, int altVal) {
			
			this.value = val;
			this.altValue = altVal;
		}
		
		public int getAltValue() {
			
			return altValue;
		}
		
		public int getValue() {
			
			return value;
		}
	};
	
	public static void hit(ArrayList <Cards> aL) {
		
		int c = (int) (Math.random() * 13 + 1);
		
		switch (c) {
		
		case 1:
			aL.add(Cards.ACE);
		break;
		
		case 2:
			aL.add(Cards.TWO);
		break;
		
		case 3:
			aL.add(Cards.THREE);
		break;
		
		case 4:
			aL.add(Cards.FOUR);
		break;
		
		case 5:
			aL.add(Cards.FIVE);
		break;
		
		case 6:
			aL.add(Cards.SIX);
		break;
		
		case 7:
			aL.add(Cards.SEVEN);
		break;
		
		case 8:
			aL.add(Cards.EIGHT);
		break;
		
		case 9:
			aL.add(Cards.NINE);
		break;
		
		case 10:
			aL.add(Cards.TEN);
		break;
		
		case 11:
			aL.add(Cards.JACK);
		break;
		
		case 12:
			aL.add(Cards.QUEEN);
		break;
		
		case 13:
			aL.add(Cards.KING);
		break;
		}
	}
	
	public static void deal(ArrayList <Cards> aL) {
		
		hit(aL);
		hit(aL);
	}
	
	public static int cardValue(ArrayList <Cards> aL) {
		
		int sum = 0;
		
		for (int i = 0; i < aL.size(); i++) {
			
			sum += aL.get(i).getValue();
		}
		
		if(sum > 21) {
			
			int numAces = 0;
			
			for (int i = 0; i < aL.size(); i++) {
				
				if (aL.get(i).getValue() == 11) {
					
					numAces++;
				}
			}
			
			
			while (sum > 21 && numAces > 0) {
				
				numAces--;
				sum -= 10;
			}
			
			return sum;
		}
		
		return sum;
	}

	public static void main(String[] args) {
		
		Scanner userInteraction = new Scanner(System.in);
		String userInput;
		int playerMoney = 1_000;
		
		System.out.println("You enter the casino with $1,000. \"Luck be your lady tonight!\""
							+ " You seat yourself at the BlackJack table, will you make or break your fortune?!?");
		
		//Create Players
		ArrayList <Cards> player = new ArrayList<Cards>();
		ArrayList <Cards> dealer = new ArrayList<Cards>();
		
		while (playerMoney > 0) {
			
			int bet;
			
			do {
				System.out.println("How much money would you like to bet?");
				
				bet = userInteraction.nextInt();
				
				if (bet == 0) {
					
					System.out.println("Why even gamble nothing? What's the point?!?! Is this a GAME to you!");
				}
				else if(bet < 0) {
					
					System.out.println("This isn't Wall Street! You can't bet against yourself and win.");
				}
				else if (bet > playerMoney) {
					
					System.out.println("You don't have that kinda money. Perhaps you should try the slot machines...");
				}
			} while (bet > playerMoney || bet <=0);
			//Deal Cards
			deal(player);
			deal(dealer);
			
			int playerValue = cardValue(player);
			
			//Player turn
			System.out.println("Player Hand: ");
			System.out.println(player.get(0));
			System.out.println(player.get(1));
			System.out.println("Player Hand Value: " + playerValue);
			System.out.println("Dealer Card Shown: ");
			System.out.println(dealer.get(1));
			System.out.println("Dealer Shown Value: " + dealer.get(1).getValue());
			
			boolean bust = false;
			boolean hit = true;
			
			while (playerValue <= 21 && hit) {
				
				System.out.println("Hit? (\"Yes\" or \"No\")");
				userInput = userInteraction.next();
				
				if (userInput.equals("No")) {
					
					hit = false;
				}
				
				if (hit) {
					
					hit(player);
					
					System.out.println("Player Hand: ");
					for (int i = 0; i < player.size(); i++) {
						
						System.out.print(player.get(i));
						
						if (i < player.size() - 1) {
							
							System.out.print(", ");
						}
					}
					
					System.out.println("");
					System.out.println("Player Hand Value: " + cardValue(player));
					System.out.println("Dealer Shown Value: " + dealer.get(1).getValue());
				}
				
				if (cardValue(player) > 21) {
					
					System.out.println("BUST");
					bust = true;
					break;
				}
			}
			
			if (bust) {
				
				System.out.println("You Lose.");
				System.out.println("You lose $" + bet);
				bet = -bet;
			}
			else {
				
				while ((cardValue(player) > cardValue(dealer) || (cardValue(player) == cardValue(dealer) && cardValue(dealer) <= 14)) && cardValue(dealer) < 21) {
					
					hit(dealer);
				}
				
				System.out.println("Dealer's Hand: ");
				
				for (int i = 0; i < dealer.size(); i++) {
					
					System.out.print(dealer.get(i));
					
					if (i < dealer.size() - 1) {
						
						System.out.print(", ");
					}
				}
				
				System.out.println("");
				
				if (cardValue(dealer) > 21) {
					
					System.out.println("Dealer Bust!");
				}
				
				if (cardValue(dealer) > cardValue(player) && cardValue(dealer) <= 21) {
					
					System.out.println("You Lose.");
					System.out.println("You lose $" + bet);
					bet = -bet;
				}
				else if (cardValue(dealer) == cardValue(player) && cardValue(dealer) <= 21) {
					
					System.out.println("Draw.");
					System.out.println("Well, at least you didn't lose any money ...");
					bet = 0;
				}
				else {
					
					System.out.println("You Win!");
					System.out.println("You win $" + bet + "!");
				}
			}
			
			playerMoney += bet;
			
			System.out.println("You have $" + playerMoney);
			
			player.clear();
			dealer.clear();
		}
		
		System.out.println("You're broke, your job's a joke, and your love life's DOA. Just go back to binging \"Friends\" on Netflix on Friday nights. You're no gambler.");
	}
}
