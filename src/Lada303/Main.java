package Lada303;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberN = sc.nextInt();

        long startTime = System.currentTimeMillis();
        GreedyAlg.nonRepeatingTerms(numberN);
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime-startTime+" ms");
    }


}
