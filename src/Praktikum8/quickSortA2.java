package Praktikum8;


import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class quickSortA2 {

    private static boolean logging = false; //logging
    private static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void quickSort(String[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void sortChoose(String[] arr) {
        //gucken, ob das Array kleiner als 10 ist
        if (arr.length <= 10) {
            //wenn ja, dann insertion sort
            insertionSort(arr);
            log("Insertion Sort is used");
        } else {
            //wenn nein, dann quicksort
            quickSort(arr);
            log("Quick Sort is used");
        }
    }

    //insertion sort
    public static void insertionSort(String[] array) {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            String key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                log("Elemente getauscht: " + array[j + 1] + " und " + array[j]);
                j--;
            }

            array[j + 1] = key;
        }
    }

    private static void quickSort(String[] arr, int left, int right) {
        if (left < right) {
            if (right - left <= 2) {
                if (arr[left].compareTo(arr[right]) > 0) {
                    String temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    log("Elemente getauscht: " + arr[left] + " und " + arr[right]);
                }
            } else {
                int pivotIndex = (left + right) / 2; // Pivot in der Mitte des Subarrays auswählen
                String pivot = arr[pivotIndex];
                log("Pivot ausgewählt: " + pivot);

                int i = left;
                int j = right;

                while (i <= j) {
                    while (arr[i].compareTo(pivot) < 0) {
                        i++;
                    }

                    while (arr[j].compareTo(pivot) > 0) {
                        j--;
                    }

                    if (i <= j) {
                        String tempString = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tempString;
                        log("Elemente getauscht: " + arr[i] + " und " + arr[j]);
                        i++;
                        j--;
                    }
                }

                quickSort(arr, left, j);
                quickSort(arr, i, right);
            }
        }
    }


    private static void logcheck(String msg) {
        if (msg.equals("yes")) {
            logging = true;
            logHandler();
        } else {
            logging = false;
        }
    }
    private static void log(String message) {
        if (logging) {
            log.info(message);
        }
    }

    public static void logHandler() {
        Logger root = Logger.getLogger("");
        FileHandler txt = null;
        try{
            txt = new FileHandler("Log.txt");
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Hey");
        }
        txt.setFormatter(new SimpleFormatter());
        root.addHandler(txt);
    }

    public static void main(String[] args) {
        String[] arr = {"PC", "Köln", "Vodafone", "O2", "Kleeblatt", "Marvin", "Mattis", "ADS", "HSBI", "Minden", "Bielefeld"};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Wollen sie Logging einschalten?");
        System.out.println("Wenn ja dann geben sie yes ein, sonst geben sie etwas anders ein");
        Scanner sc = new Scanner(System.in);
        logcheck(sc.next());
        sortChoose(arr);
        System.out.println("Sortiertes Array: " + Arrays.toString(arr));
    }
}