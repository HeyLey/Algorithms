package online.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MinSubS {
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

        int[] max = new int[n];
        int[] prev = new int[n];


        for (int i = 0; i < n; i++) {
            max[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (a[i] <= a[j] && max[i] < max[j] + 1) {
                    max[i] = max[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int maxValue = 0;
        int p = 0;

        for (int i = 0; i < n; i++) {
            if (max[i] > maxValue) {
                maxValue = max[i];
                p = i;
            }
        }

        int size = 0;

        int[] seq = new int[n];

        while (p > -1) {
            seq[size] = p + 1;
            size++;
            p = prev[p];
        }

        System.out.println(maxValue);

        for (int i = size-1; i >= 0; i--) {
            System.out.print(seq[i] + " ");
        }
        System.out.println();
    }
}