package part1.week1;

import java.io.*;

/**
 * Даны две матрицы над Z. Найти их произведение.
 */
public class E {
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
        int k = nextInt();

        int[][] a = new int[n][m];
        int[][] b = new int[m][k];
        int[][] c = new int[n][k];

        for (int i = 0; i < n; i++) {
            int s = 0;
            while(s < m) {
                a[i][s] = nextInt();
                s++;
            }
        }

        for (int i = 0; i < m; i++) {
            int s = 0;
            while(s < k) {
                b[i][s] = nextInt();
                s++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int l = 0; l < k; l++) {
                for (int j = 0; j < m; j++) {
                    c[i][l] += a[i][j] * b[j][l];
                }
                out.print(c[i][l] + " ");
            }
            out.println();
        }

        out.close();
    }
}
