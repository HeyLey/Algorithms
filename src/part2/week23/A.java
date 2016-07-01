package part2.week23;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A {
    static int[] q;
    static int[] nextQ;
    static Edge[] prev;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];
        List<List<Edge>> glist = new ArrayList<List<Edge>>();

        for (int i = 0; i < n; i++) {
            glist.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            Edge e1 = new Edge(i, from, to, w);
            edges[i] = e1;
            Edge e2 = new Edge(-1, to, from, w);
            glist.get(from).add(e1);
            glist.get(to).add(e2);
            e1.other = e2;
            e2.other = e1;
        }

        Edge[][] g = new Edge[n][];

        for (int i = 0; i < n; i++) {
            List<Edge> next = glist.get(i);
            g[i] = new Edge[next.size()];
            for (int j = 0; j < next.size(); j++) {
                g[i][j] = next.get(j);
            }
        }

        q = new int[n];
        nextQ = new int[n];
        prev = new Edge[n];
        visited = new boolean[n];

        while (true) {
            bfs(0, n - 1, g);

            int size = Integer.MAX_VALUE;

            if (prev[n - 1] == null) {
                break;
            }
            int p = n - 1;
            while (p != 0) {
                Edge e = prev[p];

                size = Math.min(size, e.w - e.flow);
                p = e.from;
            }
            p = n - 1;
            while (p != 0) {
                Edge e = prev[p];
                Edge other = e.other;
                if (other.flow > 0) {
                    if (other.flow > size) {
                        other.flow =- size;
                    } else {
                        e.flow += size - other.flow;
                        other.flow = 0;
                    }
                } else {
                    e.flow += size;
                }
                p = e.from;
            }
        }
        Edge prev[] = bfs(0, n, g);
        int sum = 0;
        List<Edge> cut = new ArrayList<>();
        for (Edge e : edges) {
            boolean a = (e.from == 0 || prev[e.from] != null);
            boolean b = (e.to == 0 || prev[e.to] != null);
            if (a != b) {
                sum += e.w;
                cut.add(e);
            }
        }
        PrintWriter out = new PrintWriter(System.out);

        out.println(cut.size() + " " + sum);
        for (Edge e : cut) {
            out.print((e.index + 1) + " ");
        }
        out.println();
        out.close();
    }

    static Edge[] bfs(int from, int to, Edge[][] g) {
        int qSize = 0;
        int nextQSize;
        q[qSize++] = from;

        Arrays.fill(prev, null);
        Arrays.fill(visited, false);

        visited[from] = true;

        while (qSize > 0) {
            nextQSize = 0;
            for (int k = 0; k < qSize; k++) {
                int u = q[k];
                for (Edge edge : g[u]) {
                    if (edge.flow < edge.w) {
                        int v = edge.to;

                        if (visited[v]) {
                            continue;
                        }
                        prev[v] = edge;
                        visited[v] = true;
                        nextQ[nextQSize++] = v;
                        if (v == to) {
                            return prev;
                        }
                    }
                }
            }
            int[] t = q;
            q = nextQ;
            nextQ = t;
            qSize = nextQSize;
        }
        return prev;
    }

    static class Edge {
        int index;
        int from;
        int to;
        int w;
        int flow;
        Edge other;

        public Edge(int index, int from, int to, int w) {
            this.index = index;
            this.from = from;
            this.to = to;
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
