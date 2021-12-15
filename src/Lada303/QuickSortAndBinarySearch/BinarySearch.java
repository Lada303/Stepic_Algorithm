package Lada303.QuickSortAndBinarySearch;
/*
В первой строке даны целое число n где 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел,
не превышающих 10^9, в порядке возрастания, во второй — целое число k где 1≤k≤10^5 и k натуральных
чисел b1,... bk, не превышающих 10^9. Для каждого i от 1 до k необходимо вывести индекс j  1≤j≤n,
для которого A[j]=bi, или −1, если такого j нет.
 */

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
