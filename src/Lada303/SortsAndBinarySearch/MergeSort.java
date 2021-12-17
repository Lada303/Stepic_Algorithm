package Lada303.SortsAndBinarySearch;
/*
Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа, не
превосходящие 10^9. Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j].
(Такая пара элементов называется инверсией массива. Количество инверсий в массиве является в некотором
смысле его мерой неупорядоченности: например, в упорядоченном по неубыванию массиве инверсий нет вообще,
а в массиве, упорядоченном по убыванию, инверсию образуют каждые два элемента.)
*/
import java.util.*;

public class MergeSort {

    static long countInversion;

    public void run() {
        Scanner sc = new Scanner(System.in);
        int arrayLength = sc.nextInt();
        long[] arrayNumbers = new long[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arrayNumbers[i] = sc.nextLong();
        }
       // countInversion = 0;
        System.out.println(Arrays.toString(mergeSortIterative(arrayNumbers)));
        //System.out.println(countInversion);

    }

    //Итерактивная сортировка слиянием с использованием очереди
    //Чтобы посчитать правильно колчисетво инверсий надо в начало очереди добавить {0}
    //чтобы общее количество элементов очереди было 2 в степени
    public static Long[] mergeSortIterative(long[] arr) {
        Queue<Long[]> queue= new ArrayDeque<>();
        for (long l : arr) {
            Long[] elem = new Long[1];
            elem[0] = l;
            queue.add(elem);
        }
        while (queue.size() >1) {
            queue.add(myMerge(queue.remove(), queue.remove()));
        }
        return queue.remove();
    }
    public static Long[] myMerge(Long[] arrLeft, Long[] arrRight) {
        Long[] arrResult = new Long[arrLeft.length + arrRight.length];
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i < arrResult.length; i++) {
            if (countLeft == arrLeft.length && countRight != arrRight.length) {
                arrResult[i] = arrRight[countRight];
                countRight++;
                continue;
            }
            if (countLeft != arrLeft.length && countRight == arrRight.length) {
                arrResult[i] = arrLeft[countLeft];
                countLeft++;
                continue;
            }
            if (arrLeft[countLeft] <= arrRight[countRight]) {
                arrResult[i] = arrLeft[countLeft];
                countLeft++;
            } else {
                arrResult[i] = arrRight[countRight];
                countRight++;
            }
        }
        return arrResult;
    }


    // Васриант с использованием нового массива
    public static long[] mergeSortRecursive(long[] arr, int left, int right) {
        int middle;
        if (left < right) {
            middle = (left + right) / 2;
            return myMerge(mergeSortRecursive(arr, left, middle),
                                    mergeSortRecursive(arr, middle + 1, right));
        }
        long[] arrElement = new long[1];
        arrElement[0] = arr[left];
        System.out.println(Arrays.toString(arrElement));
        return arrElement;
    }
    public static long[] myMerge(long[] arrLeft, long[] arrRight) {
        long[] arrResult = new long[arrLeft.length + arrRight.length];
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i < arrResult.length; i++) {
            if (countLeft == arrLeft.length && countRight != arrRight.length) {
                arrResult[i] = arrRight[countRight];
                countRight++;
                continue;
            }
            if (countLeft != arrLeft.length && countRight == arrRight.length) {
                arrResult[i] = arrLeft[countLeft];
                countLeft++;
                continue;
            }
            if (arrLeft[countLeft] <= arrRight[countRight]) {
                arrResult[i] = arrLeft[countLeft];
                countLeft++;
            } else {
                arrResult[i] = arrRight[countRight];
                countInversion += arrLeft.length - countLeft;
                countRight++;
               // System.out.println(countInversion);
            }
        }
      //  System.out.println(Arrays.toString(arrResult));
        return arrResult;
    }

    //Простая сортировка вставками - T(n * n)
    // слишком долго, не проходит Time limit exceeded
    public static int simpleInsertionSort(long... arr) {
        long cache;
        int countInversion=0;
        for (int i = 0; i < arr.length-1; i++) {
            int c=0;
            for (int j = i; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    cache = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = cache;
                    countInversion++;
                    c++;
                }
                if (arr[arr.length-1-j] < arr[arr.length-1-j-1]) {
                    cache = arr[arr.length-1-j];
                    arr[arr.length-1-j] = arr[arr.length-1-j-1];
                    arr[arr.length-1-j-1] = cache;
                    countInversion++;
                    c++;
                }
            }
            if (c == 0) {
               // System.out.println("i= "+i);
               // System.out.println(Arrays.toString(arr));
               // System.out.println(countInversion);
                return countInversion;
            }
        }
        return countInversion;
    }

}
