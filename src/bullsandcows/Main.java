
import java.util.*;
//package bullsandcows;

public class Main {

    public static Set<Character> secretGenerator(int numbers, int possibleNumbers) {
        /*
        We will generate ints and then convert them to valid characters from ascii table.

        ASCII codes:

        0-9: 48-57 (max possibleNumbers - 10)
        a-z: 97-122 (max possibleNumbers - 36)
        A-Z: 65-90 (max possibleNumbers - 62) - it makes game too complicated, but can be added at any moment

         */
        int initialNumbers = numbers;

        Set<Character> answer = new LinkedHashSet<>();

        if (numbers > possibleNumbers | numbers < 1 | possibleNumbers < 1)
        {
            System.out.printf("Can't generate a secret number with a length of %d with only %d unique symbols.%n", numbers, possibleNumbers);
            System.out.println("Please enter a number of possible symbols no less than a number of symbols in secret.");
            return answer;

        } else
        {

            Random random = new Random(1);

            while (numbers > 0)
            {
                char digit = encodeInt(random.nextInt(possibleNumbers));

                // If answer already contains that digit - pass.
                if (answer.contains(digit)) continue;

                answer.add(digit);
                numbers--;
            }

            String borderDigits = borderDigits(possibleNumbers);
            String borderLetters = borderLetters(possibleNumbers);
            System.out.printf("The secret is prepared: %s (%s%s).%n", new String(new char[initialNumbers]).replace("\0", "*"), borderDigits, borderLetters);
            return answer;
        }
    }

    public static char encodeInt(int code) {
        char symbol = 0;
        if (code < 10)
        {
            return String.valueOf(code).charAt(0);
        } else
        {
            return (char) (code - 9 + 'a');
        }
    }

    public static String borderDigits(int code) {
        if (code < 2)
        {
            return code + "";
        }

        return 0 + "-" + Math.min(9, code-1);
    }

    public static String borderLetters(int code) {
        if (code - 10 <= 0) return "";
        if (code - 10 == 1) return ", a";

        return ", a-" + (char) (code - 9 + 'a');
    }

    public static int grader(Set<Character> correctAnswer, String input){
        int bulls = 0;
        int cows = 0;
        int size = correctAnswer.size();
        Iterator<Character> answerIterator = correctAnswer.iterator();

        for (int i = 0; i < size; i++) {
            if (answerIterator.next() == input.charAt(i))
            {
                bulls += 1;
            } else if (correctAnswer.contains(input.charAt(i)))
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

        // Check if there is no forbidden symbols

        return true;
    }

    public static boolean wishToExit(String userAnswer) {
        if (userAnswer.startsWith("/")) {
            return "/exit".equals(userAnswer);
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, player! Enter the length of the secret number:");
        int secretNumberLength = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the number of unique symbols in code:");
        int secretNumberOfSymbols = scanner.nextInt();
        scanner.nextLine();

        Set<Character> secretNumber = secretGenerator(secretNumberLength, secretNumberOfSymbols);

        while (secretNumber.isEmpty())
        {
            System.out.println("Do you wish to input length once more? 1 - yes, other - no");
            String wishAgain = scanner.nextLine().trim();

            if ("1".equals(wishAgain))
            {
                System.out.println("Enter the length of the secret number.");
                secretNumberLength = scanner.nextInt();
                scanner.nextLine();


                System.out.println("Enter the number of unique symbols in code:");
                secretNumberOfSymbols = scanner.nextInt();
                scanner.nextLine();

                secretNumber = secretGenerator(secretNumberLength, secretNumberOfSymbols);
            } else {
                break;
            }
        }

        if (!secretNumber.isEmpty())
        {
            int userBulls = 0;
            int turns = 1;
            while (userBulls != secretNumberLength)
            {
                System.out.printf("%nTurn %d. Answer:%n", turns);
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
                turns++;

            }

            if (userBulls == secretNumberLength) {
                System.out.printf("Congrats! The secret number is %s.%n", secretNumber);
            }
        }

    }
}