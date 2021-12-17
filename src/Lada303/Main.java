package Lada303;

import Lada303.SortsAndBinarySearch.CountSort;

public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        new CountSort().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime-startTime+" ms");
    }


}
