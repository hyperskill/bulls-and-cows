package bullsandcows;

import java.util.*;

public class Main {

    private static char[] randKey;
    private static Scanner scn = new Scanner(System.in);
    private static String allSymb = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(final String[] args) {
        getData();
        System.out.println();
        boolean game = true;
        int answer = 1;
        while (game) {
            System.out.println("Turn " + answer + ". Answer: ");
            String answerNumbers = getAnswer();
            System.out.println(answerNumbers);
            if (checkBullsAndCows(answerNumbers)) {
                System.out.print("Congrats! The secret number is ");
                for (char k : randKey) {
                    System.out.print(k);
                }
                System.out.println();
                game = false;
            }
            answer++;
        }
    }

    private static String getAnswer() {
        String answer = "";
        while (scn.hasNext()) {
            answer = scn.next();
            if (answer.length() == randKey.length) {
                break;
            } else {
                System.out.println("bad length");
            }
        }
        return answer;
    }

    private static void getData() {
        int keys;
        int symbols;
        do {
            System.out.println("Enter length of key");
            if (scn.hasNextInt()) {
                keys = scn.nextInt();
            } else {
                System.out.println("\"" + scn.next() + "\" isnt a valid number.");
                continue;
            }
            System.out.println("Input the number of possible symbols in the code: ");
            if (scn.hasNextInt()) {
                symbols = scn.nextInt();
            } else {
                System.out.println("\"" + scn.next() + "\" isnt a valid number.");
                continue;
            }
            if (keys > symbols) {
                System.out.println("It's not possible to generate a code with a length of " + keys + " with " + symbols + " unique symbols.\n");
            } else {
                break;
            }
        } while (true);
        setRandKey(keys, symbols);
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < randKey.length; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("(" + allSymb.charAt(0) + "-" + allSymb.charAt(symbols - 1) + ")");
    }

    private static boolean checkBullsAndCows(String answerNumbers) {
        int cows = 0;
        int bulls = 0;
        String result = "Grade: ";
        for (int i = 0; i < randKey.length; i++) {
            if (answerNumbers.contains(randKey[i] + "")) {
                if (answerNumbers.charAt(i) == randKey[i]) {
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

    private static void setRandKey(int keys, int symbols) {
        String allSymbols = allSymb;
        allSymbols = allSymbols.substring(0, symbols);
        randKey = new char[keys];
        Random r = new Random();
        for (int i = 0; i < randKey.length; i++) {
            int charNum = r.nextInt(allSymbols.length());
            randKey[i] = allSymbols.charAt(charNum);
            allSymbols = allSymbols.replace(allSymbols.charAt(charNum) + "", "");
        }
    }
}