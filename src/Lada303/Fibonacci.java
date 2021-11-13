package Lada303;

import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

    // Число Фибаначи
    public static BigInteger nFibonacci(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1 || n == 2) return BigInteger.ONE;
        BigInteger lastN_2;
        BigInteger lastN_1 = BigInteger.ONE;
        BigInteger lastN = BigInteger.ONE;
        for (int i = 3; i <= n; i++) {
            lastN_2 = lastN_1;
            lastN_1 = lastN;
            lastN = lastN_1.add(lastN_2);
        }
        return lastN;
    }

    // Последняя цыфра Числа Фибаначи
    public static int lastN_nFibonacci(int n) {
        if (n == 1 || n == 2) return 1;
        int arrLastN_2;
        int arrLastN_1 = 1;
        int arrLastN = 1;
        for (int i = 3; i <= n; i++) {
            arrLastN_2 = arrLastN_1;
            arrLastN_1 = arrLastN;
            arrLastN = (arrLastN_1 + arrLastN_2) % 10;
        }
        return arrLastN;
    }

    // огромное число Фибоначи N по модулю M
    public static int modFibonacciNbyM(long n, int m) {
        if (n == 1 || n == 2) return 1;
        int[] arrModFibonacciNbyM = new int[m * 6];
        arrModFibonacciNbyM[1] = 1;
        arrModFibonacciNbyM[2] = 1;
        int periodPizano=0;
        for (int i = 3; i < m * 6; i++) {
            arrModFibonacciNbyM[i] = (arrModFibonacciNbyM[i - 1] + arrModFibonacciNbyM[i - 2]) % m;
            if (arrModFibonacciNbyM[i] == 1 && arrModFibonacciNbyM[i - 1] == 1 && arrModFibonacciNbyM[i - 2] == 0) {
                periodPizano = i-2;
                break;
            }
        }
        int index = Math.floorMod(n, periodPizano);
        return arrModFibonacciNbyM[index];
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        System.out.println(modFibonacciNbyM(n, m));
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new Fibonacci().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime-startTime+" ms");
    }

}
