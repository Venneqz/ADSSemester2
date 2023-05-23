public class Suchbaum {
    public static void main(String[] args) {
        //Neuen Baum erstellen
        Suchbaum suchbaum = new Suchbaum();
        Baum baum = new Baum();
        System.out.println("Beim einfügen: Wenn der Wert schon vorhanden ist, wird er nicht nochmal eingefügt");
        System.out.println("Wenn sie keinen Knoten angeben, wird der Wert 0 eingefügt");
        //Werte in den Baum einfügen
        suchbaum.einfuegen(baum, "6", "null");
        suchbaum.einfuegen(baum, "3", "6");
        suchbaum.einfuegen(baum, "9", "6");
        suchbaum.einfuegen(baum, "1", "3");
        suchbaum.einfuegen(baum, "5", "3");
        suchbaum.einfuegen(baum, "7", "9");
        //Baum ausgeben
        suchbaum.ausgeben(baum);
        System.out.println("");
        //Baum löschen
        suchbaum.loeschen(baum, "7");
        //Baum ausgeben
        suchbaum.ausgeben(baum);
        System.out.println("");

        //Baum modifizieren
        suchbaum.modifizieren(baum, "6", "7");
        //Baum ausgeben
        suchbaum.ausgeben(baum);
        System.out.println("");


    }

    /**
     * Suchbaum für Stings mit Opeartionen: Einfügen, Löschen, Modifizieren, Baumausgabe
     * **/

    //Knoten
    class Knoten {
        String wert;
        Knoten links;
        Knoten rechts;
    }

    //Baum
    static class Baum {
        Knoten wurzel;
    }

    //Einfügen
    public void einfuegen(Baum b, String s, String KnotenWert) {
        //Wenn der Baum leer ist, wird der Wert als Wurzel eingefügt
        if (b.wurzel == null) {
            b.wurzel = new Knoten();
            b.wurzel.wert = s;
            b.wurzel.links = null;
            b.wurzel.rechts = null;
        } else {
            //Wenn der Baum nicht leer ist, wird der Wert an der richtigen Stelle eingefügt
            Knoten k = b.wurzel;
            while (k != null) {
                //Wenn der Wert schon vorhanden ist, wird er nicht nochmal eingefügt
                if (k.wert.equals(s)) {
                    System.out.println("Der Wert ist schon vorhanden");
                    break;
                }
                //Wenn der Wert kleiner ist als der Wert des aktuellen Knotens, wird er links eingefügt
                if (s.compareTo(k.wert) < 0) {
                    if (k.links == null) {
                        k.links = new Knoten();
                        k.links.wert = s;
                        k.links.links = null;
                        k.links.rechts = null;
                        break;
                    } else {
                        k = k.links;
                    }
                }
                //Wenn der Wert größer ist als der Wert des aktuellen Knotens, wird er rechts eingefügt
                if (s.compareTo(k.wert) > 0) {
                    if (k.rechts == null) {
                        k.rechts = new Knoten();
                        k.rechts.wert = s;
                        k.rechts.links = null;
                        k.rechts.rechts = null;
                        break;
                    } else {
                        k = k.rechts;
                    }
                }
            }
        }
    }

    //Löschen
    public void loeschen(Baum b, String s) {
        if (b.wurzel != null) {
            loeschen1(b.wurzel, s);
        }
    }

    public void loeschen1(Knoten k, String s) {
        System.out.println(k);
        System.out.println(k.wert);
        System.out.println(k.links);
        System.out.println(k.rechts);
        System.out.println("");
        //Wenn der Wert des aktuellen Knotens gleich dem zu löschenden Wert ist, wird der Knoten gelöscht
        if (k.wert.equals(s)) {
            //Wenn der Knoten keine Kinder hat, wird er gelöscht
            if (k.links == null && k.rechts == null) {
                k = null;
                System.out.println(k);
            }
            //Wenn der Knoten nur ein Kind hat, wird er gelöscht und das Kind wird an seine Stelle gesetzt
            else if (k.links != null && k.rechts == null) {
                k = k.links;
            } else if (k.links == null && k.rechts != null) {
                k = k.rechts;
            }
            //Wenn der Knoten zwei Kinder hat, und der rechte Sohn ein Blatt ist, wird der Knoten gelöscht und der rechte Sohn wird an seine Stelle gesetzt
            //ansonsten wird der Knoten mit dem am weitesten links stehenden Blatts des rechten Teilbaums verstauscht und entfernt
            else if (k.links != null && k.rechts != null) {
                if (k.rechts.links == null && k.rechts.rechts == null) {
                    k = k.rechts;
                } else {
                    Knoten k1 = k.rechts;
                    while (k1.links.links != null) {
                        k1 = k1.links;
                    }
                    k.wert = k1.links.wert;
                    k1.links = null;
                }
            }
        }
        //Wenn der Wert des aktuellen Knotens kleiner ist als der zu löschende Wert, wird der linke Teilbaum durchsucht
        else if (s.compareTo(k.wert) < 0) {
            if (k.links != null) {
                loeschen1(k.links, s);
            }
        }
        //Wenn der Wert des aktuellen Knotens größer ist als der zu löschende Wert, wird der rechte Teilbaum durchsucht
        else if (s.compareTo(k.wert) > 0) {
            if (k.rechts != null) {
                loeschen1(k.rechts, s);
            }
        }
    }

    //Modifizieren
    public void modifizieren(Baum b, String s, String neu) {
        if (b.wurzel != null) {
            modifizieren1(b.wurzel, s, neu);
        }
    }

    public void modifizieren1(Knoten k, String s, String neu) {
        if (k.wert.compareTo(s) < 0) {
            if (k.links != null) {
                if (k.links.wert.equals(s)) {
                    k.links.wert = neu;
                } else {
                    modifizieren1(k.links, s, neu);
                }
            }
        } else {
            if (k.rechts != null) {
                if (k.rechts.wert.equals(s)) {
                    k.rechts.wert = neu;
                } else {
                    modifizieren1(k.rechts, s, neu);
                }
            }
        }
    }

    //Baumausgabe
    public void ausgeben(Baum b) {
        //Baumartige Ausgabe mit einrücken
        if (b.wurzel != null) {
            ausgeben1(b.wurzel, 0);
        }
    }

    public void ausgeben1(Knoten k, int i) {
        //ausgabe mit einrückung von oben nach unten
        if (k.rechts != null) {
            ausgeben1(k.rechts, i + 1);
        }
        for (int j = 0; j < i; j++) {
            System.out.print("   ");
        }
        System.out.println(k.wert);
        if (k.links != null) {
            ausgeben1(k.links, i + 1);
        }
    }

}
