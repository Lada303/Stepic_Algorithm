package Lada303.dynamicProgramming;

import java.util.*;

/*
У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
заменить x на 2x, 3x или x+1. По данному целому числу 1≤n≤10^5 определите минимальное число операций k,
необходимое, чтобы получить n из 1. Выведите k и последовательность промежуточных чисел.
 */
public class StrangeCalculator {
    int number;
    int[] counters;
    int minLengthSequence;

    public void run() {
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        counters = new int[number + 1];
        minLengthSequence = strangeCalcTD(number);
        System.out.println(minLengthSequence);
        //System.out.println(Arrays.toString(counters));
        sequenceOfIntermediateNumbers(number, minLengthSequence);
        System.out.println();
    }

    private int strangeCalcTD(int number) {
        if (number == 1) {
            counters[number] = 0;
            return 0;
        }
        if (counters[number] != 0) {
            return counters[number];
        }
        int counter;
        int c1, c2;
        if (number % 3 == 0) {
            counter = strangeCalcTD(number / 3);
        } else {
            c2 = (number & 1) == 0 ? strangeCalcTD(number / 2) : 10000000;
            c1 = strangeCalcTD(number - 1);
            counter = Math.min(c2, c1);
        }
        counters[number] = counter + 1;
        return counter + 1;
    }

    private void sequenceOfIntermediateNumbers(int intermediateNumber, int intermediateCounter) {
        if (intermediateNumber == 1) {
            System.out.print(1);
            return;
        }
        intermediateCounter--;
        if (intermediateNumber % 3 == 0 && counters[intermediateNumber / 3] == intermediateCounter) {
            sequenceOfIntermediateNumbers(intermediateNumber / 3 , intermediateCounter);
        } else if (intermediateNumber % 2 == 0 && counters[intermediateNumber / 2] == intermediateCounter) {
            sequenceOfIntermediateNumbers(intermediateNumber / 2, intermediateCounter);
        } else {
            sequenceOfIntermediateNumbers(intermediateNumber - 1, intermediateCounter);
        }
        System.out.print(" " + intermediateNumber);
    }
}
