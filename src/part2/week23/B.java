package part2.week23;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B {
    static int[] q;
    static int[] nextQ;
    static Edge[] prev;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;

        List<List<Edge>> glist = new ArrayList<List<Edge>>();

        for (int i = 0; i < n; i++) {
            glist.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            Edge e1 = new Edge(from, to, 1);
            Edge e2 = new Edge(to, from, 1);
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

        boolean result = twoPaths(n, s, t, g);

        MyWriter out = new MyWriter(System.out);

        if (result) {
            out.println("YES");
            dfs(g, s, t, out);
            dfs(g, s, t, out);
        } else {
            out.println("NO");
        }

        out.close();
    }

    private static void dfs(Edge[][] g, int s, int t, MyWriter out) throws IOException {
        int u = s;
        out.print((u + 1) + " ");
        while (u != t) {
            for (Edge edge : g[u]) {
                if (edge.flow == 1) {
                    edge.flow = 0;
                    u = edge.to;
                    break;
                }
            }
            out.print((u + 1) + " ");
        }
        out.println();
    }

    private static boolean twoPaths(int n, int from, int to, Edge[][] g) {
        for (int j = 0; j < 2; j++) {
            Edge prev[] = bfs(from, n, g);

            if (prev[to] == null) {
                return false;
            }
            int p = to;
            while (p != 0) {
                Edge e = prev[p];
                Edge other = e.other;
                if (other.flow > 0) {
                    other.flow = 0;
                } else {
                    e.flow += 1;
                }
                p = e.from;
            }

        }
        return true;
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
        int from;
        int to;
        int w;
        int flow;
        Edge other;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
    static class MyWriter {
        BufferedOutputStream out;

        final int bufSize = 1 << 16;
        int n;
        byte b[] = new byte[bufSize];

        MyWriter(OutputStream out) {
            this.out = new BufferedOutputStream(out, bufSize);
            this.n = 0;
        }

        byte c[] = new byte[20];

        void print(int x) throws IOException {
            int cn = 0;
            if (n + 20 >= bufSize)
                flush();
            if (x < 0) {
                b[n++] = (byte) ('-');
                x = -x;
            }
            while (cn == 0 || x != 0) {
                c[cn++] = (byte) (x % 10 + '0');
                x /= 10;
            }
            while (cn-- > 0)
                b[n++] = c[cn];
        }

        void print(char x) throws IOException {
            if (n == bufSize)
                flush();
            b[n++] = (byte) x;
        }

        void print(String s) throws IOException {
            for (int i = 0; i < s.length(); i++)
                print(s.charAt(i));
        }

        void println(String s) throws IOException {
            print(s);
            println();
        }

        static final String newLine = System.getProperty("line.separator");

        void println() throws IOException {
            print(newLine);
        }

        void flush() throws IOException {
            out.write(b, 0, n);
            n = 0;
        }

        void close() throws IOException {
            flush();
            out.close();
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