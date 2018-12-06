import java.util.Scanner;
final class BullsAndCowsGame {
    public static void main(final String[] args) {
        grader();
    }


    /**
     * This method calculates how many bulls and cows in number by comparing it with secret number.
     */
    private static void grader() {
        Scanner scan = new Scanner(System.in);
        //use predifined secret number.
        SecretGenerator.main();
        String secretNumber = SecretGenerator.endString;
        System.out.println(secretNumber);
        String answer = null;
        while (!secretNumber.equals(answer)) {
            System.out.print("Enter your guess: ");
            answer = scan.nextLine();
            int cow = 0;
            int bull = 0;
            for (int i = 0; i < SecretGenerator.numOfDigits; i++) {
                int val1 = Integer.parseInt(String.valueOf(answer.charAt(i)));
                int val2 = Integer.parseInt(String.valueOf(secretNumber.charAt(i)));
                if (val1 == val2) {
                    bull++;
                }
            }
            int ind1 = 0;
            int ind2 = 0;
            for (int x = 0; x < SecretGenerator.numOfDigits; x++) {
                int val1 = Integer.parseInt(String.valueOf(secretNumber.charAt(x)));
                for (int y = 0; y < SecretGenerator.numOfDigits; y++) {
                    int val2 = Integer.parseInt(String.valueOf(answer.charAt(y)));
                    if (ind1 != ind2) {
                        if (val1 == val2) {
                            cow++;
                        }
                    }
                    ind2++;
                }
                ind1++;
                ind2 = 0;
            }
            if (cow != 0 && bull == 0) {
                System.out.println("Grade: " + cow + " cow(s).");
            } else if (bull != 0 && cow == 0) {
                System.out.println("Grade: " + bull + " bull(s).");
            } else {
                System.out.println("Grade: " + bull + " bull(s) and " + cow + " cow(s).");
            }

        }
    }
}
