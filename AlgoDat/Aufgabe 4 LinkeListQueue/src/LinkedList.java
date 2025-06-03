public class LinkedList {
    private LinkedListElement first;
    private int size;
        
    public LinkedList() {
        first = null;
        size  = 0;
    }
    
    /**
      * Inserts the specified data at the specified position in 
      * this list
      * @param index - index at which the specified value has to be
      * inserted
      * @param data - value to be inserted
      * @throws IndexOutOfBoundsException if the index is
      * out of range (<0 or >size())
      */
    public void insert(String data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            first = new LinkedListElement(data, first);
        } else {
            LinkedListElement temp = first;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = new LinkedListElement(data, temp.next);
        }

        size++;
    }
    
    /**
     * Removes the first occurrence of the specified data from this
     * list
     * @param data - the data to be removed from this list, if
     * present
     * @return true if this list contained the specified value
     * before removal
     */
    public boolean remove(String data) {
        if (first == null) {
            return false;
        }

        if (first.data.equals(data)){
            first = first.next;
            size--;
            return true;
        } 

        LinkedListElement temp = first;
        while (temp.next != null){
            if (temp.next.data.equals(data)){
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Removes the i-th element of the list and returns its data
     * @param i - the index of the data to be returned
     * @return the data of of the i-th element, or on error null
     */
    public String remove(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        
        LinkedListElement e;

        if (i == 0){
            e = first;
            first = e.next;
        } else {
            LinkedListElement temp = first;
            for (int j = 0; j < i - 1; j++){
                temp = temp.next;
            }
            e = temp.next;
            temp.next = e.next;
        }
        e.next = null;
        size--;
        return e.data;
    }
    
    /** 
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() { return size; }
    
    /**
     * Returns true if the LinkedList contains no elements
     * @return true if this list contains no elements, 
     * otherwise false
     */
    public boolean isEmpty() { return size()==0; }

    /** Returns the element at the specified position in this list. 
     * @param index -  index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException - if the index is out of
     *  range (index < 0 || index >= size())
     */
    public String get(int index) {
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        LinkedListElement temp = first;
        for (int j = 0; j < index; j++){
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Returns a string representation of this LinkedList which
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
}
