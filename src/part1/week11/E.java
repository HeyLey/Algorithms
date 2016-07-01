package part1.week11;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class E {

    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        List<List<Integer>> listW = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            listW.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            int w = in.nextInt();
            list.get(i).add(j);
            listW.get(i).add(w);

        }

        int[][] g = toArray(n, list);
        int[][] w = toArray(n, listW);

        long[] dist = new long[n];

        Queue q = new Queue(2*n);


        for (int i = 0; i < n; i++) {
            q.push(i);
        }

        boolean cycle = true;

        for (int k = 0; k < 2 * n; k++) {
            if (q.isEmpty()) {
                cycle = false;
            }
            Queue qnext = new Queue(n + 1);
            while (!q.isEmpty()) {
                int v = q.pull();
                int[] next = g[v];
                long d = dist[v];
                int[] weights = w[v];
                for (int i = 0; i < next.length; i++) {
                    int u = next[i];
                    int edgeWeight = weights[i];
                    if (d + edgeWeight < dist[u]) {
                        dist[u] = d + edgeWeight;
                        if (!q.inQueue[u] && !qnext.inQueue[u]) {
                            qnext.push(u);
                        }
                    }
                }
            }
            q = qnext;
        }

        if (cycle) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static int[][] toArray(int n, List<List<Integer>> list) {
        int[][] g = new int[n][];

        for (int k = 0; k < n; k++) {
            List<Integer> l = list.get(k);

            int[] arr = new int[l.size()];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = l.get(j);
            }
            g[k] = arr;
        }
        return g;
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