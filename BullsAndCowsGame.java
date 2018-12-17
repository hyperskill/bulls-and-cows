import java.util.Scanner;
final class BullsAndCowsGame {
    public static void main(final String[] args) {
        SecretGenerator.main();
        System.out.println("The secret is prepared: " + SecretGenerator.stars + " (0 - 9), (a - z)");
        checker();
        }

    public static void checker(){
        int bulls = 0;
        int cows = 0;
        String guess;
        String check;
        Scanner scan = new Scanner(System.in);
        while (bulls != SecretGenerator.numOfDigits){
            System.out.print("Enter your guess: ");
            guess = scan.next();
            for(int i = 0; i < SecretGenerator.numOfDigits; i++){
                check = Character.toString(guess.charAt(i));
                if(SecretGenerator.firstList.contains(check)) {
                    cows++;
                }
            }

            for (int i = 0; i < SecretGenerator.numOfDigits; i++){
                if (guess.charAt(i) == SecretGenerator.endString.charAt(i)){
                    bulls++;
                }
            }
            System.out.println("Your guess is: Bulls - " + bulls + "   Cows - " + (cows - bulls));
            if(bulls == SecretGenerator.numOfDigits){
                break;
            }
            bulls = 0;
            cows = 0;
        }
    }
}
