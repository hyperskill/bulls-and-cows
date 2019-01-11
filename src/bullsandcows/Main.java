package bullsandcows;

import java.util.*;
import java.util.stream.IntStream;

public class Class9998 {
	
	public static int indexOf(String[] arr, String val) {
	    return IntStream.range(0, arr.length).filter(i -> arr[i] == val).findFirst().orElse(-1);
	}

	public static void main(String[] args) {
		
		while (true) {
			String[] a = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			String[] at = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		    Scanner scanner = new Scanner(System.in);
	
		    System.out.println("Input the length of the secret code:");
		    
		
			String tTest = scanner.nextLine();
			
			int t = 0;
			
			if (tTest.matches("\\d+")) { 
				t = Integer.parseInt(tTest);
		    } else {
		    	System.out.println("\""+tTest+"\" isn't a valid number.");
		    	break;
		    }
			    
			System.out.println("Input the number of possible symbols in the code:");
			    
			String lTest = scanner.nextLine();
			
			int lim = 0;
			
			if (lTest.matches("\\d+")) { 
				lim = Integer.parseInt(lTest);
		    } else {
		    	System.out.println("\""+lTest+"\" isn't a valid number.");
		    	break;
		    }
			
			if (lim>36) {
				System.out.println("Maximum number of possible symbols in the code is 36 (0-9, a-z).");
				break;
			}
		    
		    if (t>lim) {
		    	System.out.println("It's not possible to generate a code with a length of "+t+" with "+lim+" unique symbols.");
		    	break;
		    }
	
		    String n = "";
	
		    while (true) {
		        if (t<=a.length) {
			        while (n.length()<t) {
			        	String rnd = (a[new Random().nextInt(lim)]);
			        	n+=rnd;
			        	if (rnd=="") {
			        		continue;
			        	}
			        	a[indexOf(a,rnd)] = "";
			        	
			        }
			        break;
	
		        }
		    }
		    
		    System.out.print("The secret is prepared: ");
		    
		    for (int i = 0;i<n.length();i++) {
		    	System.out.print('*');
		    }
		    
		    if (lim<11) {
		    	System.out.println(" (0-"+at[lim-1]+")");
		    } else {
		    	System.out.println(" (0-9, a-"+at[lim-1]+")");
		    }
		    
		    
		    int cow = 0;
			int bull = 0;
			
			int c = 1;
		    while (true) {
		    	cow = 0;
		    	bull = 0;
		
		    	System.out.println("Turn "+c+". Answer:");
	
		        String m = scanner.next();
		        
		        if (m.length()!=n.length()) {
		        	System.out.println("\""+tTest+"\" isn't a valid number.");
		        	continue;
		        }
	
		        for (int i = 0;i<n.length();i++) {
		        	if (m.charAt(i)==n.charAt(i)) {
		        		bull++;
		        	} else {
		        		if (n.contains(Character.toString(m.charAt(i)))) {
		        			cow++;
		        		}
		        	}
		        }
	
		        if (bull==0&&cow!=0) {
		        	System.out.println("Grade: "+ cow  + " cow(s).");
		        } else if (cow==0&bull!=0) {
		        	System.out.println("Grade: "+ bull  + " bull(s).");
		        } else if (bull!=0&&cow!=0) {
		            System.out.println("Grade: "+ bull  + " bull(s) and " + cow  + " cow(s).");
		        } else {
		            System.out.println("Grade: nothing.");
		        }
		        
		        c++;
	
	
		        if (bull==n.length()) {
		        	break;
		        } else {
		        	continue;
		        }
		        
		        
		    }
	
		    System.out.print("The secret number is "+n);
		    
		}
		
	}

}
