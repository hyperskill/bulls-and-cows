package bullsandcows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int[] randKey;

    public static void main(final String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter length of key");
        int keys = scn.nextInt();
        setRandKey(keys);
        System.out.println("The secret is prepared: ****.");
        System.out.println();
        boolean game = true;
        int answer = 1;
        while (game) {
            System.out.println("Turn " + answer + ". Answer: ");
            String answerNumbers = scn.next();
            System.out.println(answerNumbers);
            if (checkBullsAndCows(answerNumbers)) {
                System.out.print("Congrats! The secret number is ");
                for (int k : randKey) {
                    System.out.print(k);
                }
                System.out.println();
                game = false;
            }
            answer++;
        }
    }

    private static boolean checkBullsAndCows(String answerNumbers) {
        int cows = 0;
        int bulls = 0;
        String result = "Grade: ";
        for (int i = 0; i < randKey.length; i++) {
            if (answerNumbers.contains(randKey[i] + "")) {
                if (Character.getNumericValue(answerNumbers.charAt(i)) == randKey[i]) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        if (cows == 1 && bulls == 0) {
            result += cows + " cow.";
        } else if (bulls == 1 && cows == 0) {
            result += bulls + " bull.";
        } else if (cows == 0 && bulls == 0) {
            result += " nothing.";
        } else if (bulls > 0 && cows == 0){
            result += bulls + " bulls.";
        } else if (cows > 0 && bulls == 0) {
            result += cows + " cows.";
        } else if (cows == 1 && bulls == 1){
            result += bulls + " bull and " + cows + " cow.";
        } else {
            result += bulls + " bulls and " + cows + " cows.";
        }
        System.out.println(result);
        if (bulls == randKey.length) {
            return true;
        } else {
            System.out.println();
            return false;
        }
    }

    private static void setRandKey(int keys) {
        randKey = new int[keys];
        Random r = new Random();
        for (int i = 0; i < randKey.length; i++) {
            randKey[i] = r.nextInt(10);
        }
    }
}