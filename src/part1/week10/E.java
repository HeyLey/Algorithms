package part1.week10;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class E {

    public static void main(String[] args) throws IOException {

        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

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

        boolean[] visited = new boolean[n];

        int[] prev = new int[n];
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = -1;
        }

        IntList l = new IntList(n);
        IntList next = new IntList(n);
        l.add(0);
        visited[0] = true;
        int len = 0;
        while (l.size > 0) {
            for (int j = 0; j < l.size; j++) {
                int u = l.data[j];
                distances[u] = len;
                for (int v : list.get(u)) {
                    if (visited[v]) {
                        continue;
                    }
                    if (a[u] == a[v]) {
                        l.add(v);
                        prev[v] = u;
                        visited[v] = true;
                    }
                }
            }
            for (int j = 0; j < l.size; j++) {
                int u = l.data[j];
                for (int v : list.get(u)) {
                    if (visited[v]) {
                        continue;
                    }

                    prev[v] = u;
                    visited[v] = true;
                    next.add(v);
                }
            }
            len += 1;
            IntList tmp = l;
            l = next;
            next = tmp;
            next.size = 0;
        }

        PrintWriter out = new PrintWriter(System.out);

        if (distances[n-1] == -1) {
            out.println("impossible");
        } else {
            int p = n - 1;
            ArrayList<Integer> path = new ArrayList<>();
            path.add(p + 1);
            while (p != 0) {
                p = prev[p];
                path.add(p + 1);
            }
            out.println(distances[n - 1] + " " + path.size());

            for (int i = path.size() - 1; i >= 0; i--) {
                out.print(path.get(i) + " ");
            }
            out.println();
        }
        out.close();
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
