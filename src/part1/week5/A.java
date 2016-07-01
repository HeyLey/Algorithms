package part1.week5;

import java.io.*;

/**
 * Created by leyla on 11/10/15.
 */
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
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();

        long f0 = 1;
        long f1 = 1;
        long res = 0;

        for (int i = 2; i <= n; i++) {
            res = f0 + f1;
            f0 = f1;
            f1 = res;
        }

        if (n == 0 || n == 1) {
            res = 1;
        }

        out.println(res);
        out.close();
    }
}
