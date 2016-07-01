package part1.week8;

/*
Дан неориентированный граф. Проверьте, является ли он деревом.
Формат входных данных
В первой строке входного файла заданы через пробел два целых числа 𝑛 и 𝑚 — количество
вершин и рёбер в графе, соответственно (1 6 𝑛 6 100). В следующих 𝑚 строках заданы рёбра;
𝑖-я из этих строк содержит два целых числа 𝑢𝑖 и 𝑣𝑖 через пробел — номера концов 𝑖-го ребра
(1 6 𝑢𝑖
, 𝑣𝑖 6 𝑛). Граф не содержит петель и кратных рёбер.
Формат выходных данных
В первой строке выходного файла выведите “YES”, если граф является деревом, и “NO” в
противном случае.
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class D {

    public static void main(String[] args) throws IOException {
        MyRd in = new MyRd(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            list.get(i).add(j);
            list.get(j).add(i);
        }

        boolean visited[] = new boolean[n];

        dfs(list, visited, 0);

        boolean connectivity = true;

        for (boolean b : visited) {
            if (!b) {
                connectivity = false;
            }
        }

        if (connectivity == true && ((n - m) == 1)) {
            out.println("YES");
        } else {
            out.println("NO");
        }

        out.close();
    }

    public static void dfs(List<List<Integer>> list, boolean[] visited, int v) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;

        for (int i : list.get(v)) {
            dfs(list, visited, i);
        }
    }
}


class MyRd{
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    MyRd(InputStream in) {
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

