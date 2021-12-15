package Lada303.Huffman;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generate {

    public static void main(String[] args) throws FileNotFoundException {
        new Generate().run();
    }

    private void run() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("input.txt");
        int length = 10000;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            printWriter.print((char)('a' + random.nextInt(26)));
        }
        printWriter.close();
    }
}
