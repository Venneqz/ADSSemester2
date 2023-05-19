import java.util.Scanner;

public class VerketteteListen {
    public static class ElementL {
        private Object element;
        private ElementL next;

        public ElementL(Object o) {
            element = o;
            next = null; //da noch kein nächstes Element existiert
        }
    }

        public static ElementL head; //head der Linked List erstellen (Referenz! Kein Objekt!)

        public void VerketteteListen() {
            head = null; //da noch kein Element existiert
        }

        public static ElementL createNewList() {
            ElementL newElement = new ElementL(null);
            return newElement;
        }

        public static ElementL deleteList(ElementL head) {
            //check if list is empty
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
                return null;
            }
            ElementL current = head;
            ElementL next;
            while (current != null) {
                next = current.next;
                current = null;
                current = next;
            }
            head = null;
            return head;
        }

        public static ElementL addElement(Object o, ElementL pred) {
            //check if list is empty
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
                return null;
            }
            ElementL newElement = new ElementL(o);
            if (pred == null) {
                newElement.next = head;
                head = newElement;
            } else {
                newElement.next = pred.next;
                pred.next = newElement;
            }
            return newElement;
        }


        public static void deleteElement(ElementL current) {
            //check if list is empty
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
                return;
            }
            //check if element doesnt exist
            if (current.element == null) {
                System.out.println("Element existiert nicht");
                return;
            }
            //find predesessor
            ElementL pred = head;
            while (pred.next != current) {
                pred = pred.next;
            }
            if (pred == null) {
                head = head.next;
            } else {
                pred.next = pred.next.next;
            }
        }

        public static void searchElement(String name) {
            //check if list is empty
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
                return;
            }
            //check if element doesnt exist
            if (name == null) {
                System.out.println("Element existiert nicht");
                return;
            }
            // Element suchen und Vorgänger und Nachfolger anzeigen
            ElementL current = head;
            ElementL prev = null;
            ElementL next = null;
            while (current != null) {
                if (current.element == null) {
                    System.out.println("Element existiert nicht");
                    break;
                }
                else if (current.element.equals(name)) {
                    if (prev == null) {
                        System.out.println("Vorgänger: Nicht vorhanden");
                    } else {
                        System.out.println("Vorgänger: " + prev.element);
                    }
                    if (current.next == null) {
                        System.out.println("Nachfolger: Nicht vorhanden");
                    } else {
                        System.out.println("Nachfolger: " + current.next.element);
                    }
                    break;
                }
                prev = current;
                current = current.next;
                if (current != null) {
                    next = current.next;
                }
            }
        }

        public static void printList() {
            //check if list is empty
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
            } else {
                ElementL current = head;
                while (current != null) {
                    System.out.println(current.element);
                    current = current.next;
                }
            }
        }

        public static void modifyElement(Object o, ElementL element) {
            //check if list is empty or not created yet
            if (head == null) {
                System.out.println("Liste wurde noch nicht initialisiert");
            } else {
                //check if element doesnt exist
                if (element == null) {
                    System.out.println("Element existiert nicht");
                    return;
                }
                ElementL newElement = new ElementL(o);
                newElement.next = element.next;
                //find predesessor and change next to newElement
                ElementL pred = head;
                while (pred.next != element) {
                    pred = pred.next;
                }
                pred.next = newElement;
            }
        }

        public void userInterface(){
            Scanner scanner = new Scanner(System.in);
            String choice;

            while (true) {
                System.out.println("N = neue Liste anlegen");
                System.out.println("L = Liste löschen");
                System.out.println("Z = Liste zeigen");
                System.out.println("e = Element hinter einem anderen einfügen (bzw. in die leere Liste schreiben)");
                System.out.println("s = Element suchen und Vorgänger und Nachfolger anzeigen");
                System.out.println("l = Element löschen");
                System.out.println("m = Element modifizieren");
                System.out.println("q = Beenden");
                System.out.print("Was möchten Sie tun? ");
                choice = scanner.nextLine();
                VerketteteListen liste = new VerketteteListen();

                switch (choice) {
                    case "N":
                        head = createNewList();
                        System.out.println("Neue Liste angelegt.");
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "L":
                        head = deleteList(head);
                        System.out.println("Liste gelöscht.");
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "Z":
                        printList();
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "e":
                        System.out.print("Welches Element möchten Sie einfügen? ");
                        String element = scanner.nextLine();
                        System.out.print("Hinter welchem Element möchten Sie " + element + " einfügen? ");
                        System.out.println("Wenn sie " + element + " an den Anfang der Liste einfügen möchten, drücken Sie bitte Enter.");
                        String element2 = scanner.nextLine();
                        if (element2.equals("")) {
                            addElement(element, null);
                        } else {
                            ElementL current = head;
                            while (current != null) {
                                if (current.element == null) {
                                    System.out.println("Element existiert nicht");
                                    break;
                                }
                                if (current.element.equals(element2)) {
                                    addElement(element, current);
                                    break;
                                }
                                current = current.next;
                            }
                        }
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "s":
                        System.out.print("Geben Sie das Element ein, das Sie suchen möchten: ");
                        String data = scanner.nextLine();
                        if (data == null) {
                            System.out.println("Element existiert nicht");
                            break;
                        }
                        else {
                            searchElement(data);
                        }
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "l":
                        System.out.print("Geben Sie das Element ein, das Sie löschen möchten: ");
                        data = scanner.nextLine();
                        ElementL current = head;
                        while (current != null) {
                            if (current.element == null) {
                                System.out.println("Element existiert nicht");
                                break;
                            }
                            else if(current.element.equals(data)) {
                                deleteElement(current);
                                break;
                            }
                            current = current.next;
                        }
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "m":
                        System.out.print("Geben Sie das Element ein, das Sie modifizieren möchten: ");
                        data = scanner.nextLine();
                        System.out.print("Geben Sie das neue Element ein: ");
                        String newData = scanner.nextLine();
                        current = head;
                        while (current != null) {
                            if (current.element == null) {
                                System.out.println("Element existiert nicht");
                                break;
                            }
                            else if(current.element.equals(data)) {
                                modifyElement(newData, current);
                                break;
                            }
                            current = current.next;
                        }
                        System.out.println("");
                        System.out.println("");
                        break;
                    case "q":
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Ungültige Eingabe. Bitte versuchen Sie es erneut.");
                        break;
                }
            }
        }


        public static void main(String[] args) {
            VerketteteListen list = new VerketteteListen();
            list.userInterface();
        }
    }
