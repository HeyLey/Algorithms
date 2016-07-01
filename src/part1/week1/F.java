package part1.week1;

import java.io.*;

/**
 * Даны два целых числа A и B (1 <= A,B <= 100). Найдите два таких целых числа X и Y,
 что выполнено равенство AX + BY = 1.
 */
public class F {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        long r = a % b;
        return gcd(b, r);
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int a = nextInt();
        int b = nextInt();

        int x = 0;
        int y = 0;

        int i = 1;
        int j = 0;
        int k = 0;
        int l = 1;

        if (gcd(a, b) != 1) {
            out.print("0 0");
        } else {
            while (a != 0 && b != 0) {
                if (a >= b) {
                    a = a - b;
                    i = i - k;
                    j = j - l;
                } else {
                    b = b - a;
                    k = k - i;
                    l = l - j;
                }
            }
            if (a != 0) {
                x = i;
                y = j;
            } else {
                x = k;
                y = l;
            }
            out.print(x + " " + y);
        }

        out.close();
    }
}
