public class SearchTree {

    public static class BinaryTree {
        private String value; //STring mit den Zahlen
        private BinaryTree left; //linker Teilbaum
        private BinaryTree right; //rechts Teilbaum

        // Constructor
        public BinaryTree(String value) {
            this.value = value; //Wert des Knotens
            this.left = null; //linker Sohn
            this.right = null; //rechter Sohn
        }

        //Neunen Knoten einfügen mit dem Wert dder Übergeben wird
        public static BinaryTree bin(BinaryTree left, String value, BinaryTree right) {
            BinaryTree tree = new BinaryTree(value);
            tree.left = left;
            tree.right = right;
            return tree;
        }

        //AXIOME

        //Linker Teilbaum lef t(bin(x, b, y)) = x
        public static BinaryTree left(BinaryTree tree) {
            return tree.left;
        }

        // Rechter Teilbaum right(bin(x, b, y)) = y
        public static BinaryTree right(BinaryTree tree) {
            return tree.right;
        }

        //Gibt den Wert des Knotens zurück value(bin(x, b, y)) = b
        public static String value(BinaryTree tree) {
            return tree.value;
        }

        //Guckt ob der Baum leer ist is_empty(empty) = true bzw is_empty(bin(x, b, y)) = f alse
        public static boolean is_empty(BinaryTree tree) {
            return tree == null;
        }
    }

    //Wurzel des Baums
    private static BinaryTree root;

    //Einfügen
    public static void insert(String key) {
        if (root == null) {
            root = new BinaryTree(key);
        } else {
            insertRec(root, key);
        }
    }

    // Reckursive insert funktion
    private static void insertRec(BinaryTree node, String key) {
        if (key.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new BinaryTree(key);
            } else {
                insertRec(node.left, key);
            }
        } else if (key.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new BinaryTree(key);
            } else {
                insertRec(node.right, key);
            }
        }
    }

    //Entfernen
    public static void delete(String key) {
        root = deleteRec(root, key);
    }

    // Rekurisve delete funktion
    private static BinaryTree deleteRec(BinaryTree node, String key) {
        if (node == null) {
            return null;
        }
        //Beispiel für Löschen in der Vorlesung
        if (key.compareTo(node.value) < 0) { //Wenn der Wert kleiner ist als der Wert des aktuellen Knotens, wird er links eingefügt
            node.left = deleteRec(node.left, key);
        } else if (key.compareTo(node.value) > 0) { //Wenn der Wert größer ist als der Wert des aktuellen Knotens, wird er rechts eingefügt
            node.right = deleteRec(node.right, key);
        } else { //Wenn der Wert gleich ist wie der Wert des aktuellen Knotens, wird er gelöscht
            if (node.left == null) { //Wenn der linke Teilbaum leer ist, wird der rechte Teilbaum zurückgegeben
                return node.right;
            } else if (node.right == null) { //Wenn der rechte Teilbaum leer ist, wird der linke Teilbaum zurückgegeben
                return node.left;
            }

            node.value = minValue(node.right); //Der kleinste Wert des rechten Teilbaums wird gesucht
            node.right = deleteRec(node.right, node.value); //Der kleinste Wert des rechten Teilbaums wird gelöscht
        }

        return node;
    }

    //Gibt den kleinsten Wert des Baums zurück (für delete)
    private static String minValue(BinaryTree node) {
        String minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    //modifizieren
    public static void modify(String key, String newValue) {
        delete(key);
        insert(newValue);
    }

    //asuageben
    public static void printTree() {
        printTreeRec(root);
    }

    //Rekursive print funktion
    private static void printTreeRec(BinaryTree node) {
        if (node == null) {
            return;
        }

        int height = getHeight(node);
        String nodeDetails = node.value + "(h" + height + ")";
        String leftChild = (node.left != null) ? node.left.value + "(h" + getHeight(node.left) + ")" : "null";
        String rightChild = (node.right != null) ? node.right.value + "(h" + getHeight(node.right) + ")" : "null";
        System.out.println(nodeDetails + " : " + leftChild + ", " + rightChild);

        printTreeRec(node.left);
        printTreeRec(node.right);
    }

    //gibt die Höhe des Baums zurück
    private static int getHeight(BinaryTree node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //e Inorder-Traversierung
    public static void inorderTraversal() {
        inorderTraversalRec(root);
    }

    //rekursive Inorder-Traversierung
    private static void inorderTraversalRec(BinaryTree node) {
        if (node == null) {
            return;
        }

        inorderTraversalRec(node.left);
        System.out.println(node.value);
        inorderTraversalRec(node.right);
    }



    //main
    public static void main(String[] args) {
            //Binärer Suchbaum
            BinaryTree tree = BinaryTree.bin(
                    BinaryTree.bin(
                            new BinaryTree("B"),
                            "A",
                            new BinaryTree("D")
                    ),
                    "C",
                    new BinaryTree("E")
            );

            // Perform binary tree operations
            System.out.println("Left subtree: " + BinaryTree.value(BinaryTree.left(tree))); // Output: B
            System.out.println("Right subtree: " + BinaryTree.value(BinaryTree.right(tree))); // Output: E
            System.out.println("Root value: " + BinaryTree.value(tree)); // Output: C
            System.out.println("Is tree empty? " + BinaryTree.is_empty(tree)); // Output: false
            System.out.println("Is left subtree empty? " + BinaryTree.is_empty(BinaryTree.left(tree))); // Output: false
            System.out.println("Is right subtree empty? " + BinaryTree.is_empty(BinaryTree.right(tree))); // Output: false

            System.out.println();

            //Suchbaum Werte einfügen
            SearchTree.insert("6");
            SearchTree.insert("3");
            SearchTree.insert("9");
            SearchTree.insert("1");
            SearchTree.insert("5");
            SearchTree.insert("7");
            SearchTree.insert("10");

            //Ausgaben
            System.out.println("Anfang:");
            SearchTree.printTree();
            System.out.println();

            SearchTree.delete("10");
            System.out.println("Nach entfernen von '10':");
            SearchTree.printTree();
            System.out.println();

            SearchTree.modify("7", "8");
            System.out.println("Nach modifizieren von '7' zu '8':");
            SearchTree.printTree();
            System.out.println();

            System.out.println("Inorder-Traversierung:");
            SearchTree.inorderTraversal();
    }

}
