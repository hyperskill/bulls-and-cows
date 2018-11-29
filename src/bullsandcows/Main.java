package bullsandcows;

import java.util.Scanner;
import java.util.Arrays;

final class Main {
    private static long secret = 1;
    private static long pseudoRandomNumber;
    private static long l = 1;
    private static boolean used[] = new boolean[10];
    private static int bulls = 0;
    private static int cows = 0;

    public static void grade(int ans, int secret)
    {
        boolean[] dAns = new boolean[10];
        boolean[] dSecret = new boolean[10];
        
        bulls = 0;
        cows = 0;
        
        while(ans != 0) {
        	if(ans % 10 == secret % 10)
                bulls++;
            else {
                dAns[ans % 10] = true;
            	dSecret[secret % 10] = true;
            }
            ans /= 10;
            secret /= 10;
        }
        for(int i=0; i<10; i++)
            if(dAns[i] == dSecret[i])
                cows++;
        
        System.out.print("Grade: ");
        if(bulls > 0) {
            System.out.print(bulls + "bull");
        	if(bulls > 1) System.out.print("s");
        }
        if(cows > 0) {
            System.out.print(" and " + cows + "cow");
        	if(bulls > 1) System.out.print("s");
        
        }
        if(bulls == 0 && cows ==0)
            System.out.print("nothing");
        
        System.out.println(".");
        
    }
    
    public static void addDigits(int n)
    {
        while(l != n && pseudoRandomNumber != 0)
        {
            long d = pseudoRandomNumber % 10;
            if(!used[(int)d]) {
                secret *= 10;
                secret += d;
                l++;
                used[(int)d] = true;
            }
            pseudoRandomNumber /= 10;

        }
    }

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        used[1] = true;
        if(n > 10) {
            System.out.println("Can't generate a secret number with a length of " + n + " because there aren't enough unique digits.");
            System.out.println("Please enter a number not greater than 10.");
        }
        else {
        	while(l != n) {
                pseudoRandomNumber = System.nanoTime();
                addDigits(n);
            }
            
          while(bulls != n) {
            System.out.println();
            System.out.println("Turn " + turn + ". Answer: ");
            ans = sc.nextInt();
            grade(ans, secret);
        }
        
        System.out.println("Congrats! The secret number is " + secret + ".");
        }
    }
}
