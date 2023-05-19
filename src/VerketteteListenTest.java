public class VerketteteListenTest {
    public static void main(String[] args) {
        String s = "Wow", p = "krass", d = "genial", f = "Toll";

        VerketteteListen l = new VerketteteListen();
        VerketteteListen.ElementL e = l.addElement(s,null);

        l.addElement(d,null);

        l.addElement(f,e);

        l.addElement(p,null);

        l.printList();

        l.deleteElement(e);

        l.printList();
    }
}
