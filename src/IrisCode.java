import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class IrisCode {
    private static TreeMap<String, String> database = new TreeMap<>();
    private final double match = 0.32;

    public boolean enroll(String name, String hex){
        int n = hex.length();
        for (int i = 0; i < n; i++){
            char ch = hex.charAt(i);
            if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'F')){
                System.out.println("Failure - Invalid Input: Must be in Hex");
                return false;
            }
        }
        try {
            database.put(name, hex);
        } catch (Exception e) {
            System.out.println("Error");
        }
        System.out.println("Success - Enrollment");
        return true;
    }

    public boolean authenticate(String name, String hex){
        String iriscode = database.get(name);
        if (iriscode == null){
            System.out.println("Failure - Invalid Input: Can't Find Corresponding Name");
            return false;
        }
        hex = hexToBinary(hex);
        iriscode = hexToBinary(iriscode);
        double n = hex.length();
        double diff = 0;
        for (int i = 0; i < n; i++){
            char hexCh = hex.charAt(i);
            char irisCh = iriscode.charAt(i);
            if (hexCh != irisCh){
                diff++;
            }
        }
        double hamm = diff / n;
        // There is security concern for showing hamming distance data.
        System.out.println("Hamming Distance = " + hamm);
        return hamm < match;
    }

    // Copy from https://www.geeksforgeeks.org/java-program-to-convert-hexadecimal-to-binary/
    private static String hexToBinary(String hex)
    {

        // variable to store the converted
        // Binary Sequence
        String binary = "";

        // converting the accepted Hexadecimal
        // string to upper case
        hex = hex.toUpperCase();

        // initializing the HashMap class
        HashMap<Character, String> hashMap
                = new HashMap<Character, String>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;


        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < hex.length(); i++) {
            // extracting each character
            ch = hex.charAt(i);

            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))

                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += hashMap.get(ch);

                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        IrisCode ic = new IrisCode();

        boolean done = false;
        while (!done) {
            try {
                System.out.println("Welcome to Iris Authentication: " + "\n1. New Record \n2. Authenticate \n3. Terminate System");
                System.out.println("\nPlease Enter the Corresponding Integer: ");
                String input = scan.nextLine();
                int i = Integer.parseInt(input);
                if (i == 1) {
                    System.out.println("Please Enter Your Name: ");
                    String name = scan.nextLine();
                    System.out.println("Iris Code (8 HexDecimals): ");
                    String irisCode = scan.nextLine();
                    if (irisCode.length() != 8){
                        System.out.println("Error - Input Must be 8 HexDecimals");
                    } else {
                        ic.enroll(name, irisCode);
                    }
                    System.out.println("\n***********************************\n");
                } else if (i == 2) {
                    System.out.println("Please Enter Your Name: ");
                    String name = scan.nextLine();
                    System.out.println("Iris Code (8 HexDecimals): ");
                    String irisCode = scan.nextLine();
                    if (irisCode.length() != 8){
                        System.out.println("Error - Input Must be 8 HexDecimals");
                    } else {
                        if (ic.authenticate(name, irisCode)){
                            System.out.println("Success - Authentication");
                        } else {
                            System.out.println("Fail - Autentication");
                        }
                    }
                    System.out.println("\n***********************************\n");
                } else if (i == 3) {
                    System.out.println("System Terminated - All Data Are Cleared");
                    done = true;
                } else {
                    System.out.println("Error - Input Must Be 1, 2, or 3\n");
                }
            } catch (Exception e) {
                System.out.println("Error - Invalid Input\n");
            }
        }
    }

}
