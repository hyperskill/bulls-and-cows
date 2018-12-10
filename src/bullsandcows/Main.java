package bullsandcows;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
	
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList hiddenList = new ArrayList();
	private static ArrayList answerlList = new ArrayList();
	private static int bulls = 0;
	private static int cows = 0;
	
	public static void main(String[] args) {
		grade();
	}
	
	public static int inputCodeLength() {
		int codeLength;
		while (true) {
			while (true) {
				System.out.print("Input a length of the secret code: ");
				if (scanner.hasNextInt()) {
					codeLength = scanner.nextInt();
					break;
				} else {
					System.out.println("You input not integer");
				}
				scanner.nextLine();
			}
		
			if (codeLength > 36) {
				System.out.println("Can't generate a secret code with a length of" + codeLength + "because there aren't enough unique symbols.");
				System.out.println("Please enter a number not greater than 36");
			}
			else 
				break;
		}
		return (codeLength);
	}
	
	public static int inputPossibleSymbols(int codeLength) {
		int possibleSymbols;
		while(true) {
			while (true) {
				System.out.print("Input the number of possible symbols in the code: ");
				if (scanner.hasNextInt()) {
					possibleSymbols = scanner.nextInt();
					break;
				} else {
					System.out.println("You input not integer");
				}
				scanner.nextLine();
			}
			
			if (possibleSymbols < codeLength)
				System.out.println("It's not possible to generate a code with a length of " + codeLength + " with " + possibleSymbols + " unique symbols.");
			else
				break;
		}
		return possibleSymbols;
	}
	
	public static void grade() {
		
		int codeLength;
		int possibleSymbols;
		String answerCode;

		codeLength = inputCodeLength();
		possibleSymbols = inputPossibleSymbols(codeLength);	
		generateHiddenCode(codeLength, possibleSymbols);
		scanner.nextLine();
		while (true) {
			answerCode = inputAnswerCode(codeLength);
			if (checkBullsAndCows (answerCode)) {
				System.out.println("Congrats! You win!");
				System.exit(0);
			}
		}
		
		}
	
	public static boolean checkBullsAndCows(String answerCode) {
		boolean label = false;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		for (int i =0; i < hiddenList.size(); i++) {
			if (hiddenList.get(i) == answerlList.get(i)) {
				bulls++;
			}
			else {
				list1.add(hiddenList.get(i));
				list2.add(answerlList.get(i));
			}
		}
		if (bulls == hiddenList.size())
			return label = true;
		
		for ( Object i : list1) {
			for ( Object j : list2) {
				if (i == j) {
					cows++;
				}
			}
		}
		System.out.println("Grade: "+ bulls +" bulls and "+ cows +" cows.");
		list1.clear();
		list2.clear();
		answerlList.clear();
		bulls = 0;
		cows = 0;
		return label;
	}
	
	public static String inputAnswerCode(int codeLength) {
		String answerCode;
		while (true) {
			System.out.print("Input answer: ");
			answerCode = scanner.nextLine();
			if (answerCode.length() != codeLength) {
				System.out.println("Count of symbols do not match! Please repeat action");
			}
			else
				break;
		}		
		for (int i = 0; i < answerCode.length(); i++) {
			answerlList.add(answerCode.charAt(i));
		}
		return answerCode;
	}
	
	
	public static void generateHiddenCode(int codeLength, int possibleSymbols ) {
		Random random = new Random();
		String allSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";
		char symbol = 0;
		int i = 0;
		
		while (i < codeLength) {
			symbol = allSymbols.charAt(random.nextInt(possibleSymbols));
			if (!(hiddenList.contains(symbol))) {
				hiddenList.add(symbol);
				i++;
			}
		}	
		System.out.print("The secret is prepared: ");
		for (int j =0; j < codeLength; j++)
			System.out.print("*");
		System.out.println(" " + allSymbols.substring(0, possibleSymbols-1));
	}
}
