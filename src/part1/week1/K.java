package part1.week1;
/**
 Даны две квадратных матрицы из целых неотрицательных чисел и целое число m.
 Посчитайте произведение матриц по модулю m.
 Формат входных данных
 На первой строке n (1 <= n <= 700), m (1 <= m <= 10^9).
 Следующие n строк содержат по n целых чисел от 0 до (m − 1) — матрица A.
 Следующие n строк содержат по n целых чисел от 0 до (m − 1) — матрица B.
 Выведите n строк по n целых чисел от 0 до (m − 1) в каждой — матрица ( A × B ) mod m.
 */
import java.io.*;

public class K {
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

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];

        long cons = Long.MAX_VALUE >> 1;

        for (int i = 0; i < n; i++) {       //a
            for (int j = 0; j < n; j++) {
                a[i][j] = nextInt();
            }
        }

        for (int i = 0; i < n; i++) {       //b
            for (int j = 0; j < n; j++) {
                b[j][i] = nextInt();
            }
        }


        for (int i = 0; i < n; i++) {
            int[] s = a[i];
            for (int l = 0; l < n; l++) {
                int[] k = b[l];
                long el = 0;
                for (int j = 0; j < n; j++) {
                    el += (long)s[j] * (long)k[j];
                    if (el > cons) {
                        el = el % m;
                    }
                }
                el = el % m;
                c[i][l] = (int)el;
                out.print(c[i][l] + " ");
            }
            out.println();
        }

        out.close();
    }
}