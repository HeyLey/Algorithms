package part1.week1;

/**
 Перечислите все разбиения целого положительного числа N (1 <= N <= 40) на целые положительные слагаемые.
 Разбиения должны обладать следующими свойствами:
 1. Слагаемые в разбиениях идут в невозрастающем порядке.
 2. Разбиения перечисляются в лексикографическом порядке.
 */
import java.io.*;

public class H {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static void slug(int n, int[] array, int i, int max, PrintWriter out) {
        if(n == 0) {
            for (int j = 0; j < i; j++) {
                out.print(array[j] + " ");
            }
            out.println();
            return;
        }
        for (int j = 1; j <= max && j <= n; j++) {
            array[i] = j;
            slug(n - j, array, i + 1, j, out);
        }
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int[] array = new int[n];

        slug(n, array, 0, n, out);

        out.close();
    }
}

