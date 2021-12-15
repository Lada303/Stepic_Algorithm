package Lada303.QuickSortAndBinarySearch;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generate {

    private static final int COUNT_SECTIONS = 50000;
    private static final int COUNT_POINTS = 10000;
    private static final int MAX_POINT = 100000000;

    public static void main(String[] args) throws FileNotFoundException {
        new Generate().run();
    }

    private void run() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("SectionsAndPoints.txt");
        int length = 50000;
        printWriter.println(COUNT_SECTIONS + " " + COUNT_POINTS);
        Random random = new Random();
        int startSection, endSection;
        for (int i = 0; i <= length; i++) {
            startSection = random.nextInt(2 * MAX_POINT) - MAX_POINT;
            do {
                endSection = random.nextInt(2 * MAX_POINT) - MAX_POINT;
            }  while (startSection > endSection);
            printWriter.println(startSection + " " + endSection);
        }
        for (int i = 0; i <= length; i++) {
            printWriter.print((random.nextInt(2 * MAX_POINT) - MAX_POINT) + " ");
        }
        printWriter.println();
        printWriter.close();
    }
}
