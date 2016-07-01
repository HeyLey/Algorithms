package part1.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

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

        int a[][] = new int[8][8];

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                a[i][j] = nextInt();
            }
        }

        int[][] maxT = new int[8][8];

        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int max = Integer.MIN_VALUE;
                if (i < 7) {
                    max = Math.max(max, -maxT[i + 1][j] + a[i + 1][j]);
                }
                if (j < 7) {
                    max = Math.max(max, -maxT[i][j + 1] + a[i][j + 1]);
                }
                if (i < 7 && j < 7) {
                    max = Math.max(max, -maxT[i + 1][j + 1] + a[i + 1][j + 1]);
                }
                maxT[i][j] = max;
                if (i == 7 && j == 7) {
                    maxT[i][j] = 0;
                }
            }
        }

        System.out.print(maxT[0][0]);

    }
}