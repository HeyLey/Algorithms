package part1.week3;

import java.io.*;

public class I {
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
        int k = nextInt();

        final int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        int min = 0;
        int max = a[a.length - 1] - a[0];

        while (min < max) {
            int mid = (max + min + 1) / 2;
            int last = a[0] - mid * 2;
            int cows = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] - last >= mid) {
                    last = a[i];
                    cows += 1;
                }
            }
            if (cows < k) {
                max = mid - 1;
            } else {
                min = mid;
            }
        }

        out.println(min);

        out.close();
    }
}
