package Lada303;


import java.util.Arrays;
import java.util.Scanner;
/*
Наибольшие подпоследовательности
 */

public class LongestSubsequences {

    public void run() {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("SectionsAndPoints.txt"));
        int n = sc.nextInt();
        int[] arraySequence = new int[n];
        for (int i = 0; i < n; i++) {
            arraySequence[i] = sc.nextInt();
        }
        sc.close();
        //System.out.println(Arrays.toString(arraySequence));
        //longestCMS(arraySequence);
        longestNonIS(arraySequence);

    }
    /*
    Задача на программирование: наибольшая последовательнократная подпоследовательность
    Дано целое число 1≤n≤10^3 и массив A[1…n] натуральных чисел, не превосходящих 2⋅10^9.
    Выведите максимальное 1≤k≤n, для которого найдётся подпоследовательность 1<=i_1<i_2...<i_k<n
    длины k, в которой каждый элемент делится на предыдущий нацело.
    */
    //это O(n^2)
    private void longestCMS(int[] arraySequence) {
        int[] arrayLengthLMS = new int[arraySequence.length];
        int max = 1;
        for (int i = 0; i < arraySequence.length; i++) {
            arrayLengthLMS[i] = 1;
            for (int j = 0; j < i ; j++) {
                if (arraySequence[i] % arraySequence[j] == 0 && arrayLengthLMS[i] < arrayLengthLMS[j] + 1) {
                    arrayLengthLMS[i] = arrayLengthLMS[j] + 1;
                    max = Math.max(max, arrayLengthLMS[i]);
                }
            }
        }
        //System.out.println(Arrays.toString(arrayLengthLMS));
        System.out.println(max);
    }
/*
Дано целое число 1≤n≤10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9.
Найдите наибольшую невозрастающую подпоследовательность в A. В первой строке выведите её длину k, во второй
— её индексы 1<i_1<i_2<...<i_k ≤n (таким образом, A[i_1]≥A[i_2]≥…≥A[i_n]).
*/
    //это O(n(log n))
    private void longestNonIS(int[] arrSequence) {
        //нахождение длины LnIS и ее последнего элемента
        int[] arrLengthLnIS = new int[arrSequence.length];
        int maxLengthLnIS = 0;
        //это нужно для уменьшение времени поиска
        int[][] arrMaxValueOfSequenceLengthInd = new int[arrSequence.length + 1][2];
        arrMaxValueOfSequenceLengthInd[maxLengthLnIS][0] = 1000000000;
        for (int i = 0; i < arrSequence.length; i++) {
            for (int j = maxLengthLnIS; j >= 0 ; j--) {
                if (arrSequence[i] <= arrMaxValueOfSequenceLengthInd[j][0]) {
                    arrLengthLnIS[i] = j + 1;
                    maxLengthLnIS = Math.max(maxLengthLnIS, arrLengthLnIS[i]);
                    if (arrSequence[i] > arrMaxValueOfSequenceLengthInd[arrLengthLnIS[i]][0]) {
                        arrMaxValueOfSequenceLengthInd[arrLengthLnIS[i]][0] = arrSequence[i];
                        arrMaxValueOfSequenceLengthInd[arrLengthLnIS[i]][1] = i;
                    }
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(arrLengthLnIS));
        //System.out.println(Arrays.deepToString(arrMaxValueOfSequenceLengthInd));
        System.out.println(maxLengthLnIS);
        //Восстановление ответа
        int element = arrMaxValueOfSequenceLengthInd[maxLengthLnIS][0];
        int indexElementInSequence = arrMaxValueOfSequenceLengthInd[maxLengthLnIS][1];
        int length = maxLengthLnIS - 1;
        int[] indexLnIS= new int[maxLengthLnIS];
        indexLnIS[maxLengthLnIS - 1] = indexElementInSequence + 1;
        for (int i = indexElementInSequence - 1; i >= 0 ; i--) {
            if (arrLengthLnIS[i] == length && arrSequence[i] >= element) {
                indexLnIS[length - 1] = i + 1;
                length--;
                element = arrSequence[i];
            }
        }
        //System.out.println(Arrays.toString(indexLnIS));
        for (int i = 0; i < indexLnIS.length; i++) {
            System.out.print(indexLnIS[i] + " ");
        }
        System.out.println();
    }
}
