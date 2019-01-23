package bullsandcows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean endOfGame = false;
        Scanner scan = new Scanner(System.in);
        int secretNumLength = getSecretNumLength(scan);
        String secretNumStr = getSecretNumber(secretNumLength);
        secretNumberGuessing(endOfGame, scan, secretNumLength, secretNumStr);

        scan.close();
    }

    public static void secretNumberGuessing(boolean endOfGame, Scanner scan, int secretNumLength, String secretNumStr) {
        while (!endOfGame) {
            System.out.println("Try to guess the secret number.");
            String numberFromGamer = scan.next();
            if (numberFromGamer.length() != secretNumLength) {
                System.out.println("Wrong length of a number. Please enter a number with " + secretNumLength + " digits.");
                continue;
            }
            endOfGame = grader(secretNumStr, numberFromGamer);
        }
    }

    private static int getSecretNumLength(Scanner scan) {
        System.out.println("Input secret number's length: ");
        int secretNumLength = scan.nextInt();
        if (secretNumLength > 10) {
            System.out.println("Can't generate a secret number with a length of 100 because there aren't enough unique digits. " +
                                "Please enter a number not greater than 10.");
            secretNumLength = scan.nextInt();
        }
        return secretNumLength;
    }

    private static boolean grader(String secretNumStr, String numberFromGamer) {
        boolean endOfGame = false;
        int secretNumLength = secretNumStr.length();
        int bulls = 0;
        int cows = 0;
        if (secretNumStr.equals(numberFromGamer)) {
            System.out.println("Congratulations! You input true secret number!");
            endOfGame = true;
            return endOfGame;
        }
        char[] secretNumCharArray = secretNumStr.toCharArray();
        char[] numberFromGamerCharArray = numberFromGamer.toCharArray();
        for (int i = 0; i < secretNumLength; i++) {
            for (int j = 0; j < secretNumLength; j++) {
                if (i == j && secretNumCharArray[i] == numberFromGamerCharArray[j]) {
                    bulls++;
                    continue;
                }
                if (i != j && secretNumCharArray[i] == numberFromGamerCharArray[j]) {
                    cows++;
                }
            }
        }
        System.out.println(bulls + " bull(s) + and " + cows + " cow(s)."/* The secret number is " + secretNumStr*/);
        return endOfGame;
    }

    private static String getSecretNumber(int secretNumLength) {
        long pseudoRandomNumber = System.nanoTime();
        String secretNumStr = "";
        for (int i = 1; i <= secretNumLength; i++) {
            String digitForSecretNum = pseudoRandomNumber % 10 + "";
            while (secretNumStr.indexOf(digitForSecretNum) >= 0) {
                pseudoRandomNumber /= 10;
                if (pseudoRandomNumber < 1) {
                    pseudoRandomNumber = System.nanoTime();
                }
                digitForSecretNum = pseudoRandomNumber % 10 + "";
            }
            secretNumStr += digitForSecretNum;
        }
        return secretNumStr;
    }
}