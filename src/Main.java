import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        Scanner scanner;
        DoublyLinkedList dll = new DoublyLinkedList();
        System.out.println("""
                 Your options are following:
                 (Warning the code is key sensitive so please be careful while writing the names)
                 For pulling the customers from customer.txt file write: 1
                 For adding a new customer write: 2
                 For showing info about an already existing customer write: 3
                 For deleting a customer write: 4
                 For displaying every record in the list by alphabetical(tr) order write: 5
                 For displaying every record in the list by reverse alphabetical(tr) order write: 6
                 For closing the program write: 7\s""");
        while(true) { // Continues infinitely until user exits the program by choosing option 7
            System.out.println("Please enter your choice: ");
            int option = keyboardInput.nextInt();
            keyboardInput.nextLine();
            switch (option) {
                case 1 -> { // Reads trough the customer.txt file
                    try { // Opens the file correctly
                        scanner = new Scanner(new FileInputStream("customer.txt"));
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                        break;
                    }
                    while (scanner.hasNextLine()) {
                        ArrayList<String> phoneNumber = new ArrayList<>();
                        String inputLine = scanner.nextLine();
                        String delimiters = ","; //Divides everything by commas
                        StringTokenizer token = new StringTokenizer(inputLine, delimiters);
                        String name = token.nextToken();
                        String address = token.nextToken();
                        //Since there could be more than one phone number it loops trough the numbers
                        while (token.hasMoreTokens()) {
                            phoneNumber.add(token.nextToken());
                        }
                        //System.out.println(phoneNumber);
                        Customerinfo customer = new Customerinfo(address, name, phoneNumber);
                        dll.insert(customer);
                    }
                    scanner.close();
                }
                //Gets a similar line to one of the lines inside of the customer.txt filer as keyboard input then
                //does same things as the case 1
                case 2 -> {
                    ArrayList<String> phoneNumber2 = new ArrayList<>();
                    System.out.println("""
                    Please enter all information about the customer divided by commas:
                    For example: Celal Şengör,Istanbul,0 533 1111111,0 312 1111111\s""");
                    String inputLine = keyboardInput.nextLine();
                    String delimiters = ",";
                    StringTokenizer token = new StringTokenizer(inputLine, delimiters);
                    String name = token.nextToken();
                    String address = token.nextToken();
                    while (token.hasMoreTokens()) {
                        phoneNumber2.add(token.nextToken());
                    }
                    Customerinfo customer = new Customerinfo(address, name, phoneNumber2);
                    dll.insert(customer);
                }
                // Gets name of the wanted customer, then prints information about them via findOrDelete method option a
                case 3 -> {
                    System.out.println("Please enter name of the customer that you want information about: ");
                    String wanted = keyboardInput.nextLine();
                    dll.findOrDelete(wanted, "a");
                }
                // Gets name of the wanted customer, then  deletes them via findOrDelete method option b
                case 4 -> {
                    System.out.println("Please enter name of the customer that you want to be removed: ");
                    String wanted = keyboardInput.nextLine();
                    dll.findOrDelete(wanted, "b");
                }
                // Prints everyone from the list according to Turkish alphabetical order.
                case 5 -> dll.sortedPrint("a");
                // Prints everyone from the list according to reverse of the Turkish alphabetical order.
                case 6 -> dll.sortedPrint("b");
                // Finishes the process
                case 7 -> System.exit(0);
            }
        }
    }
}