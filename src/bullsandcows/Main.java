package com.company;

import java.util.Scanner;

public class Main {

    static public String SecretGenerator()
    {
        long pseudoRandomNumber = System.nanoTime();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длину случайного числа");
        int input = scanner.nextInt();
        String rezult = new String();
        boolean status = true;

        String random = Long.toString(pseudoRandomNumber);
        int count = 0;

        while(status)
        {
            if (input > 10)
            {
                System.out.println("Can't generate a secret number with a length of " + input + " because there aren't enough unique digits. Please enter a number not greater than 10.");
                System.out.println("Введите длину случайного числа");
                input = scanner.nextInt();
            }
            if(input<=10){
                break;
            }
        }
        if(input<=10)
        {

            for (int i = 0; input > rezult.length(); i++)
            {

                count = 0;

                if (random.length() == i)
                {
                    pseudoRandomNumber = System.nanoTime();
                    random = Long.toString(pseudoRandomNumber);
                    i = 0;
                }


                for (int j = 0; j < rezult.length(); j++)
                {

                    if (rezult.charAt(j) == random.charAt(i))
                    {

                        count++;
                        break;
                    }
                }

                if (count == 0) {
                    char num = random.charAt(i);
                    rezult += num;
                }
            }
        }
        return rezult;
    }

    static public int Grader(String secret)
    {
        int bulls = 0;
        int cows = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ответ");
        String answer = scanner.nextLine();

        for (int i = 0; i < secret.length(); i++) {
            int secretDigit = secret.charAt(i);
            for (int j = 0; j < answer.length(); j++) {
                int answerDigit = answer.charAt(j);

                if (answerDigit == secretDigit && i == j) {
                    bulls++;
                }
                if (answerDigit == secretDigit && i != j) {
                    cows++;
                }
            }

        }

        if (cows != 0 && bulls == 0) {
            System.out.println("Grade: " + cows + " cow(s). The secret number is " + secret + ".");
            return 0;
        }
        if (cows == 0 && bulls != 0) {
            System.out.println("Grade: " + bulls + " bull(s). The secret number is " + secret + ".");

            if(secret.length()==bulls){
                System.out.println("You are win!!!!!!!!!!!!!!!");
                return 1;
            }
            else{
                return 0;
            }
        }
        if (cows != 0 && bulls != 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret number is " + secret + ".");
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {

    String secret = SecretGenerator();
    int status = 0;

        while(status==0) {

            status = Grader(secret);

        }

    }
}
