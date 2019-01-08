package bullsandcows;
import java.util.*;

final class BulllsAndCows {
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] nms = {'0','1','2','3','4','5','6','7','8','9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        //long end = 0;
        System.out.println("Enter HC-ness!");
        int nb = sc.nextInt();
        while (nb > 35){
            System.out.printf("Can't generate a secret number with a length of %d because there aren't enough unique digits.\nPlease enter a number not greater than 10.\n", nb);
            nb = sc.nextInt();
        }
        System.out.println("Numbers of digits?");
        int nmsn = sc.nextInt();
        while (nmsn > nb){
            System.out.printf("Can't generate a secret number with a length of %d because there aren't enough unique digits.\nPlease enter a number not greater than 10.\n", nmsn);
            nmsn = sc.nextInt();
        }
        char[] end = new char[nmsn];
        int pos = 0;
        for (int n = 0; n < nmsn; n++){
            int pN = (int) (Math.random() * 100);
            pN = pN % nb;
            if (pN < 0)
                pN *= -1;
            //if (end.length == 0 && pN == 0){
              //  pN = 3;
            //}
            end[pos] = nms[pN];
            pos++;
            nms[pN] = nms[nb];
            nb--;
        }
        String ss = "";
        for (char c : end)
            ss += c;
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
        int nb;
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
