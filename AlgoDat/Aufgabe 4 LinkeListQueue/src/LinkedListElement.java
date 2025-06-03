
public class LinkedListElement {
    LinkedListElement next;
    String data;
    
    public LinkedListElement(String data, LinkedListElement next) {
        this.data = data;
        this.next = next;
    }
    
    public LinkedListElement(String data) {
        this.data = data;
        this.next = null;
    }
}
