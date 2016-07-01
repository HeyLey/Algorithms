package part1.week10;

/* Дан ориентированный граф.
В нём необходимо найти расстояние от одной заданной вершины до другой.
Формат входных данных
В первой строке входного файла содержится три натуральных числа N, S и F
(1 <= S,F <= N <= 100) — количество вершин в графе и номера начальной и конечной вершин соответственно.
Далее в N строках задана матрица смежности графа.
Если значение в j-м элементе i-й строки равно 1, то в графе есть направленное ребро из вершины i в вершину j.

Формат выходных данных
В единственной строке должно находиться минимальное расстояние от начальной вершины до конечной.
Если пути не существует, выведите 0.

input:
4 4 3
0 1 1 1
1 0 1 0
1 1 0 0
1 0 0 0

output:
2
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A {

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        int[][] g = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        IntList l = new IntList(n);
        IntList next = new IntList(n);
        l.add(s);
        visited[s] = true;
        dist[s] = 0;
        int len = 1;
        while (l.size > 0) {
            for (int j = 0; j < l.size; j++) {
                int u = l.data[j];
                for (int v = 0; v < n; v++) {
                    if (g[u][v] == 0) {
                        continue;
                    }
                    if (visited[v]) {
                        continue;
                    }
                    visited[v] = true;

                    dist[v] = len;
                    next.add(v);
                }
            }
            len += 1;
            IntList tmp = l;
            l = next;
            next = tmp;
            next.size = 0;
        }

        System.out.println(dist[f]);
    }

    static class IntList {
        int[] data;
        int size;

        IntList(int n) {
            data = new int[n];
            size = 0;
        }

        public void add(int v) {
            data[size] = v;
            size++;
        }
    }

    static class MyReader {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        MyReader(InputStream in) {
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
}