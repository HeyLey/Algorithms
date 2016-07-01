package part1.week1;

/**
 * Числа Каталана определяются следущим образом:
 * 1. C_0 = 1
 * 2. C_n = sum(from i=0 to n-1 ) [ C_i * C_{n-i-1} ]
 * Ваша задача — посчитать C_n mod m.
 */

import java.io.*;

public class G {
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

        int n = nextInt();
        int m = nextInt();
        long[] array = new long[1001];
        array[0] = 1;

        if (n == 0 && m == 1) {
          out.println(0);
        } else {

            for (int k = 1; k <= n; k++) {
                long c = 0;
                for (int i = 0; i < k; i++) {
                    c += (array[i] * array[k - i - 1]) % m;
                }
                array[k] = c % m;

            }
            out.println(array[n]);
        }
        out.close();
    }
}