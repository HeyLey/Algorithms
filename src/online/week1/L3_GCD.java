package online.week1;

import java.util.Scanner;

public class L3_GCD {

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        long r = a % b;
        return gcd(b, r);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        long a = s.nextLong();
        long b = s.nextLong();
        long res = gcd(a,b);

        System.out.println(res);
    }
}
