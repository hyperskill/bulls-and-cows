package bullsandcows;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        playingGame();
    }


    private static void playingGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Input the secret number’s length: ");
        int longOfNum = scanner.nextInt();
        if (longOfNum>10) {
            System.out.println ("Can't generate a secret number with a length of"  + longOfNum +  "because there aren't enough unique digits.");
            System.out.println ("Please enter a number not greater than 10.");
            return;
        }
        long randomNumber;
        String secretNumber;
        do {
            randomNumber = random.nextInt();
            secretNumber=giveSecretNumber(randomNumber);
        } while (secretNumber.length()<longOfNum);
        String numberForPrint="";
        if (secretNumber.length()>longOfNum) {
            String newSecretNumber = secretNumber.substring(0,longOfNum);
            for (int i=0; i<newSecretNumber.length(); i++){
                numberForPrint = numberForPrint + "*";
            }
            System.out.println ("The random secret number is " + numberForPrint +".");
            checkingAnswer (newSecretNumber);
        } else {
            for (int i=0; i<secretNumber.length(); i++){
                numberForPrint = numberForPrint + "*";
            }
            System.out.println ("The random secret number is " + numberForPrint + ".");
            checkingAnswer (secretNumber);
        }


    }

    private static String giveSecretNumber(long randomNumber) {
        String num = Long.toString(randomNumber);
        String secretNumber="";
        int numLength = num.length();
        for (int i=1; i<=numLength; i++) {
            if (!secretNumber.contains(Character.toString(num.charAt(numLength-i)))) {
                secretNumber = secretNumber + num.charAt(numLength-i);
            }
        }
        return secretNumber;
    }

    private static void checkingAnswer (String secretNumber) {
        Scanner scanner = new Scanner(System.in);
        int counter = 1;
        int numOfBulls;
        int numOfCows;
        do {
            System.out.println("Turn " + counter + ". Answer:");
            counter++;
            String userGuess = scanner.next();
            numOfBulls=0;
            numOfCows=0;
            for (int i=0; i<userGuess.length(); i++) {
                if (userGuess.charAt(i) == secretNumber.charAt(i)) {
                    numOfBulls++;
                } else if (secretNumber.contains(Character.toString(userGuess.charAt(i)))) {
                    numOfCows++;
                }
            }
            System.out.println("Grade: " + numOfBulls + " bill(s) and " + numOfCows + " cow(s)");
            System.out.println();
        } while (numOfBulls!=secretNumber.length());
        System.out.println("Congrats! The secret number is " + secretNumber);
    }
}






