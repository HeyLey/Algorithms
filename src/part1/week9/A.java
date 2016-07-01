package part1.week9;

/*
В заданном корневом дереве найдите вершины, максимально удалённые от корня.
Расстоянием между вершинами считается количество рёбер в пути.
Формат входных данных
В первой строке задано n — количество вершин в дереве (1 <= n <= 100).
В следующих n-1 строках заданы вершины, являющиеся предками вершин 2, 3, . . ., n.
Вершина 1 является корнем дерева.
Формат выходных данных
В первой строке выведите максимальное расстояние от корня до остальных вершин дерева.
Во второй строке выведите, сколько вершин дерева находятся от корня на таком расстоянии.
В третьей строке выведите номера этих вершин через пробел в порядке возрастания.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class A {

    public static void main(String[] args) throws IOException {
        ReaderMy in = new ReaderMy(System.in);

        int n = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int k = 1; k < n; k++) {
            int i = in.nextInt() - 1;
            list.get(k).add(i);
            list.get(i).add(k);
        }


        boolean visited[] = new boolean[n];

        int[] dist = new int[n];


        dfs(list, visited, 0, dist, 0);

        int max = 0;
        int number = 0;

        for (int i = 0; i < n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                number = 1;
            } else if (dist[i] == max) {
                number++;
            }
        }

        System.out.println(max);
        System.out.println(number);

        for (int i = 0; i < n; i++) {
            if (dist[i] == max) {
                System.out.print((i + 1) + " ");
            }
        }

        System.out.println();

    }

    public static void dfs(List<List<Integer>> list, boolean[] visited, int v, int[] dists, int d) {
        if (visited[v]) {
            return;
        }

        dists[v] = d;
        visited[v] = true;

        for (int i : list.get(v)) {
            dfs(list, visited, i, dists, d + 1);
        }
    }


    static class ReaderMy {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        ReaderMy(InputStream in) {
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