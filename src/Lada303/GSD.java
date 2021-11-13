package Lada303;

import java.math.BigInteger;

public class GSD {

    public long gsd(long a, long b) {
        while (true) {
            System.out.println(a+" "+b);
            if (a == 0) return b;
            if (b == 0) return a;
            if (a >= b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
    }

    public BigInteger gsd(BigInteger a, BigInteger b) {
        while (true) {
          //  System.out.println(a+" "+b);
            if (a.equals(BigInteger.ZERO)) return b;
            if (b.equals(BigInteger.ZERO)) return a;
            if (a.compareTo(b) >= 0) {
                a = a.mod(b);
            } else {
                b = b.mod(a);
            }
        }
    }

    protected long euclidGCD(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        return (a >= b) ? euclidGCD(a%b, b) : euclidGCD(a, b%a);
    }

    public void run() {

        BigInteger a = new BigInteger("523487523495384563084563075403754035463075403754370543084530754304503854");
        BigInteger b = new BigInteger("38564375437543745123541254273154253254345374538257437543845354");
        System.out.println(gsd(a,b));
       // a.gcd(b);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new GSD().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime-startTime+" ms");

    }

}
