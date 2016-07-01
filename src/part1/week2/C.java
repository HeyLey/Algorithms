package part1.week2;

import java.io.*;
import java.util.Arrays;

/**
 * Даны два вектора: v1 = (x1, x2, . . . , xn) и v2 = (y1, y2, . . . , yn).
 * Скалярным произведением этих векторов называется значение, вычисляемое по формуле:
 * x1*y1 + x2*y2 + . . . + xn*yn.
 Разрешено переставлять координаты каждого из векторов любым образом. Выберите та-
 кие их перестановки, чтобы скалярное произведение двух полученных векторов было минимальным и выведите его значение.
 1 <= n <= 800          -100000 <= xi, yi, <= 100000

 Формат входных данных
 Первая строка входного файла содержит единственное целое число t — количество наборов тестовых данных.
 Далее следуют сами наборы, по три строки в каждом.
 Первая строка тестового набора содержит единственное целое число n.
 Две следуюшие строки содержат по n целых чисел, задающих координаты соответствующего вектора, каждая.

 Формат выходных данных
 Для каждого набора выведите строку с номером этого набора и ответом на задачу —
 значением минимального скалярного произведения. Следуйте формату, указанному в примере.
 */

public class C {
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
        PrintWriter out = new PrintWriter(System.out);

        int t = nextInt();

        long res = 0;

        for (int i = 0; i < t; i++) {
            int n = nextInt();
            int[] a = new int[n];
            int[] b = new int[n];

            for (int j = 0; j < n; j++) {
                a[j] = nextInt();
            }
            for (int j = 0; j < n; j++) {
                b[j] = nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            int s = 0;

            for (int k = n - 1; k >= 0; k--) {
                res += (long)a[s] * (long)b[k];
                s++;
            }

            out.println("Case #" + (i + 1) + ": " + res);
            res = 0;
        }

        out.close();
    }

}