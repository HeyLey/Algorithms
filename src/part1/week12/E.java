package part1.week12;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class E {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int d = in.nextInt();
            edges[i] = new Edge(a, b, d);
        }

        Arrays.sort(edges);

        Sets sets = new Sets(n);

        long result = 0;
        int edgesNumber = 0;

        for (int i = 0; i < m; i++) {
            Edge edge = edges[i];
            int from = edge.from;
            int to = edge.to;
            if (sets.get(from) == sets.get(to)) {
                continue;
            }
            result += edge.distance;
            edgesNumber++;
            sets.merge(from, to);
            if (edgesNumber == n-1) {
                break;
            }
        }
        System.out.println(result);
    }

    static class Edge implements Comparable<Edge>{
        int from = 0;
        int to = 0;
        int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (from != edge.from) return false;
            if (to != edge.to) return false;
            return edge.distance == distance;

        }

        @Override
        public int compareTo(Edge o) {
            if (distance != o.distance) {
                return Integer.compare(distance, o.distance);
            }
            if (from != o.from) {
                return Integer.compare(from, o.from);
            }
            return Integer.compare(to, o.to);
        }
    }


    static class Sets {
        int[] data;
        int[] rank;

        public Sets(int n) {
            data = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = i;
            }
        }

        public int get(int v) {
            if (data[v] == v) {
                return v;
            }
            data[v] = get(data[v]);
            return data[v];
        }

        public void merge(int u, int v) {
            int x = get(u);
            int y = get(v);

            if (rank[x] < rank[y]) {
                data[x] = y;
            } else {
                data[y] = x;
            }
            if (rank[x] == rank[y]) {
                ++rank[x];
            }
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