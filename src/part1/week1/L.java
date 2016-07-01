package part1.week1;

/**
 * Дано целое число n (1 <= n <= 10^12).
 * Нужно найти натуральные a, b, c : abc = n и при этом 2(ab+bc+ca) минимально.
 * Т.е. при фиксированном объеме минимимизировать площадь поверхности
 */

import java.io.*;

class L {
    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        long n = nextLong();
        int s = (int) Math.round(Math.cbrt(n));
        int k = (int) Math.round(Math.sqrt(n));

        int a = 0;
        int b = 0;

        long min = Long.MAX_VALUE;

        for (int x = 1; x <= s; x++) {
            if (n % x != 0) {
                continue;
            }
            for (int y = x; y <= (k / Math.sqrt(x)); y++) {
                long m = y * x;
                double z = (double) n / (double) m;
                if (z % 1 == 0) {
                    long res = (long) (2 * (m + y * z + x * z));
                    if (res < min) {
                        a = x;
                        b = y;
                        min = res;
                    }
                }

            }
        }

        out.println(min + " " + a + " " + b + " " + n/a/b);

        out.close();
    }
}