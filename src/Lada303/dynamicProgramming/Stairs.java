package Lada303.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;
/*
Даны число n≤10^2 ступенек лестницы и целые числа -10^4≤a1,…,an≤10^4, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки),
каждый раз поднимаясь на одну или две ступеньки.
*/
public class Stairs {
    public void run() {
        Scanner sc = new Scanner(System.in);
        int countStep = sc.nextInt();
        int[] stepsValue = new int[countStep];
        for (int i = 0; i < countStep; i++) {
            stepsValue[i] = sc.nextInt();
        }
        //System.out.println(Arrays.toString(stepsValue));
        System.out.println(maxSumOfSteps(stepsValue));
    }

    private int maxSumOfSteps(int[] steps) {
        if (steps.length == 1) {
            return steps[0];
        }
        int max1 = steps[0];
        int max2 = Math.max(steps[1], max1 + steps[1]);
        int max;
        for (int i = 2; i < steps.length; i++) {
            max = Math.max(steps[i] + max1, steps[i] + max2);
            max1 = max2;
            max2 = max;
        }
        return max2;
    }
}
