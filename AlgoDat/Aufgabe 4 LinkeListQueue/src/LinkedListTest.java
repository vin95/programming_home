public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insert("A", 0);
        list.insert("B", 1);
        list.insert("C", 2);
        list.insert("D", 1);  // Liste: [A, D, B, C]

        System.out.println("List: " + list);  // [A, D, B, C]

        System.out.println("Remove by index (2): " + list.remove(2));  // B
        System.out.println("List after remove: " + list);  // [A, D, C]
        System.out.println("Size: " + list.size());  // 3

        System.out.println("Remove by value (D): " + list.remove("D"));  // true
        System.out.println("List after remove: " + list);  // [A, C]
        System.out.println("Size: " + list.size());  // 2

        System.out.println("Get index 1: " + list.get(1));  // C
        System.out.println("Size: " + list.size());  // 2
        System.out.println("Is empty? " + list.isEmpty());  // false
    }
}
