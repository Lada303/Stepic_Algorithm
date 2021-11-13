package Lada303;

import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlg {

    static void nonRepeatingTerms(long number) {
        long term=1;
        long sum = 0;
        while (true) {
            if (number - sum > term * 2) {
                sum += term;
                term++;
            } else {
                break;
            }
        }
        System.out.println(term);
        for (int i = 1; i < term; i++) {
            System.out.print(i+" ");
        }
        System.out.println(number - sum);
    }


    static double knapsack (int weightKnapsack, int[]... itemsArray) {
        Arrays.sort(itemsArray, Comparator.comparingDouble(a -> (double) a[0]/ (double) a[1]));
       // Arrays.stream(itemsArray).map(Arrays::toString).forEach(System.out::print);
        double costKnapsack=0;
        for (int i = itemsArray.length-1; i >=0; i--) {
            if (itemsArray[i][1]<=weightKnapsack) {
                costKnapsack += itemsArray[i][0];
                weightKnapsack = weightKnapsack- itemsArray[i][1];
            } else {
                costKnapsack += (weightKnapsack/(double)itemsArray[i][1])*itemsArray[i][0];
                weightKnapsack=0;
            }
            if (weightKnapsack == 0) {break;}
        }
        return costKnapsack;
        //System.out.printf("%.3f", costKnapsack);
    }

     static void pointCover (long[]... sectionsArray) {
        Arrays.sort(sectionsArray, (o1, o2) -> {
            if (o1[0] > o2[0]) {return 1;}
            if (o1[0] == o2[0] && o1[1] > o2[1]) return 1;
            if (o1[0] == o2[0] && o1[1] == o2[1]) return 0;
            return -1;
        });
        // Arrays.stream(sectionArr)
        //      .sorted(Comparator.comparingLong(a -> a[0]))
        //      .map(Arrays::toString)
        //      .forEach(x -> System.out.print(x + " "));
        long[] pointArray = new long[sectionsArray.length];
        int counterPoint=0;
        long minRight;
        minRight = sectionsArray[0][1];
        for (long[] section : sectionsArray) {
            if (minRight > section[1]) {
                minRight = section[1];
                continue;
            }
            if (section[0] > minRight) {
                pointArray[counterPoint] = minRight;
                counterPoint++;
                minRight = section[1];
            }
        }
        pointArray[counterPoint]=minRight;
        counterPoint++;
        System.out.println(counterPoint);
        for (int i = 0; i < counterPoint; i++) {
            System.out.print(i == pointArray.length - 1 ? pointArray[i] + "\n" : pointArray[i] + " ");
        }
    }



    //сортировка сразу с двух сторон массива, вначале по первому элементу массива, потом по второму
    static void sortingArrayOfSectionByLeftAndRight (long[]... array) {
        long[] cash;
        int counter;
        for (int j = 0; j < array.length; j++) {
            counter=0;
            for (int i = j; i < array.length - 1-j; i++) {
                if (array[i][0] > array[i + 1][0]) {
                    cash = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = cash;
                    counter++;
                }
                if (array[i][0] == array[i+1][0] && array[i][1] > array[i+1][1]) {
                    cash = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = cash;
                    counter++;
                }
                if (array[array.length-1-i][0] < array[array.length-1-(i+1)][0]) {
                    cash = array[array.length-1-i];
                    array[array.length-1-i] = array[array.length-1-(i+1)];
                    array[array.length-1-(i+1)] = cash;
                    counter++;
                }
                if (array[array.length-1-i][0] == array[array.length-1-(i+1)][0]
                        && array[array.length-1-i][1] < array[array.length-1-(i+1)][1]) {
                    cash = array[array.length-1-i];
                    array[array.length-1-i] = array[array.length-1-(i+1)];
                    array[array.length-1-(i+1)] = cash;
                    counter++;
                }
            }
            if (counter == 0) {break;}
        }
    }

}
