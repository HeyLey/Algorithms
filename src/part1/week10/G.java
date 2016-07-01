package part1.week10;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class G {

    public static final int MAX_V = 100000;

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        List<List<Integer>> list = new ArrayList<>();
        List<List<Integer>> weights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            weights.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            int w = in.nextInt();

            list.get(i).add(j);
            weights.get(i).add(w);
            list.get(j).add(i);
            weights.get(j).add(w);
        }

        boolean[] visited = new boolean[n];

        long distance[] = new long[n];
        int prev[] = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE / 2;
        }

        TreeSet<Long> heap = new TreeSet<>();

        visited[s] = true;
        distance[s] = 0;
        heap.add(code(s, 0));

        while (heap.size() > 0) {
            long ind = heap.pollFirst();
            int u = (int) (ind % MAX_V);
            long d = ind / MAX_V;
            List<Integer> next = list.get(u);
            List<Integer> next_w = weights.get(u);
            for (int i = 0; i < next.size(); i++) {
                int v = next.get(i);
                int w = next_w.get(i);
                if (visited[v]) {
                    if (d + w < distance[v]) {
                        heap.remove(code(v, distance[v]));
                        heap.add(code(v, d + w));
                        distance[v] = d + w;
                        prev[v] = u;
                    }
                } else {
                    heap.add(code(v, d + w));
                    distance[v] = d + w;
                    prev[v] = u;
                    visited[v] = true;
                }
            }
        }

        PrintWriter out = new PrintWriter(System.out);


        long d = distance[f];
        if (d == Long.MAX_VALUE / 2) {
            out.println(-1);
        } else {
            out.println(d);

            List<Integer> path = new ArrayList<>();
            int p = f;
            while (p != s) {
                path.add(p + 1);
                p = prev[p];
            }
            path.add(s + 1);
            for (int i = path.size() - 1; i >= 0; i--) {
                out.print(path.get(i) + " ");
            }
            out.println();
        }
        out.close();
    }

    private static long code(int v, long d) {
        return d * MAX_V + v;
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