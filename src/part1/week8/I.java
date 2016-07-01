package part1.week8;

/* Дан ориентированный невзвешенный граф без петель и кратных рёбер.
Необходимо определить есть ли в нём циклы, и если есть, то вывести любой из них.
Формат входных данных
В первой строке входного файла находятся два натуральных числа N и M
(1 <= N <= 100 000, M <= 100 000) — количество вершин и рёбер в графе соответственно.
Далее в M строках перечислены рёбра графа.
Каждое ребро задаётся парой чисел — номерами начальной и конечной вершин соответственно.
Формат выходных данных
Если в графе нет цикла, то вывести «NO», иначе — «YES»
и затем перечислить все вершины в порядке обхода цикла.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class I {

    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        Stack stack = new Stack(n + 1);

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            list.get(i).add(j);
        }

        int visited[] = new int[n];

        for (int v = 0; v < n; v++) {
            if (visited[v] != 0) {
                continue;
            }
            int cycle = dfs(list, visited, v, stack);

            if (cycle == 1) {
                out.println("YES");
                ArrayList<Integer> c = new ArrayList<>();
                int first = stack.pop();
                while (true) {
                    int t = stack.pop();
                    c.add(t + 1);
                    if (t == first) {
                        break;
                    }
                }
                Collections.reverse(c);
                for (int i = 0; i < c.size(); i++) {
                    out.print(c.get(i) + " ");
                }

                out.close();
                return;
            }
        }

        out.println("NO");
        out.close();
    }

    public static int dfs(List<List<Integer>> list, int[] visited, int v, Stack stack) {
        if (visited[v] == 1) {
            stack.push(v);
            return 1;
        }
        if (visited[v] == 2) {
            return 0;
        }

        visited[v] = 1;
        stack.push(v);

        for (int i : list.get(v)) {
            if (dfs(list, visited, i, stack) != 0) {
                return 1;
            }
        }
        visited[v] = 2;
        stack.pop();
        return 0;
    }
}

class Stack {
    int size;
    int[] data;

    Stack(int capacity) {
        data = new int[capacity];
    }

    void push(int value) {
        data[size++] = value;
    }

    int pop() {
        return data[--size];
    }
}


class RdMy {
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    RdMy(InputStream in) {
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