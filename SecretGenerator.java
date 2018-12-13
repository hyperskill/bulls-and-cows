import java.util.*;

final class  SecretGenerator {
    public static String stars = "";
    public static String endString = "";
    public static int numOfDigits = 0;
    public static List<String> firstList;
    public static void main() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the length of the secret code: ");
        numOfDigits = scan.nextInt();
        if (numOfDigits > 10) {
            System.out.println("Can't generate a secret number with a length of " + numOfDigits + " because there aren't enough unique digits.");
            System.out.println("Please enter a number not greater than 10.");
            System.exit(0);
        }
        System.out.print("Input the number of possible symbols in the code: ");
        int symbols = scan.nextInt();
        Set<String> secretSet = new TreeSet<>();
        String secretValueString = "abcdefghijklmnopqrstuvwxyz";
        while(numOfDigits != secretSet.size()){
            int rNumber = (int) Math.floor((Math.random() * 10));
            String numStr = Integer.toString(rNumber);
            if (secretSet.contains(numStr)){
                continue;
            } else if (numOfDigits != secretSet.size()){
                secretSet.add(numStr);
            }
            int rStr = (int) Math.floor(Math.random() * symbols);
            Character sValue =  secretValueString.charAt(rStr);
            String a = Character.toString(sValue);
            if (secretSet.contains(a)){
                continue;
            } else if (numOfDigits != secretSet.size()){
                secretSet.add(a);
            }
        }
        firstList = new ArrayList<>(secretSet);
        Collections.shuffle(firstList);

        for (int i = 0; i < numOfDigits; i++){
            stars += "*";
        }

        for (int i = 0; i < firstList.size(); i++){
            endString += firstList.get(i);
        }
    }
}