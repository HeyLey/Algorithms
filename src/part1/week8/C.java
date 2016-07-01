package part1.week8;

/*
В этой задаче требуется проверить, что граф является связным,
то есть что из любой вершины можно по рёбрам этого графа попасть в любую другую.
В графе могут существовать петли и кратные ребра.
Формат входных данных
В первой строке входного файла заданы числа N и M через пробел —
количество вершин и рёбер в графе, соответственно (1 <= N <=  100, 0 <= M <= 10 000).
Следующие M строк содержат по два числа u_i и v_i через пробел (1 <= u_i, v_i <= N);
каждая такая строка означает, что в графе существует ребро между вершинами u_i и v_i.
Формат выходных данных
Выведите “YES”, если граф является связным, и “NO” в противном случае.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class C {

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
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

        for (boolean b : visited) {
            if (!b) {
                out.println("NO");
                out.close();
                System.exit(0);
            }
        }

        out.println("YES");
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


class MyReader {
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