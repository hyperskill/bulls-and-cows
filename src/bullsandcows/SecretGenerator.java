package bullsandcows;
final class SecretGenerator {
          public static long Generate(String v) {
        
   
      if ( v.length()>2){
        System.out.println("Please enter a number not greater than 10.");
        return -1;
    }
    if (v.length()==2){
  if (Integer.parseInt(Character.toString(v.charAt(1)))>=1){
       System.out.println("Please enter a number not greater than 10.");
        return -1;
  }
          }
    int k=Integer.parseInt(v);
  Random r = new Random();
int lowerBound = 1;
int upperBound =(int)Math.pow(10,k)-1;
int secret = r.nextInt(upperBound-lowerBound) + lowerBound;

    return secret;
    
}
}
