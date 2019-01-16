package bullsandcows;

import java.util.Scanner;

public class Main {

    private static String secret = "";
    private static int count = 1;
    private static boolean isWin = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the length of the secret code: ");
        int length = scanner.nextInt();

        if (length > 10) {
            System.out.println("Can't generate a secret number with a length of " + length +
                    " because there aren't enough unique digits.");
            System.out.println("Please enter a number not greater than 10.");
        } else {

            secret = secretGenerator(length);

            while (!isWin) {

                int bulls = grader();

                if (bulls == -1) {
                    System.out.println("Incorrect answer, try again.");
                }

                if (bulls == length) {
                    isWin = true;
                }
            }

            System.out.println("Congrats! The secret number is " + secret + ".");

            scanner.close();
        }
    }

    private static String secretGenerator(int length) {
        String secret = "";
        boolean isFinish = false;
        while (!isFinish) {
            String pseudo = String.valueOf(System.nanoTime());
            for (int j = pseudo.length() - 1; j >= 0; j--) {
                if (secret.indexOf(pseudo.charAt(j)) == -1) {
                    secret += pseudo.charAt(j);
                    if (secret.length() == length) {
                        isFinish = true;
                        break;
                    }
                }
            }
        }
        return secret;
    }

    private static int grader() {
        int bulls = 0;
        int cows = 0;

        System.out.println("Turn " + count + ". Answer: ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (isAsnswerCorrect(answer)) {
            for (int i = 0; i < secret.length(); i++) {
                int secretDigit = secret.charAt(i);
                for (int j = 0; j < answer.length(); j++) {
                    int answerDigit = answer.charAt(j);

                    if (answerDigit == secretDigit && i == j) {
                        bulls++;
                    }
                    if (answerDigit == secretDigit && i != j) {
                        cows++;
                    }
                }
            }
        } else {
            return -1;
        }

        if (cows == 0 && bulls == 0) {
            System.out.println("Grade: nothing.");
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bulls, cows);
        }
        System.out.println();
        count++;

        return bulls;
    }

    private static boolean isAsnswerCorrect(String answer) {
        if (answer.length() != secret.length()) {
            return false;
        }

        for (int i = 0; i < answer.length(); i++) {
            for (int j = i + 1; j < answer.length() ; j++) {
                if (answer.charAt(i) == answer.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }
}