public class BSTTest {
    public static void main(String[] args) {
        BST bst = new BST();

        // Test: Baum ist leer
        System.out.println("Ist Baum leer? " + bst.isEmpty()); // true

        // Elemente einfügen
        bst.insert(50);
        

        // Test: Baum ist nicht leer
        System.out.println("Ist Baum leer? " + bst.isEmpty()); // false

        // Test: inOrder Traversal (soll sortierte Ausgabe sein)
        System.out.println("InOrder: " + bst.inOrderTraversal());  // [20 30 40 50 60 70 80 ]

        // Test: Elemente suchen
        System.out.println("Enthält 40? " + bst.isElement(40)); // true
        System.out.println("Enthält 100? " + bst.isElement(100)); // false

        // Test: getMin
        System.out.println("Minimum: " + bst.getMin()); // 20

        // Elemente entfernen
        bst.remove(20);  // Blatt entfernen
        System.out.println("InOrder nach Entfernen von 20: " + bst.inOrderTraversal()); // [30 40 50 60 70 80 ]

        bst.remove(30);  // Knoten mit einem Kind entfernen
        System.out.println("InOrder nach Entfernen von 30: " + bst.inOrderTraversal()); // [40 50 60 70 80 ]

        bst.remove(50);  // Wurzel mit zwei Kindern entfernen
        System.out.println("InOrder nach Entfernen von 50: " + bst.inOrderTraversal()); // [40 60 70 80 ]

        bst.remove(10);
        System.out.println("InOrder nach Entfernen von 10: " + bst.inOrderTraversal());

        // Test Traversals
        System.out.println("PreOrder: " + bst.preOrderTraversal());
        System.out.println("PostOrder: " + bst.postOrderTraversal());
    }
}