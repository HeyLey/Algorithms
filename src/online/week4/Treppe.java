package online.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Treppe {
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
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        int[] max = new int[n + 1];

        max[0] = 0;
        max[1] = a[0];
        for (int i = 2; i <= n; i++) {
            max[i] = Math.max(max[i - 1], max[i - 2]) + a[i - 1];
        }

        System.out.println(max[n]);
    }
}