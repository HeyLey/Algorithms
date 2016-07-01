package part1.week8;

/*
Простой неориентированный граф задан списком ребер,
выведите его представление в виде матрицы смежности.
Формат входных данных
Входной файл содержит числа N (1 <= N <= 100) — число вершин в графе
и M (1 <= M <= n(n-1)/2) — число ребер.
Затем следует M пар чисел — ребра графа.
Формат выходных данных
Выведите в выходной файл матрицу смежности заданного графа.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class B {

    public static void main(String[] args) throws IOException {
        MyRead in = new MyRead(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        int[][] matrix = new int[n][n];

        for (int k = 0; k < m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();

            matrix[i - 1][j - 1]++;
            matrix[j - 1][i - 1]++;

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }

        out.close();
    }
}


class MyRead {
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    MyRead(InputStream in) {
        this.in = new BufferedInputStream(in, bufSize);
    }

    int nextInt() throws IOException {
        int c;
        while ((c = nextChar()) <= 32)
            ;
        int x = 0, sign = 1;
        if (c == '-') {
            sign = -1;
            c = nextChar();
        }
        while (c >= '0') {
            x = x * 10 + (c - '0');
            c = nextChar();
        }
        return x * sign;
    }

    StringBuilder _buf = new StringBuilder();

    String nextWord() throws IOException {
        int c;
        _buf.setLength(0);
        while ((c = nextChar()) <= 32 && c != -1)
            ;
        if (c == -1)
            return null;
        while (c > 32) {
            _buf.append((char) c);
            c = nextChar();
        }
        return _buf.toString();
    }

    int bn = bufSize, k = bufSize;

    int nextChar() throws IOException {
        if (bn == k) {
            k = in.read(b, 0, bufSize);
            bn = 0;
        }
        return bn >= k ? -1 : b[bn++];
    }

    int nextNotSpace() throws IOException {
        int ch;
        while ((ch = nextChar()) <= 32 && ch != -1)
            ;
        return ch;
    }
}