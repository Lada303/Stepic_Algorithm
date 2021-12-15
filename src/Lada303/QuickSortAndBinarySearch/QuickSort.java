package Lada303.QuickSortAndBinarySearch;
/*
В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков
и точек на прямой, соответственно. Следующие n строк содержат по два целых числа
ai и bi , где ai < bi — координаты концов отрезков.
Последняя строка содержит m целых чисел — координаты точек.
Все координаты не превышают 10^8 по модулю. Точка считается принадлежащей отрезку,
если она находится внутри него или на границе. Для каждой точки в порядке появления
во вводе выведите, скольким отрезкам она принадлежит.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {

    public void run() throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("SectionsAndPoints.txt"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arrayStartPointSegments = new int[n];
        int[] arrayEndPointSegments = new int[n];
        for (int i = 0; i < n; i++) {
            arrayStartPointSegments[i] = sc.nextInt();
            arrayEndPointSegments[i] = sc.nextInt();
        }
        quickSort(arrayStartPointSegments, 0, n - 1);
        quickSort(arrayEndPointSegments, 0, n - 1);
        int indexInArraySPS;
        int indexInArrayEPS;
        int point;
        for (int i = 0; i < m; i++) {
            point = sc.nextInt();
            if (point < arrayStartPointSegments[0]
                    || point > arrayEndPointSegments[arrayEndPointSegments.length - 1]) {
                System.out.print(0 + " ");
                continue;
            }
            indexInArraySPS = searchIndexInArraySPS(point, arrayStartPointSegments);
            if (point <= arrayEndPointSegments[0]) {
                System.out.print((indexInArraySPS + 1) + " ");
                continue;
            }
            indexInArrayEPS = searchIndexInArrayEPS(point, arrayEndPointSegments);
            System.out.print((indexInArraySPS + 1) - (indexInArrayEPS) > 0 ?
                    (indexInArraySPS + 1) - (indexInArrayEPS) + " " : "0 ");
        }
    }

    private int searchIndexInArraySPS(int point, int[] arr) {
        int pointNew;
        int index;
        if (point > arr[arr.length - 1]) {
            return arr.length - 1;
        } else {
            index = binarySearch(point, arr);
            if (arr[index] <= point) {
                pointNew = arr[index];
                while (index + 1 != arr.length && arr[index + 1] == pointNew) {
                    index++;
                }
            } else {
                while (index - 1 >= 0 && arr[index] > point) {
                    index--;
                }
            }
        }
        return index;
    }

    private int searchIndexInArrayEPS(int point, int[] arr) {
        int pointNew;
        int index;
        if (point == arr[arr.length - 1]) {
            index = arr.length - 1;
        } else {
            index = binarySearch(point, arr);
        }
        if (arr[index] >= point) {
            pointNew = arr[index];
            while (index - 1 != 0 && arr[index - 1] == pointNew) {
                index--;
            }
        } else {
            while (index + 1 != arr.length && arr[index] < point) {
                index++;
            }
        }
        return index;
    }

    private void quickSort(int[] arr, int left, int right) {
        int[] middle;
        while (left < right) {
            middle = partition(arr, left, right);
            if (middle[0] - left < right - middle[1]) {
                if (middle[0] - left > 1 )quickSort(arr, left, middle[0] - 1);
                left = middle[1] + 1;
            } else {
                quickSort(arr, middle[1] + 1, right);
                right = middle[0] - 1;
            }
        }
    }

    private int[] partition(int[] arr, int left, int right) {
        Random random = new Random();
        int[] middle = new int[2];
        int randomX = random.nextInt(right - left) + left;
        int valueX = arr[randomX];
        arr[randomX] = arr[left];
        arr[left] = valueX;
        int y = left;
        int z = left;
        int cache;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] <= valueX) {
                y++;
                cache = arr[i];
                arr[i] = arr[y];
                arr[y] = cache;
                if (arr[y] < valueX){
                    cache = arr[z];
                    arr[z] = arr[y];
                    arr[y] = cache;
                    z++;
                }
            }
        }
        middle[0] = z;
        middle[1] = y;
        return  middle;
    }

    private int binarySearch(int intK, int[] arrayN) {
        int left = 0;
        int right = arrayN.length-1;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (arrayN[middle] == intK) {
                return middle;
            } else if (arrayN[middle] > intK) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return middle;
    }

}
