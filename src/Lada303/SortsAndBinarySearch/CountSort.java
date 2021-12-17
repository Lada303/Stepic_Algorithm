package Lada303.SortsAndBinarySearch;
/*
Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.
 */

import java.util.Arrays;
import java.util.Scanner;

public class CountSort {
    private static final int M = 10;

    public void run()  {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arraySimpleNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            arraySimpleNumbers[i] = sc.nextInt();
        }
        countSort1(arraySimpleNumbers);

    }
    //вариант 1 - сортировка на месте, когда неважна стабильность
    private void countSort1(int[] arraySN) {
        //System.out.println(Arrays.toString(arraySN));
        int[] arrayCountNumber = new int[M + 1];
        for (int k : arraySN) {
            arrayCountNumber[k]++;
        }
        //System.out.println(Arrays.toString(arrayCountNumber));
        int j = 0;
        for (int i = 0; i <= M; i++) {
            while (arrayCountNumber[i] != 0) {
                arraySN[j] = i;
                j++;
                arrayCountNumber[i]--;
            }
        }
        //System.out.println(Arrays.toString(arrayCountNumber));
        for (int k : arraySN) {
            System.out.print(k + " ");
        }
        System.out.println();
    }

    //вариант 2 - предложенный в лекции, но он с доп массивом
    // он важен когда нужна стабильная сортировка
    private void countSort2(int[] arraySN) {
        int[] arrayCountNumber = new int[11];
        for (int j : arraySN) {
            arrayCountNumber[j]++;
        }
        for (int i = 1; i <= 10; i++) {
            arrayCountNumber[i] = arrayCountNumber[i] + arrayCountNumber[i - 1];
        }
        int[] arraySNSorted = new int[arraySN.length];
        for (int i = arraySN.length - 1; i >= 0; i--) {
            arraySNSorted[arrayCountNumber[arraySN[i]] - 1] = arraySN[i];
            arrayCountNumber[arraySN[i]]--;
        }
        System.out.println(Arrays.toString(arraySNSorted));
    }
}
