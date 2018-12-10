package bullsandcows;
import java.util.ArrayList;
import java util.Scanner;

public class Main {
        public static void main(String[] args) {
		grade();
	}
	
	public static void grade() {
		
		int numberLength;
		Scanner scanner = new Scanner(System.in);
		long hiddenNumber;
		long unravelNumber;
		int cows = 0;
		int bulls = 0;
		int turn = 1;
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		while (true) {
			System.out.print("Enter a length of secret number: ");
			numberLength = scanner.nextInt();	
			if (numberLength > 10) {
				System.out.println("Can't generate a secret number with a length of 100 because there aren't enough unique digits.");
				System.out.println("Please enter a number not greater than 10");
			}
			else 
				break;
		}
		
		hiddenNumber = generateNumber(numberLength);
		
		while (true) {
			System.out.println("Turn " + turn + ". Answer:");
			unravelNumber = scanner.nextLong();
			long hn = hiddenNumber;
			long un = unravelNumber;
			while (true) {
				if (hn % 10 == un % 10) {
					bulls ++;
				}
				else {
					list1.add(hn % 10);
					list2.add(un % 10);
				}
				
				hn /= 10; 
				un /=10;
				
				if (hn<= 0) {
					break;
				}
			}
			for ( Object i : list1) {
				for ( Object j : list2) {
					if (i == j) {
						cows++;
					}
				}
			}
			
			System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
			if (bulls == numberLength) {
				System.out.println("Congrats! The secret number is " + hiddenNumber);
				System.exit(0);
			}
			
			bulls = 0;
			cows = 0;
			list1.clear();
			list2.clear();
			turn++;
		}
	}
	
	public static long generateNumber(int numberLength) {
		
		long randomNumber = 0;
		long numeral;

		int i = 0;
        int k = 1;
		ArrayList list = new ArrayList();
        
       
		while (i < numberLength ) {	
			numeral = (int) (System.nanoTime() % 10);
			if (!(list.contains(numeral))) {
				list.add(numeral);
				randomNumber = numeral*k + randomNumber;
				k *= 10;
				i++;
			}
		}
		System.out.println("The random secret number is " + randomNumber);
		return randomNumber;
		
	}

}
