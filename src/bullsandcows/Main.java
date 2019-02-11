package bullsandcows;

import java.util.*;

public class Main {
    static HashSet<Character> possibleSymbolsCharSet = new HashSet<>();
    static HashSet<Character> secretCodeSymbolsCharSet = new HashSet<>();
    static String stringCharSet = "0123456789abcdefghijklmnopqrstuvwxyz";
    static int secretCodeLength;
    static String secretCode;
    static int numOfPossibleChars;

    public static void main(String[] args) {
        boolean endOfGame = false;
        Scanner scan = new Scanner(System.in);
        secretCodeLength = getSecretCodeLength(scan);
        getPossibleSymbolsSet(scan);
        secretCode = getSecretCode(secretCodeLength, possibleSymbolsCharSet); //fills secretCodeSymbolsCharSet :)
        //System.out.println(secretCode); //for testing
        messageAboutSecretCode();
        secretNumberGuessing(endOfGame, scan, secretCodeLength, secretCode);

        scan.close();
    }

    public static void secretNumberGuessing(boolean endOfGame, Scanner scan, int secretCodeLength, String secretCode) {
        while (!endOfGame) {
            System.out.println("Try to guess the secret.");
            String codeFromGamer = scan.next();
            if (codeFromGamer.length() != secretCodeLength) {
                System.out.println("Wrong length of a code. Please enter a code with " + secretCodeLength + " symbols.");
                continue;
            }
            endOfGame = grader(secretCode, codeFromGamer);
        }
    }

    private static int getSecretCodeLength(Scanner scan) {
        int secretCodeLength;
        String inputLine = "";
        while (true) {
            System.out.println("Input secret code's length: ");
            try {
                inputLine = scan.nextLine();
                secretCodeLength = Integer.parseInt(inputLine);

            } catch (NumberFormatException e) {
                System.out.println("\"" + inputLine + "\" isn't a valid number.");
                continue;
            }
            if (secretCodeLength <= 0) {
                System.out.println("Length must be positive.");
                continue;
            }
            break;
        }
        return secretCodeLength;
    }

    private static void getPossibleSymbolsSet(Scanner scan) {
        String inputLine = "";
        while (true) {
            System.out.println("Input the number of possible symbols in the code: ");
            try {
                inputLine = scan.nextLine();
                numOfPossibleChars = Integer.parseInt(inputLine);
                if (numOfPossibleChars < secretCodeLength) {
                    System.out.println("It's not possible to generate a code with a length of " + secretCodeLength +
                            " with " + numOfPossibleChars + " unique symbols.");
                    continue;
                } else if (numOfPossibleChars > 36) {
                    System.out.println("Maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    continue;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + inputLine + "\" isn't a valid number.");
                continue;
            }
        }
        for (int i = 0; i < numOfPossibleChars; i++) {
            possibleSymbolsCharSet.add(stringCharSet.charAt(i));
        }
    }

    private static String getSecretCode(int secretCodeLength, HashSet<Character> setOfPossibleSymbols) {
        String secretCode = "";
        HashMap<Integer, Character> mapOfPossibleSymbols = new HashMap<>();
        int mapK = 0;
        Random random = new Random();
        for (char symbol : setOfPossibleSymbols) {
            mapOfPossibleSymbols.put(mapK,symbol);
            mapK++;
        }
        for (int i = 0; i < secretCodeLength; i++) {
            int index = random.nextInt(numOfPossibleChars);
            char randomSymbol = mapOfPossibleSymbols.get(index);
            while (randomSymbol == ' ') {
                index = random.nextInt(numOfPossibleChars);
                randomSymbol = mapOfPossibleSymbols.get(index);
            }
            secretCode += randomSymbol;
            secretCodeSymbolsCharSet.add(randomSymbol);
            mapOfPossibleSymbols.put(index, ' ');
        }
        return secretCode;
    }

    private static void messageAboutSecretCode () {
        String messageStars = "";
        for (int i = 0; i < secretCodeLength; i++) {
            messageStars += "*";
        }
        if (numOfPossibleChars <= 10) {
            System.out.println("The secret is prepared: " + messageStars + " (0-" + (numOfPossibleChars-1) + ").");
        } else if (numOfPossibleChars == 11) {
            System.out.println("The secret is prepared: " + messageStars + " (0-9),  a.");
        } else {
            System.out.println("The secret is prepared: " + messageStars + " (0-9)" +
                    ", (a-" + stringCharSet.charAt(numOfPossibleChars-1) + ").");
        }
    }

    private static boolean grader(String secretCode, String codeFromGamer) {
        boolean endOfGame = false;
        int bulls = 0;
        int cows = 0;
        if (secretCode.equals(codeFromGamer)) {
            System.out.println("Congratulations! You input true secret code!");
            endOfGame = true;
            return endOfGame;
        }
        char[] secretCodeCharArray = secretCode.toCharArray();
        char[] codeFromGamerCharArray = codeFromGamer.toCharArray();
        for (int i = 0; i < secretCodeLength; i++) {
            if (secretCodeCharArray[i] == codeFromGamerCharArray[i]) {
                bulls++;
                continue;
            } else if (secretCodeSymbolsCharSet.contains(codeFromGamerCharArray[i])) {
                cows++;
            }
        }
        System.out.println(bulls + " bull(s) + and " + cows + " cow(s).");
        return endOfGame;
    }
}