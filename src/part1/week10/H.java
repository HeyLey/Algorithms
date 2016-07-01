package part1.week10;

import java.io.*;
import java.util.*;

public class H{

    public static void main(String[] args) throws Exception {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int t = in.nextInt();

        Map<Integer, ArrayList<Node>> list = new HashMap<>();
        Queue<Edge> queue = new PriorityQueue<>();
        long[] distance = new long[n + 1];

        for (int k = 1; k <= n; k++) {
            ArrayList<Node> nb = new ArrayList<>();
            list.put(k, nb);
        }

        for (int k = 1; k <= m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            int w = in.nextInt();

            list.get(i).add(new Node(j, w));
            list.get(j).add(new Node(i, w));
        }

        for (Integer v : list.keySet()) {
            if (v == s)
                distance[v] = 0;
            else
                distance[v] = Integer.MAX_VALUE;
            queue.add(new Edge(v, distance[v]));
        }

        dejkstra(s, distance, queue, list);

        if (distance[t] != Integer.MAX_VALUE) {
            System.out.print(distance[t]);
        } else  {
            System.out.print(-1);
        }
    }

    private static void dejkstra(int u, long[] distance, Queue<Edge> queue, Map<Integer, ArrayList<Node>> list) {
        distance[u] = 0;

        while (!queue.isEmpty()) {
            Edge node = queue.poll();
            int v = node.vertex;
            for (Node t : list.get(v)) {
                int to = t.v;
                int len = t.w;
                if (distance[v] + len < distance[to]) {
                    distance[to] = distance[v] + len;
                    queue.add(new Edge(to, distance[to]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        private long distV;

        private Edge(int vertex, long distV) {
            this.vertex = vertex;
            this.distV = distV;
        }

        public int compareTo(Edge edge) {
            if (distV < edge.distV) {
                return -1;
            } else if (distV > edge.distV) {
                return 1;
            }
            return 0;
        }
    }

    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
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