package part1.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Посчитать sum (from k = 1 to k^2 <= n^2) floor(n / k^2)
 * 1 <= n <= 10^19
 *
 * Есть формула [n/k^2] = v.
 * Тебе нужно перебирать не k, а v.
 * И для каждого v вычислять то количество k, которое удовлетворяет приведенноё выше формуле.
 * Это будет равно sqrt(n / v) - sqrt(n / (v + 1)).
 * При этом перебирать нужно сверху вниз.
 * Это выгодно делать, пока данная разность больше 1.
 * Как только она становится равна 1 или 0 - переходишь к обычному перебору по k от 1 до sqrt(n / (v + 1))
 */

public class J {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        BigInteger n = new BigInteger(s);

        long k = 1;
        long k2 = 1;
        BigInteger sum = BigInteger.ZERO;

        while (k < 100) {
            BigInteger bigK2 = BigInteger.valueOf(k2);
            if (bigK2.compareTo(n) > 0) {
                break;
            }
            BigInteger p = n.divide(bigK2);
            sum = sum.add(p);

            k2 = k2 + 2 * k + 1;
            k++;
        }

        long na = n.divide(BigInteger.valueOf(16)).longValue();
        long nb = n.mod(BigInteger.valueOf(16)).longValue();

        long longSum = 0;

        while (true) {
            if (k2 / 16 >= na && k2 % 16 > nb) {
                break;
            }
            long p = na / k2 * 16 + (na % k2 * 16 + nb) / k2;
            if (k > 10000 && p < 100000) {
                break;
            }
            longSum = longSum + p;

            k2 = k2 + 2 * k + 1;
            k++;
        }

        long p = n.divide(BigInteger.valueOf(k2)).longValue();

        while (p > 0) {
            BigInteger next = n.divide(BigInteger.valueOf(p));

            long nextK = (long) Math.sqrt(next.doubleValue()) + 1;

            while (BigInteger.valueOf(nextK-1).pow(2).compareTo(next) > 0) {
                nextK--;
            }

            longSum += p * (nextK - k);
            k = nextK;
            p--;
        }

        System.out.println(sum.add(BigInteger.valueOf(longSum)));
    }
}
