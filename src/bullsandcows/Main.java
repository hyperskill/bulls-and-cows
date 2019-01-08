package bullsandcows;
import java.util.*;

public class Main {
        public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nms = {0,1,2,3,4,5,6,7,8,9};
        int nmsn = 10;
        long end = 0;
        int nb = sc.nextInt();
        while (nb > 10){
            System.out.printf("Can't generate a secret number with a length of %d because there aren't enough unique digits.\nPlease enter a number not greater than 10.\n", nb);
            nb = sc.nextInt();
        }
        for (int n = 0; n < nb; n++){
            int pN = (int) System.nanoTime();
            pN = pN % nmsn;
            if (pN < 0)
                pN *= -1;
            if (end == 0 && pN == 0){
                pN = 3;
            }
            end = end * 10 + nms[pN];
            nms[pN] = nms[nmsn - 1];
            nmsn--;
        }
        String ss = Long.toString(end);
        System.out.println(ss);

        while (true) {
            String inp = sc.nextLine();
            if (inp.length() > ss.length()){
                inp = inp.substring(0, inp.length() - (inp.length() - ss.length()));
            }
            if (!inp.equals(ss)){
                int bull = bulls(inp, ss);
                int cow = cows(inp, ss);
                String bullS = "bulls";
                String cowS = "cows";
                if (cow > 0 && bull > 0) {
                    if (cow == 1)
                        cowS = "cow";
                    if (bull == 1)
                        bullS = "bull";
                    System.out.printf("%d %s %d %s here.\n", bull, bullS, cow, cowS);
                }
                else if (bull > 0) {
                    if (bull == 1)
                        bullS = "bull";
                    System.out.printf("%d %s here.\n", bull, bullS);
                }
                else if (cow > 0){
                    if (cow == 1)
                        cowS = "cow";
                    System.out.printf("%d %s here.\n", cow, cowS);
                }
                else {
                    System.out.println("No animals here!");
                }
            }
            else {

                System.out.println("Right! You won!");
                break;
            }
        }

    }

    private static int bulls(String a, String b){
       int nb = 0;
       for (int i = 0; i < a.length(); i++){
           if (a.charAt(i) == b.charAt(i))
               nb++;
       }
        return nb;
    }

    private static int cows(String a, String b){
        int nb = 0;
        int cow = 0;
        for (int i = 0; i < a.length(); i++){
            nb = 0;
            for (int k = 0; k < a.length(); k++) {
                if (a.charAt(k) == b.charAt(i) && i != k && a.charAt(i) != b.charAt(i)){
                    nb++;
                    break;
                }
            }
            if (nb > 0)
                cow++;
        }
        return cow;
    }
}
