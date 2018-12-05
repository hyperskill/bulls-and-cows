import java.util.Scanner;

final class SecretGenerator {
    public static String endString = "";
    public static int numOfDigits = 0;
    public static void main() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter how many Digits Secret code must contain: ");
        numOfDigits = scan.nextInt();
        if (numOfDigits > 10) {
            System.out.println("Can't generate a secret number with a length of " + numOfDigits + " because there aren't enough unique digits.");
            System.out.println("Please enter a number not greater than 10.");
            System.exit(0);
        }
        long pseudoRandomNumber = 0;
        int ind1 = 0;
        mainLoop:
        while(true) {
            pseudoRandomNumber = System.nanoTime();
            String checkString = Long.toString(pseudoRandomNumber);
            for (int i = checkString.length() - 1; i >= 0; i--) {
                if (endString.length() < numOfDigits) {
                    int val1 = Integer.parseInt(String.valueOf(checkString.charAt(i)));
                    for (int j = endString.length() - 1; j >= 0; j--) {
                        int val2 = Integer.parseInt(String.valueOf(endString.charAt(j)));
                        if (val1 == val2) {
                            break;
                        } else {
                            ind1++;
                        }

                    }
                    if (ind1 == endString.length()) {
                        endString += val1;
                    }
                    ind1 = 0;
                }
            }
            if(endString.length() == numOfDigits){
                break mainLoop;
            }
        }
    }
}