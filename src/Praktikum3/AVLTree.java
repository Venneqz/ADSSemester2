package Praktikum3;

public class AVLTree {
    private static AVLNode root;

    private static class AVLNode {
        String key; //String mit key
        int height; //Höhe des Knotens
        AVLNode left; //linker Teilbaum
        AVLNode right; //rechter Teilbaum

        AVLNode(String key) {
            this.key = key; //key wird gesetzt
            this.height = 0; //Höhe wird auf 0 gesetzt
            this.left = null; //linker Teilbaum wird auf null gesetzt
            this.right = null; //rechter Teilbaum wird auf null gesetzt
        }
    }

    //Einfügen
    public static void insert(String key) {
        if (root == null) {
            root = new AVLNode(key);
        } else {
            insertRec(root, key);
        }
    }

    //Rekursive insert Funktion
    private static AVLNode insertRec(AVLNode node, String key) {
        if (node == null) {
            return new AVLNode(key); //Wenn der Knoten null ist wird ein neuer Knoten mit dem key erstellt
        }

        if (key.compareTo(node.key) < 0) { //Wenn der key kleiner ist als der Wert des Knotens
            node.left = insertRec(node.left, key); //Rekursiver Aufruf mit dem linken Teilbaum
        } else if (key.compareTo(node.key) > 0) { //Wenn der key größer ist als der Wert des Knotens
            node.right = insertRec(node.right, key); //Rekursiver Aufruf mit dem rechten Teilbaum
        } else {
            //Wenn der key gleich dem Wert des Knotens ist, wird nichts gemacht (duplikate nicht möglich)
            return node;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1; //Höhe des Knotens wird gesetzt

        int balance = getBalance(node); //Balance des Knotens wird gesetzt

        //Rotationen werden durchgeführt wenn der Baum nicht balanciert ist
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    //Entfernen
    public static void delete(String key) {
        root = deleteRec(root, key);
    }

    //Rekursive delete Funktion
    private static AVLNode deleteRec(AVLNode node, String key) {
        if (node == null) {
            return null;
        }
        //Beispiel für Löschung in der Vorlesung
        if (key.compareTo(node.key) < 0) { //Wenn der key kleiner ist als der Wert des Knotens
            node.left = deleteRec(node.left, key); //Rekursiver Aufruf mit dem linken Teilbaum
        } else if (key.compareTo(node.key) > 0) { //Wenn der key größer ist als der Wert des Knotens
            node.right = deleteRec(node.right, key); //Rekursiver Aufruf mit dem rechten Teilbaum
        } else { //Wenn der key gleich dem Wert des Knotens ist
            if (node.left == null || node.right == null) { //Wenn der Knoten ein Blatt ist
                node = (node.left != null) ? node.left : node.right; //Wenn der Knoten einen linken Teilbaum hat, wird der linke Teilbaum zurückgegeben, sonst der rechte
            } else { //Wenn der Knoten kein Blatt ist
                AVLNode successor = findMinimum(node.right); //Nachfolger wird gesucht
                node.key = successor.key; //key des Knotens wird auf den key des Nachfolgers gesetzt
                node.right = deleteRec(node.right, successor.key); //Rekursiver Aufruf mit dem rechten Teilbaum
            }
        }

        if (node == null) {
            return null;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1; //Höhe des Knotens wird aktualisiert

        int balance = getBalance(node); //Balance des Knotens wird gesetzt

        //Rotationen werden durchgeführt wenn der Baum nicht balanciert ist
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    //modifizieren
    public static void modify(String key, String newValue) {
        //löschen und neu einfügen
        delete(key);
        insert(newValue);
    }

    //Preorder Traversal für ausgabe
    public static void printTree() {
        printTreeRec(root);
    }

    //Rekursive Preorder Traversal Funktion
    private static void printTreeRec(AVLNode node) {
        if (node == null) {
            return;
        }
        //Details des Knotens werden gespeichert
        String nodeDetails = node.key + "(h" + node.height + ", b" + getBalance(node) + ")";
        //Details des linken und rechten Teilbaums werden gespeichert
        String leftChild = (node.left != null) ? node.left.key + "(h" + node.left.height + ", b" + getBalance(node.left) + ")" : "null";
        String rightChild = (node.right != null) ? node.right.key + "(h" + node.right.height + ", b" + getBalance(node.right) + ")" : "null";
        //Details werden ausgegeben
        System.out.println(nodeDetails + " : " + leftChild + ", " + rightChild);


        //Rekursive Aufrufe mit dem linken und rechten Teilbaum
        printTreeRec(node.left);
        printTreeRec(node.right);
    }

    //Höhe des Knotens
    private static int getHeight(AVLNode node) {
        if (node == null) {
            return -1;
        }

        return node.height;
    }

    //Balance des Knotens
    private static int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        //Balance = Höhe des linken Teilbaums - Höhe des rechten Teilbaums
        return getHeight(node.left) - getHeight(node.right);
    }

    //Rotation nach rechts
    private static AVLNode rotateRight(AVLNode node) {
        //Knoten und linker Teilbaum werden gespeichert
        AVLNode newRoot = node.left;
        AVLNode subtree = newRoot.right;

        //Rotation
        newRoot.right = node;
        node.left = subtree;

        //Höhen werden aktualisiert
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // Performs a left rotation on the given node in the AVL tree
    private static AVLNode rotateLeft(AVLNode node) {
        //Knoten und rechter Teilbaum werden gespeichert
        AVLNode newRoot = node.right;
        AVLNode subtree = newRoot.left;

        //Rotation
        newRoot.left = node;
        node.right = subtree;

        //Höhen werden aktualisiert
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // Finds the node with the minimum key value in the AVL tree
    private static AVLNode findMinimum(AVLNode node) {
        //Solange es einen linken Teilbaum gibt, wird der linke Teilbaum gespeichert
        while (node.left != null) {
            node = node.left; //Der linke Teilbaum wird gespeichert
        }

        return node;
    }



    public static void main(String[] args) {
        AVLTree.insert("6");
        AVLTree.insert("3");
        AVLTree.insert("9");
        AVLTree.insert("1");
        AVLTree.insert("5");
        AVLTree.insert("7");
        AVLTree.insert("10");

        System.out.println("Anfang:");
        AVLTree.printTree();
        System.out.println();

        AVLTree.delete("10");
        System.out.println("Nach entfernen von '10':");
        AVLTree.printTree();
        System.out.println();

        AVLTree.modify("7", "8");
        System.out.println("Nach modifizieren von '7' zu '8':");
        AVLTree.printTree();
    }
}
