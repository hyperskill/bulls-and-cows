import java.util.Scanner;
//package bullsandcows;

public class Main {

    public static String secretGenerator(int numbers) {
        int initialNumbers = numbers;

        if (numbers > 10 | numbers < 1)
        {
            System.out.printf("Can't generate a secret number with a length of %d because there aren't enough unique digits.%n", numbers);
            System.out.println("Please enter a number not greater than 10.");
            return "Wrong input";

        } else
        {
            String answer = "";
            while (numbers > 0)
            {
                long pseudoRandomNumber = System.nanoTime();
                String strNum = pseudoRandomNumber + "";

                for (int i = strNum.length() - 1; i >= 0; i --)
                {
                    if (numbers <= 0) break;

                    char digit = strNum.charAt(i);

                    // If answer already contains that digit - pass.
                    if (answer.indexOf(digit) >= 0) continue;

                    answer += digit;
                    numbers--;
                }
            }
            System.out.printf("The secret is prepared: %s.%n", new String(new char[initialNumbers]).replace("\0", "*"));
            return answer;
        }
    }

    public static int grader(String correctAnswer, String input){
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < correctAnswer.length(); i++) {
            if (correctAnswer.charAt(i) == input.charAt(i))
            {
                bulls += 1;
            } else if (correctAnswer.indexOf(input.charAt(i)) >= 0)
            {
                cows += 1;
            }
        }

        System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bulls, cows);

        return bulls;
    }

    public static boolean isUserAnswerCorrect(String userAnswer, int numberLength) {
        // Check if user input exactly needed number of digits
        if (userAnswer.length() != numberLength) {
            System.out.printf("Please input exactly %d different digits.%n", numberLength);
            return false;
        }

        // Check if all digits are different
        for (char c : userAnswer.toCharArray())
        {
            if (userAnswer.length() - userAnswer.replaceAll(Character.toString(c),"").length() > 1)
            {
                System.out.println("Please ensure that all digits are different.");
                return false;
            }
        }

        return true;
    }

    public static boolean wishToExit(String userAnswer) {
        if (userAnswer.startsWith("/")) {
            return "/exit".equals(userAnswer);
        }

        return false;
    }


    public static void main(String[] args) {

        System.out.println("Hello, player! Enter the length of the secret number.");
        Scanner scanner = new Scanner(System.in);
        int secretNumberLength = scanner.nextInt();
        scanner.nextLine();

        String secretNumber = secretGenerator(secretNumberLength);
        while ("Wrong input".equals(secretNumber))
        {
            System.out.println("Do you wish to input length once more? 1 - yes, other - no");
            String wishAgain = scanner.nextLine().trim();

            if ("1".equals(wishAgain))
            {
                secretNumberLength = scanner.nextInt();
                secretNumber = secretGenerator(secretNumberLength);
            } else {
                break;
            }
        }

        if (!"Wrong input".equals(secretNumber))
        {
            int userBulls = 0;
            while (userBulls != secretNumberLength)
            {
                System.out.printf("%nInput a %d-digit number.%n", secretNumberLength);
                String userAnswer = scanner.nextLine().trim();
                if (wishToExit(userAnswer))
                {
                    System.out.println("Exiting the game...");
                    break;
                }

                if (isUserAnswerCorrect(userAnswer, secretNumberLength)) {
                    userBulls = grader(secretNumber, userAnswer);
                }
                System.out.println("");

            }

            if (userBulls == secretNumberLength) {
                System.out.printf("Congrats! The secret number is %s.%n", secretNumber);
            }
        }

    }
}