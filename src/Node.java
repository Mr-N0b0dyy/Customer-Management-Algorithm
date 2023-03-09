public class Node {
    private final Customerinfo data;
    private Node previous;
    private Node next;
    public Node(Node previous,Customerinfo data, Node next) { // Constructor
        this.data = data;
        setNext(next);
        setPrevious(previous);
    }
    //Mutator Accessor methods
    public Node(Customerinfo data){this(null,data,null);}
    public Customerinfo data(){
        return data;
    }
    public Customerinfo getData() {
        return data;
    }
    public Node getPrevious() {
        return previous;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}