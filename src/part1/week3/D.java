package part1.week3;

import java.io.*;

public class D {
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

        int[] ropes = new int[n];

        int max = 0;

        for (int i = 0; i < n; i++) {
            ropes[i] = nextInt();
            if (ropes[i] > max) {
                max = ropes[i];
            }
        }

        int left = 0;
        int right = max;

        while (left < right) {
            int center = (left + right + 1) / 2;

            int number = 0;

            for (int i = 0; i < n; i++) {
                number += ropes[i] / center;
            }
            if (number >= k) {
                left = center;
            } else {
                right = center - 1;
            }
        }

        System.out.println(left);

        out.close();
    }
}