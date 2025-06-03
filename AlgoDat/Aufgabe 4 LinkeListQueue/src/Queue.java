public class Queue  {
    private LinkedListElement first;
    private LinkedListElement last;

    public Queue() {
        first = null;
        last = null;
    }
   
    /**
     * Returns true if this queue contains no elements
     * @return true if this queue contains no elements, 
     * otherwise false
     */
    public boolean isEmpty() { return first==null; }

     /**
     * Returns a string representation of this queue which
     * consists of a list of elements, enclosed in square brackets
     * ("[]"). Adjacent elements are separated by the characters ", "
     * (comma and space).
     * @return  a string representation of the LinkedList 
     */        
     public String toString() {
         StringBuffer sb = new StringBuffer("[");
         LinkedListElement t = first;
         while(t!=null) {
             sb.append(t.data);
             t = t.next;
             if(t!= null) sb.append(", ");
         }
         sb.append("]");
         return sb.toString();
     }
    
    /**
      * Appends the specified data at the end of this queue
      * @param data - value to be appended
      */    
     public void append(String data) {
        LinkedListElement newElement = new LinkedListElement(data);

        if (isEmpty()) {
            first = newElement;
            last = newElement;
        } else {
            last.next = newElement;
            last = newElement;
        }
     }
     
    /**
     * Removes the first element of this queue and returns
     * its data.
     * @return On success, the data of the first element of 
     * this queue, otherwise null.
     */
    public String remove() {
        if (isEmpty()) {
            return null;
        }

        String data = first.data;
        first = first.next;

        if (first == null) {
            last = null;
        }
        return data;}
}


