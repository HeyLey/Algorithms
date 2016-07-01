package part1.week11;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class D {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();

        int[][] g = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }

        long[] dist = new long[n];
        int[] prev = new int[n];

        Queue q = new Queue(2 * n);

        for (int i = 0; i < n; i++) {
            q.push(i);
        }

        int start = -1;
        main:
        for (int k = 0; k < n + 1; k++) {
            if (q.isEmpty()) {
                break;
            }
            Queue qnext = new Queue(n + 1);
            while (!q.isEmpty()) {
                int v = q.pull();
                long d = dist[v];
                int[] weights = g[v];
                for (int u = 0; u < weights.length; u++) {
                    int edgeWeight = weights[u];
                    if (d + edgeWeight < dist[u]) {
                        prev[u] = v;
                        if (k >= n) {
                            start = u;
                            break main;

                        }
                        dist[u] = d + edgeWeight;
                        if (!q.inQueue[u] && !qnext.inQueue[u]) {
                            qnext.push(u);
                        }
                    }
                }
            }
            q = qnext;
        }

        if (start == -1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            boolean[] visited = new boolean[n];
            int p = start;
            List<Integer> array = new ArrayList<>();

            do {
                visited[p] = true;
                array.add(p);
                p = prev[p];
            } while (!visited[p]);

            List<Integer> result = new ArrayList<>();

            for (int i = array.size() - 1; i >= 0; i--) {
                result.add(array.get(i) + 1);
                if (array.get(i) == p) {
                    break;
                }
            }

            System.out.println(result.size());
            for (int a : result) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    static class Queue {
        int[] data;
        boolean[] inQueue;
        int start = 0;
        int next = 0;

        Queue(int n) {
            data = new int[n];
            inQueue = new boolean[n];
            start = 0;
            next = 0;
        }

        public void push(int v) {
            inQueue[v] = true;
            data[next] = v;
            next = (next + 1) % data.length;
        }

        public int pull() {
            int v = data[start];
            inQueue[v] = false;
            start = (start + 1) % data.length;
            return v;
        }

        public boolean isEmpty() {
            return start == next;
        }
    }

    static class RdMy {
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
}