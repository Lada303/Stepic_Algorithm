package Lada303;

import java.util.Scanner;

public class BinarySearch {

    public void run() {
        Scanner sc = new Scanner(System.in);
        int arrayLength = sc.nextInt();
        int[] arrayNumbers = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arrayNumbers[i] = sc.nextInt();
        }
        int countK = sc.nextInt();
        int intK;
        for (int i = 0; i < countK; i++) {
            intK = sc.nextInt();
            binarySearch(intK, arrayNumbers);
        }
    }

    private void binarySearch(int intK, int[] arrayN) {
        int left = 0;
        int right = arrayN.length-1;
        int middle;
        while (left<=right) {
            middle = (left + right) / 2;
            if (arrayN[middle] == intK) {
                System.out.print(middle+1 + " ");
                return;
            } else if (arrayN[middle] > intK) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        System.out.print("-1 ");
    }
}
