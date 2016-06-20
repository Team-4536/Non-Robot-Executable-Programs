
import java.lang.Math;
import java.util.Scanner;

public class Risk {
	
	/**
	 * @author Liam
	 * @param a an integer to be compared
	 * @param b an integer to be compared
	 * @return The maximum integer among the two integer parameters
	 */
	public static int getMax(int a, int b) {
		
		if (b > a) {
			
			return b;
		}
		else {
			
			return a;
		}
	}
	
	/**
	 * @author Liam
	 * @param a an integer to be compared
	 * @param b an integer to be compared
	 * @param c an integer to be compared
	 * @return The maximum integer among the three integer parameters
	 */
	public static int getMax(int a, int b, int c) {
		
		return getMax(getMax(a, b), c);
	}
	
	/**
	 * @author Liam
	 * @param arr an array of dice rolls
	 * @return The highest roll from an array of dice rolls
	 */
	public static int getMax(int [] arr) {
		
		int max = arr [0];
		
		for (int i = 0; i < arr.length; i++) {
			
			if (arr[i] > max) {
				
				max = arr[i];
			}
		}
		
		return max;
	}
	
	/**
	 * @author Liam
	 * @param arr an array of dice rolls
	 * @return The second highest roll from an array of dice rolls
	 */
	public static int secondMax(int [] arr) {
		
		int max1 = getMax(arr);
		
		int max2 = 0;
		
		int c = 0;
		
		//Test if there are two or more equal maximums
		for (int i = 0; i < arr.length; i++) {
			
			if (arr[i] == max1) {
				
				c++;
			}
		}
		
		if (c >= 2) {
			
			return max1;
		}
		else {
			
			for (int i = 0; i < arr.length; i++) {
				
				if (arr[i] > max2 && arr[i] < max1) {
					
					max2 = arr[i];
				}
			}
			
			return max2;
		}
	}
	
	/**
	 * @author Liam
	 * The execution of the code. It will ask if you want a fight to the death in which case the program will
	 * run the simulation without pause. If you choose to not to fight to the death you can pause and assess
	 * the remaining number of forces on each side as you run the simulation and exit (retreat) at any point.
	 */
	public static void main(String[] args) {
		
		boolean fightToDeath;
		
		String userInteraction;
		
		Scanner userInput = new Scanner(System.in);
		
		boolean repeat = true;
		
		//Initialize Deathmatch
		System.out.println("Fight to the death? (y/n) ");
		userInteraction = userInput.nextLine();
		
		if (userInteraction.charAt(0) == 'y'){
			
			fightToDeath = true;
		}
		else {
			
			fightToDeath = false;
		}
		
		//Initialize Attackers and Defenders
		System.out.println("Please enter the number of attacking troops "
							+ "and defending troops (total troops in each territory): ");
		int attackers = userInput.nextInt();
		int defenders = userInput.nextInt();
		
		int attackersLost = 0;
		int defendersLost = 0;
		
		//Loop Battle!
		while (repeat) {
			
			//Protect number of attackers and defenders from becoming less than zero.
			if (attackers <= 1 || defenders <= 0) {
				
				break;
			}
			
			//Initialize attacker rolls based on number of attackers
			int [] attackerRolls;
			
			if (attackers >= 3) {
				
				attackerRolls = new int[3];
			}
			else if (attackers == 2) {
				
				attackerRolls = new int[2];
			}
			else {
				
				attackerRolls = new int[1];
			}
			
			for (int i = 0; i < attackerRolls.length; i++) {
				
				attackerRolls[i] = (int)(Math.random()*6 + 1);
			}
			
			//DEBUG
			for (int i = 0; i < attackerRolls.length; i++) {
				
				System.out.println("Attacker Roll #" + i + ": " + attackerRolls[i]);
			}
			
			//Initialize Defender Rolls based on number of defenders
			int [] defenderRolls;
			
			if (defenders >= 2) {
				
				defenderRolls = new int [2];
			}
			else {
				
				defenderRolls = new int [1];
			}
			
			for (int i = 0; i < defenderRolls.length; i++) {
				
				defenderRolls[i] = ((int)(Math.random()*6)) + 1;
			}
			
			//DEBUG
			for (int i = 0; i < defenderRolls.length; i++) {
				
				System.out.println("Defender Roll #" + i + ": " + defenderRolls[i]);
			}
			
			//Compare Rolls
			//Case: Highest of both
			if (attackers >= 2 && defenders >= 2) {
				
				if (getMax(attackerRolls) > getMax(defenderRolls)) {
					
					defenders--;
					defendersLost++;
				}
				else {
					
					attackers--;
					attackersLost++;
				}
				
				if (secondMax(attackerRolls) > secondMax(defenderRolls)) {
					
					defenders--;
					defendersLost++;
				}
				else {
					
					attackers--;
					attackersLost++;
				}
			}
			else {
				
				if (getMax(attackerRolls) > getMax(defenderRolls)) {
					
					defenders--;
					defendersLost++;
				}
				else {
					
					attackers--;
					attackersLost++;
				}
			}
			
			System.out.println("\n\nBattle Statistics: ");
			System.out.println("Attackers Left: " + attackers);
			System.out.println("Defenders Left: " + defenders);
			System.out.println("Total Attackers Lost: " + attackersLost);
			System.out.println("Total Defenders Lost: " + defendersLost);
			
			if (!fightToDeath) {
				
				//User End Simulation
				System.out.println("Continue (y/n)? ");
				String input = userInput.next();
				
				if (input.equals("n")) {
					
					repeat = false;
					break;
				}
			}
			
		}
	}

}
