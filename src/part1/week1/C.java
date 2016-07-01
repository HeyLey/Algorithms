package part1.week1;

import java.util.Scanner;

/**
 * посчитать sum from k=1 to k^2<=n floor(n/k^2)
 */
public class C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long sum = 0;
        double m = StrictMath.sqrt(n);

        for (long k = 1; k <= m; k++) {
            sum = sum + n / k / k;
        }
        System.out.println(sum);
    }
}
