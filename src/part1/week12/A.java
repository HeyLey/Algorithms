package part1.week12;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeSet;

public class A {

    public static void main(String[] args) throws IOException {

        RdMy in = new RdMy(System.in);

        int n = in.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        boolean[] inSet = new boolean[n];

        TreeSet<Edge> edges = new TreeSet<>();

        final int v = 0;

        double sum = 0;
        addVertex(x, y, inSet, edges, v);

        ArrayList<Edge> result = new ArrayList<>();

        while (result.size() < n - 1) {
            final Edge edge = edges.pollFirst();
            if (inSet[edge.to]) {
                continue;
            }
            sum += edge.distance;
            result.add(edge);
            addVertex(x, y, inSet, edges, edge.to);
        }
        System.out.println(sum);
        System.out.println(n-1);
        for (Edge e : result) {
            System.out.println((e.from + 1) + " " + (e.to + 1));
        }
    }

    private static void addVertex(int[] x, int[] y, boolean[] inSet, TreeSet<Edge> edges, int v) {
        inSet[v] = true;
        for (int i = 0; i < inSet.length; i++) {
            if (inSet[i]) {
                continue;
            }

            double dist = Math.sqrt(Math.pow(x[v] - x[i], 2) + Math.pow(y[v] - y[i], 2));
            edges.add(new Edge(v, i, dist));
        }
    }


    static class Edge implements Comparable<Edge>{
        int from = 0;
        int to = 0;
        double distance;

        public Edge(int from, int to, double distance) {
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
            return Double.compare(edge.distance, distance) == 0;

        }

        @Override
        public int compareTo(Edge o) {
            if (distance != o.distance) {
                return Double.compare(distance, o.distance);
            }
            if (from != o.from) {
                return Integer.compare(from, o.from);
            }
            return Integer.compare(to, o.to);
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