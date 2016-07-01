package part1.week11;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C {


    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        List<List<Long>> listW = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            listW.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            long w = -in.nextLong();
            list.get(i).add(j);
            listW.get(i).add(w);

        }

        int[][] g = new int[n][];

        for (int k1 = 0; k1 < n; k1++) {
            List<Integer> l = list.get(k1);

            int[] arr = new int[l.size()];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = l.get(j);
            }
            g[k1] = arr;
        }

        long[][] w = new long[n][];

        for (int k1 = 0; k1 < n; k1++) {
            List<Long> l = listW.get(k1);

            long[] arr = new long[l.size()];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = l.get(j);
            }
            w[k1] = arr;
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 2);

        Queue q = new Queue(n + 1);

        dist[0] = 0;

        q.push(0);

        boolean[] inf = new boolean[n];

        for (int k = 0; k < n + 1; k++) {
            if (q.isEmpty()) {
                break;
            }

            Queue qnext = new Queue(n + 1);
            while (!q.isEmpty()) {
                int v = q.pull();
                if (k >= n) {
                    inf[v] = true;
                }
                int[] next = g[v];
                long d = dist[v];
                long[] weights = w[v];
                for (int i = 0; i < next.length; i++) {
                    int u = next[i];
                    long edgeWeight = weights[i];
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

        q = new Queue(n + 1);

        for (int k = 0; k < n; k++) {
            if (inf[k]) {
                q.push(k);
            }
        }

        for (int k = 0; k < n; k++) {
            if (q.isEmpty()) {
                break;
            }

            int v = q.pull();
            int[] next = g[v];
            for (int i = 0; i < next.length; i++) {
                int u = next[i];
                if (inf[u]) {
                    continue;
                }
                inf[u] = true;
                if (!q.inQueue[u]) {
                    q.push(u);
                }
            }
        }

        if (inf[n - 1]) {
            System.out.println(":)");
        } else if (dist[n - 1] == Long.MAX_VALUE / 2) {
            System.out.println(":(");
        } else {
            System.out.println(-dist[n - 1]);
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

        long nextLong() throws IOException {
            int c;
            while ((c = nextChar()) <= 32)
                ;
            long x = 0;
            long sign = 1;
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