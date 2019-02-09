package bullsandcows;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        playingGame();
    }

    private static void playingGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Input the length of the secret code: ");
        int longOfNum = scanner.nextInt();
        System.out.print("Input the number of possible symbols in the code: ");
        int numOfSymbols = scanner.nextInt();
        Set<Character> secretCode = giveSecretCode(longOfNum, numOfSymbols);
        String numberForPrint="";
        for (int i=0; i<secretCode.size(); i++){
            numberForPrint = numberForPrint + "*";
            }
        if (numOfSymbols<10){
            System.out.println ("The secret is prepared: " + numberForPrint + ". 0-" + (numOfSymbols-1));
        } else {
            System.out.println("The secret is prepared: " + numberForPrint + ". 0-9, a-" + (char)(numOfSymbols+86));
        }
        checkingAnswer (secretCode);
    }

    private static Set<Character> giveSecretCode(int longOfNum, int numOfSymbols) {
        Random random = new Random();
        Set<Character> secretCode = new LinkedHashSet<>();
        do {
            int randomNumber = random.nextInt(numOfSymbols);
            char randomSymboll;
            if (randomNumber<10) {
                randomSymboll = (char) (randomNumber+48);
            } else {
                randomSymboll = (char) (randomNumber+87);
            }
            if (!secretCode.contains(randomSymboll)) {
                secretCode.add(randomSymboll);
            }
        } while (secretCode.size()<longOfNum);
        return secretCode;
    }

    private static void checkingAnswer (Set<Character> secretCode) {
        Scanner scanner = new Scanner(System.in);
        int counter = 1;
        int numOfBulls;
        int numOfCows;
        String strSecretCode = secretCode.toString();
        strSecretCode=strSecretCode.replace(", ", "");
        strSecretCode=strSecretCode.replace("[", "");
        strSecretCode=strSecretCode.replace("]", "");
        do {
            System.out.println("Turn " + counter + ". Answer:");
            counter++;
            String userGuess = scanner.next();
            numOfBulls=0;
            numOfCows=0;
            for (int i=0; i<userGuess.length(); i++) {
                if (userGuess.charAt(i) == strSecretCode.charAt(i)) {
                    numOfBulls++;
                } else if (strSecretCode.contains(Character.toString(userGuess.charAt(i)))) {
                    numOfCows++;
                }
            }
            System.out.println("Grade: " + numOfBulls + " bill(s) and " + numOfCows + " cow(s)");
            System.out.println();
        } while (numOfBulls!=strSecretCode.length());
        System.out.println("Congrats! The secret number is " + strSecretCode);
    }
}






