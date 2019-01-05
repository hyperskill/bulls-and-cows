package bullsandcows;

import java.util.*;
public class Main {
    public static void main(String[] args) {
       int cows=0;
       int bulls=0;
        Scanner sc = new Scanner (System.in);
        String s=sc.nextLine();
        long k=SecretGenerator.Generate(s);
        long l;
        String st=Long.toString(k);
        while (true) {
        System.out.println("Guess!");
       if  ((l=sc.nextLong())==k) {
            break;
        }
        String next=Long.toString(l);
           for (int i=0;i<st.length();i++){
               for (int j=0;j<next.length();j++){
                   if (st.charAt(i)==next.charAt(j)){
                       if(i==j){
                           bulls++;
                       } else {
                           cows++;
                       }
                   }
                   
               }
              
           }
           System.out.println("Bulls ="+bulls+" and Cows =" + cows);
        }
        System.out.println("Right");
    }
}
