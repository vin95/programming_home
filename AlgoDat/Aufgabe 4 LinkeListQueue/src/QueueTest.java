public class QueueTest {
    public static void main(String[] args) {
        Queue queue = new Queue();

        System.out.println("Ist die Queue leer? " + queue.isEmpty()); // true
        System.out.println("Aktuelle Queue: " + queue);               // []

        queue.append("Apfel");
        queue.append("Banane");
        queue.append("Kirsche");

        System.out.println("Nach dem Hinzufügen: " + queue);          // [Apfel, Banane, Kirsche]
        System.out.println("Ist die Queue leer? " + queue.isEmpty()); // false

        System.out.println("Entferntes Element: " + queue.remove());  // Apfel
        System.out.println("Queue danach: " + queue);                 // [Banane, Kirsche]

        System.out.println("Entferntes Element: " + queue.remove());  // Banane
        System.out.println("Entferntes Element: " + queue.remove());  // Kirsche
        System.out.println("Entferntes Element: " + queue.remove());  // null

        System.out.println("Ist die Queue leer? " + queue.isEmpty()); // true
        System.out.println("Aktuelle Queue: " + queue);               // []
    }
}
