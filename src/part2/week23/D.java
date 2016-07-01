package part2.week23;

import java.io.*;
import java.util.Arrays;

public class D {
    static int[] q;
    static Edge[] prev;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];
        Edge[] active = new Edge[n];

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            Edge e1 = new Edge(from, to, w);
            addToActive(active, e1);
            edges[i] = e1;
            Edge e2 = new Edge(to, from, 0);
            addToActive(active, e2);

            e1.other = e2;
            e2.other = e1;
        }

        q = new int[n];
        prev = new Edge[n];
        visited = new boolean[n];

        int maxFlow = 0;


        while (true) {
            bfs(0, n - 1, active);

            int size = Integer.MAX_VALUE;

            if (prev[n - 1] == null) {
                break;
            }
            int p = n - 1;
            while (p != 0) {
                Edge e = prev[p];

                size = Math.min(size, e.w);
                p = e.from;
            }
            p = n - 1;
            while (p != 0) {
                Edge e = prev[p];
                Edge other = e.other;

                e.w -= size;
                //if (other.w == 0) {
                //    addToActive(active, other);
                //}
                other.w += size;

                p = e.from;
            }
            maxFlow += size;
        }
        MyWriter out = new MyWriter(System.out);

        out.print(maxFlow);
        out.print("\n");

        for (int i = 0; i < m; i++) {
            out.print(edges[i].other.w);
            out.print("\n");
        }

        out.close();
    }

    private static void addToActive(Edge[] active, Edge e1) {
        int v = e1.from;
        e1.nextActive = active[v];
        active[v] = e1;
    }

    private static void bfs(int from, int to, Edge[] active) {
        q[0] = from;
        int qStart = 0;
        int qEnd = 1;

        prev[to] = null;
        Arrays.fill(visited, false);

        visited[from] = true;

        while (qStart != qEnd) {
            int u = q[qStart];
            qStart++;
            //Edge prevEdge = null;
            for (Edge edge = active[u]; edge != null; ) {
                if (edge.w > 0) {
                    int v = edge.to;

                    if (!visited[v]) {
                        prev[v] = edge;
                        visited[v] = true;
                        q[qEnd] = v;
                        qEnd++;
                        if (v == to) {
                            return;
                        }
                    } }
                    //prevEdge = edge;
                    edge = edge.nextActive;
               // } else {
                    /*if (prevEdge == null) {
                        active[u] = edge.nextActive;
                        edge = edge.nextActive;
                    } else {
                        prevEdge.nextActive = edge.nextActive;
                        edge = edge.nextActive;
                    }*/
              //      edge = edge.nextActive;
              //  }

            }
        }

    }

    static class Edge {
        int w;
        short to;
        short from;
        Edge other;
        Edge nextActive;

        public Edge(int from, int to, int w) {
            this.from = (short) from;
            this.to = (short) to;
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

}
