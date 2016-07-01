package part1.week4;

/**
 * Вам нужно научиться отвечать на запрос “сумма чисел на отрезке”.
 * Массив не меняется. Запросов много. Отвечать на 1 запрос следует за О(1).
 * <p/>
 * Формат входных данных:
 * Размер массива — n и числа x, y, a_0,
 * порождающие массив a:  a_i = (x * a_(i−1) + y) mod 2^16
 * Далее следуеют количество запросов m и числа z, t, b_0,
 * порождающие массив b: b_i = (z * b_(i-1) + t) mod 2^30, c_i = b_i mod n.
 * i-й запрос — найти сумму на отрезке от min(c_(2i), c_(2i+1)) до max(c_(2i), c_(2i+1)) в массиве a.
 * Ограничения: 1 <= n <= 10^7, 0<= m <= 10^7
 * Все числа целые от 0 до 2^16
 * t может быть −1.
 * <p/>
 * Формат выходных данных:
 * Выведите сумму всех сумм.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class A {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int x = nextInt();
        int y = nextInt();
        int a0 = nextInt();

        int m = nextInt();
        int z = nextInt();
        int t = nextInt();
        int b0 = nextInt();

        long[] sum = new long[n+1];

        for (int i = 0; i < n; i++) {
            sum[i+1] = a0;
            a0 = (x * a0 + y) & 0xffff;
        }

        long s = 0;
        for (int i = 0; i < n; i++) {
            s += sum[i + 1];
            sum[i + 1] = s;
        }

        long result = 0;

        for (int i = 0; i < m; i++) {
            int b1 = b0 % n;
            b0 = (z * b0 + t) & (1073741824 - 1);
            int b2 = b0 % n;
            b0 = (z * b0 + t) & (1073741824 - 1);

            int l = Math.min(b1, b2);
            int r = Math.max(b1, b2);

            result += sum[r+1] - sum[l];
        }

        System.out.println(result);
    }

}