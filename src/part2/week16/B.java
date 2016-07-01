package part2.week16;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class B {
    static int t = 0;
    static int[] tin;
    static int[] tout;
    static int[][] up;
    static ArrayList<ArrayList<Integer>> graph;
    static int l = 0;

    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();


        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }


        tin = new int[N];

        tout = new int[N];


        for (int k = 1; k <= N-1; ++k) {
            int i = in.nextInt();

            graph.get(i-1).add(k);

        }

        while ((1<<l) <= N) {
            ++l;
        }

        up = new int[N][l + 1];

        dfs(0, 0);


        int M = in.nextInt();

        for (int k = 0; k < M; ++k) {

            int a = in.nextInt();

            int b = in.nextInt();

            out.println(lca(a-1, b-1) + 1);

        }


        out.close();
    }


    static void dfs(int u, int p) {
        tin[u] = ++t;

        up[u][0] = p;

        for (int i = 1; i <= l; ++i) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int i = 0; i < graph.get(u).size(); ++i) {
            int to = graph.get(u).get(i);

            if (to != p) {
                dfs(to, u);
            }

        }

        tout[u] = ++t;

    }

    static boolean upper(int a, int b) {
        return tin[a] <= tin[b] && tout[a] >= tout[b];
    }

    static int lca(int a, int b) {

        if (upper(a, b))  return a;

        if (upper(b, a))  return b;

        for (int i = l; i >= 0; --i)

            if (! upper(up[a][i], b))

                a = up[a][i];

        return up[a][0];

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