package bullsandcows;

import java.util.Random;
import java.util.Scanner;

final class main {
    public static void main(final String[] args) {
        //int pseudoRandomNumber = Math.abs((int) Math.abs(System.nanoTime()));
    	
    	String[] a = new String[]{"0","1","2","3","4","5","6","7","8","9"};
        Scanner scanner = new Scanner(System.in);
        
        int t = scanner.nextInt();
        
        int s;
        
        String n = "";
        
        while (true) {
	        if (t<=10) {
		        while (n.length()<t) {
		        	String rnd = (a[new Random().nextInt(a.length)]);
		        	n+=rnd;
		        	if (rnd=="") {
		        		continue;
		        	}
		        	s = Integer.parseInt(rnd);
		        	a[s] = "";
	
		        }
		        break;
	
	        } else {
	        	System.out.printf("Can\'t generate a secret number with a length of %d because there aren't enough unique digits.\n",t);
	        	System.out.println("Please enter a number not greater than 10.");
	        	continue;
	        }
        }
        
        int cow = 0;
		int bull = 0;
        while (true) {
        	cow = 0;
        	bull = 0;
        	
	        String m = scanner.next();
	    	
	        for (int i = 0;i<n.length();i++) {
	        	if (m.charAt(i)==n.charAt(i)) {
	        		bull++;
	        	} else {
	        		for (int j = i;j<n.length();j++) {
	        			if (m.charAt(i)==n.charAt(j)) {
	        				cow++;
	        			}
	        		}
	        	}
	        }
	        
	        
	        if (bull==0&&cow!=0) {
	        	System.out.print("Grade: "+ cow  + " cow(s).");
	        } else if (cow==0&bull!=0) {
	        	System.out.print("Grade: "+ bull  + " bull(s).");
	        } else if (bull!=0&&cow!=0) {
	            System.out.print("Grade: "+ bull  + " bull(s) and " + cow  + " cow(s).");
	        } else {
	            System.out.print("Grade: nothing.");
	        }
	        
	        if (bull==n.length()) {
	        	break;
	        } else {
	        	continue;
	        }
   
        }
        
        System.out.print(" The secret number is "+n);
    }
}
