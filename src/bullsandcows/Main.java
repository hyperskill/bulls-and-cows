package bullsandcows;

import java.util.*;

public class Main {

    private static String secret = "";
    private static int count = 1;
    private static boolean isWin = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String result = "";
        String secretLength = "";
        String chars = "";

        while (!"isCorrect".equals(result)) {
            System.out.println();
            System.out.println("Input the length of the secret code(from 1 to 10): ");
            secretLength = scanner.nextLine();
            System.out.println("Input the number of possible symbols in the code(from 10 to 36): ");
            chars = scanner.nextLine();

            result = checkUserInput(secretLength, chars);

            if (!"isCorrect".equals(result)) {
                System.out.println();
                System.out.println(result);
            }
        }

        secret = secretGenerator(secretLength, chars);
        System.out.println("For exit input \"exit\"\n");

        while (!isWin) {

            int bulls = grader();

            if (bulls == -1) {
                System.out.println("Incorrect answer, try again.");
            }

            if (bulls == Integer.parseInt(secretLength)) {
                isWin = true;
            }
        }

        System.out.println("Congrats! The secret number is " + secret + ".");

        scanner.close();

    }

    private static String secretGenerator(String secretLength, String chars) {
        String sec = "";
        boolean isDone = false;
        Random random = new Random();

        String allSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";

        while (!isDone) {
            int index = random.nextInt(Integer.parseInt(chars));
            String randomSymbol = String.valueOf(allSymbols.charAt(index));
            if (sec.indexOf(randomSymbol) == -1) {
                sec += randomSymbol;
                if (sec.length() == Integer.parseInt(secretLength)) {
                    isDone = true;
                }
            }
        }

        String numberStars = "";
        String availableSymbols = "0-9";

        for (int i = 0; i < Integer.parseInt(secretLength); i++) {
            numberStars += "*";
        }

        if (Integer.parseInt(secretLength) > 10) {
            availableSymbols += ", " + allSymbols.charAt(10) + "-" + allSymbols.charAt(Integer.parseInt(secretLength) - 1);
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

    private static String checkUserInput(String secretLength, String chars) {

        for (int i = 0; i < secretLength.length(); i++) {
            if (!Character.isDigit(secretLength.charAt(i))) {
                return "\"" + secretLength + "\"" + " isn't a valid number";
            }
        }

        for (int i = 0; i < chars.length(); i++) {
            if (!Character.isDigit(chars.charAt(i))) {
                return "\"" + chars + "\"" + " isn't a valid number";
            }
        }

        if (Integer.parseInt(secretLength) > 10 || Integer.parseInt(secretLength) < 1) {
            if (Integer.parseInt(secretLength) > 10) {
                return "Can't generate a secret number with a length of " +
                        secretLength + " because there aren't enough unique digits.\n" +
                        "Please enter a number not greater than 10.";
            }
            return "It's not possible to generate a code with a length of " + secretLength + " with " + chars + " unique symbols.";
        }

        if (Integer.parseInt(chars) < 10 || Integer.parseInt(chars) > 36) {
            if ( Integer.parseInt(chars) > 36) {
                return "Maximum number of possible symbols in the code is 36 (0-9, a-z).";
            }
            return "It's not possible to generate a code with a length of " + secretLength + " with " + chars + " unique symbols.";
        }

        return "isCorrect";
    }
}