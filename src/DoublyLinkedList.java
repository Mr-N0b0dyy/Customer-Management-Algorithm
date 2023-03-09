import java.text.Collator;
import java.util.Locale;

public class DoublyLinkedList {
    private Node head; // First node of the linked list
    private Node tail; // Last node of the linked list
    private int size;  // Size of the linked list
    Collator trCollator = Collator.getInstance(new Locale("tr", "TR"));
    public DoublyLinkedList() { // Parameterless Constructor
        head = null;
        tail = null;
        size = 0;}
    public void insert(Customerinfo data) {
        if (head == null) { //If empty create a new node
            tail = head = new Node(null, data, null);
            size++;
        } else {
            Node current = head; // Creates pseudo-iterator.
            boolean notFound = true;
            Node newNode = new Node(null, data, null); //Node
            String option = "a"; // For getting surname or name according to current loop.
            // (option a = checks surnames)
            // (option b = when the surnames are same checks names)
            // Checks if the Node is equal to the head by surname
            if ((trCollator.compare(data.getSurname("a"), head.data().getSurname("a"))) < 0) {
                newNode.setNext(head); //Sets new node's next value to head
                head.setPrevious(newNode); //Sets head's previous value to new node
                head = newNode; // new node becomes head.
                size++;
                notFound = false;
            }
            while (notFound) {
                // According to option method compares new node and current node either by surname or name
                if ((trCollator.compare(data.getSurname(option), current.data().getSurname(option)) < 0)) {
                    current.getPrevious().setNext(newNode); //Sets  current's previous node's next to new node
                    newNode.setPrevious(current.getPrevious()); //Sets new node's previous to  current's previous
                    current.setPrevious(newNode); //Sets next of the current's previous to new node
                    newNode.setNext(current); // Sets new node's next to current
                    size++;
                    notFound = false; // Place found
                } else if (trCollator.compare(data.getSurname(option), current.data().getSurname(option)) > 0) {
                    if (current.getNext() == null) { //Checks if there is a current nodes next or not
                        // If not, places new node after the current node
                        current.setNext(newNode);
                        newNode.setPrevious(current);
                        tail = newNode;
                        size++;
                        notFound = false; }
                    // This condition checks if the next node have the same surname or not when looping trough option b
                    else if ((option.equals("b")) && ((trCollator.compare(data.getSurname("a"),
                            // If not, places new node after the current node
                            current.getNext().data().getSurname("a"))) != 0)) {
                            current.getNext().setPrevious(newNode);
                            newNode.setNext(current.getNext());
                            current.setNext(newNode);
                            newNode.setPrevious(current);
                            size++;
                            notFound = false;
                    }
                    else {
                        current = current.getNext(); // If there is a next node checks that node
                    }
                }
                else {
                    // If surnames are equal does the same thing that specified in option a
                    // but this time checks the name instead
                    // This part continues the code for the possibility that;
                    // there are more than 2 people with the same surname
                    if(option.equals("a")){
                        if ((trCollator.compare(data.getFirstName(), head.data().getFirstName())) < 0) {
                            newNode.setNext(head);
                            head.setPrevious(newNode);
                            head = newNode;
                            size++;
                            break;
                        }
                        else{
                            option = "b"; //Changes option to b for checking everything again by the name
                        }
                    }
                    //If code ends while option b that means name and surname of the customers are identical.
                    else{
                        System.out.println("Cannot Compute");
                        System.exit(0);
                    }
                }
            }
        }
    }
    public void findOrDelete(String name ,String option) {
    Node current = head; // Creates pseudo-iterator.
    boolean notFound = true;
        while (notFound){
            if (name.equals(current.getData().getAdSoyad())) { // Finds customer by name and surname.
                if (option.equals("a")){ // Displays information about the wanted customer
                    System.out.println(current.getData());
                    notFound = false;
                }
                 else { //If option b than removes said customer from the list instead
                     //Checks 4 possibilities tha could happen and removes customers accordingly
                    if((current.equals(head)) && (current.equals(tail))) { //Cleanses the list
                         head = null;
                         tail = null;
                         size--;
                         notFound = false;
                     }
                    //Removes the head and makes one node next to the head, new head
                    else if (current.equals(head) && (!current.equals(tail)) ) {
                        head = head.getNext();
                        head.setPrevious(null);
                        size--;
                        notFound = false;
                    }
                    //Removes the tail and makes one node before the tail, new tail
                    else if(current.equals(tail) && (!current.equals(head))){
                        tail = tail.getPrevious();
                        tail.setNext(null);
                        size --;
                        notFound = false;
                    }
                    //Binds the previous node of the current node to the next node to the current node.
                    else{
                        current.getNext().setPrevious(current.getPrevious());
                        current.getPrevious().setNext(current.getNext());
                        size --;
                        notFound = false;
                    }
                    }
            }
            else {
                //Since it can't find wanted customer even end of the list that means that customer is not in the list
                if (current.getNext() == null){
                System.out.println("There is no such costumer available");
            }
            current = current.getNext(); //continues the loop
            }
        }
    }
    public void sortedPrint(String  option){
        Node current = head;
        if (option.equals("a")){ // Prints already sorted list
            if(current == null){
                System.out.println("There is no one in the list");
            }
            else{for (int a = 0; a < size; a++){
                System.out.println(current.getData());
                current = current.getNext();}
            }
        }
        else{ // Prints already sorted list beginning by the tail to the head
            if(current == null){
                System.out.println("There is no one in the list");
            }
            else{
                current = tail;
                for (int a = 0; a < size; a++){
                    System.out.println(current.getData());
                    current = current.getPrevious();
                }
            }
        }
    }
}