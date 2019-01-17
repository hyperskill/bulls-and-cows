package bullsandcows;

import java.util.*;

public class Main {

    private static String secret = "";
    private static int count = 1;
    private static boolean isWin = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int length = 0;
        int chars = 0;
        boolean isCorrect = false;

        do {
            System.out.println();
            System.out.println("Input the length of the secret code(from 1 to 10): ");
            length = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code(from 10 to 36): ");
            chars = scanner.nextInt();

            isCorrect = checkUserInput(length, chars);

            if (!isCorrect) {
                System.out.println();
                System.out.println("Incorrect input. Please try again");
            }

        } while (!isCorrect);

        secret = secretGenerator(length, chars);
        System.out.println("For exit input \"exit\"\n");

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

    private static String secretGenerator(int length, int chars) {
        String sec = "";
        boolean isDone = false;
        Random random = new Random();

        String allSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";

        while (!isDone) {
            int index = random.nextInt(chars);
            String randomSymbol = String.valueOf(allSymbols.charAt(index));
            if (sec.indexOf(randomSymbol) == -1) {
                sec += randomSymbol;
                if (sec.length() == length) {
                    isDone = true;
                }
            }
        }

        String numberStars = "";
        String availableSymbols = "0-9";

        for (int i = 0; i < length; i++) {
            numberStars += "*";
        }

        if (chars > 10) {
            availableSymbols += ", " + allSymbols.charAt(10) + "-" + allSymbols.charAt(chars - 1);
        }

        System.out.printf("The secret is prepared: %s (%s).\n\n", numberStars, availableSymbols);
        return sec;
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
        if ("exit".equals(answer)) {
            System.exit(0);
        }
        if (answer.length() != secret.length()) {
            return false;
        }

        for (int i = 0; i < answer.length(); i++) {
            for (int j = i + 1; j < answer.length(); j++) {
                if (answer.charAt(i) == answer.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkUserInput(int length, int check) {
        if ((length == 0 || check < 10) || (length > 10 || check > 36)) {
            return false;
        }

        return true;
    }
}