

/**
 * 
 * @author Liam
 * This program chooses a set of teams from the String array
 * Only meant to be used in a single execution at a time, don't implement any additional loops
 */
public class SuperSimpleTeamChooser {
	
	private static String[] teams = {"Central", "Highland", "Como",
									"Washington", "OWL", "Harding",
									"Humboldt", "Party Crasher"};
	
	private static int offset = 0;
	
	/**
	 * @author Liam
	 * @param index The index that the copying reaches
	 * This function basically shifts all elements right up to the index being removed
	 */
	public static void replace (int index) {
		
		if (index < teams.length) {
			
			for (int i = index; i > 0; i--) {
				
				teams[i] = teams[i-1];
			}
			
			offset++;
			
			//DEBUG
			/*for (int i = offset; i < teams.length; i++) {
				
				System.out.println("DEBUG: " + teams[i]);
			}*/
		}
		else {
			
			System.out.println("Invalid index!");
		}
	}
	
	/**
	 * @author Liam
	 * @param num double to be rounded into an integer
	 * @return The rounded integer form of the number provided
	 */
	public static int round(double num) {
		
		int whole = (int) num;
		
		double decimal = num - whole;
		
		if (num < 0) { // negative
			
			if (decimal < -0.5) { // For my program I want this to be less than only
				
				whole--;
			}
			
			return whole;
		}
		else {
			
			if (decimal >= 0.5) {
				
				whole++;
			}
			
			return whole;
		}
	}
	
	/**
	 * @author Liam
	 * Chooses a team from the string array of teams
	 */
	public static void chooseTeam() {
		
		int choice = round((Math.random() * (teams.length-offset) - 0.5)+ offset);
		
		System.out.println(teams[choice]);
		
		replace(choice);
	}
	
	/**
	 * @author Liam
	 * @param numChosen number of teams to choose
	 * Chooses a set of numChosen teams
	 */
	public static void chooseTeam(int numChosen) {
		
		if ((teams.length-1) <= 0 || numChosen <= 0) {
			
			System.out.println("Invalid Criteria");
		}
		else {
			
			int count = 0;
			
			while (count < numChosen) {
				
				chooseTeam();
				
				count++;
			}
		}
	}

	public static void main(String[] args) {
		
		chooseTeam(3);
	}

}
