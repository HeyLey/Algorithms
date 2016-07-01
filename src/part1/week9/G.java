package part1.week9;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class G {
    static int timer = 0;
    static boolean[] visited;
    static int tin[];
    static int fup[];
    static boolean cutpoints[];

    public static void main(String[] args) throws IOException {
        Read in = new Read(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;


        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            g.get(j).add(i);
            g.get(i).add(j);
        }


        visited = new boolean[n];
        tin = new int[n];
        fup = new int[n];
        cutpoints = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, g);
            }
        }

        boolean[] single = new boolean[n];
        visited = new boolean[n];

        dfs2(s, t, g, single);

        int number = 0;

        for (int i = 0; i < n; i++) {
            if (single[i]) {
                number++;
            }
        }
        out.println(number);

        for (int i = 0; i < n; i++) {
            if (single[i]) {
                out.print(i+1 + " ");
            }
        }
        out.println();
        out.close();
    }

    static void dfs(int v, int p, List<List<Integer>> g) {
        visited[v] = true;
        tin[v] = fup[v] = timer++;
        int children = 0;
        for (int i = 0; i < g.get(v).size(); i++) {
            int to = g.get(v).get(i);
            if (to == p) continue;
            if (visited[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v, g);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] >= tin[v] && p != -1) {
                    cutpoints[v] = true;
                }
                ++children;
            }
        }
        if (p == -1 && children > 1) {
            cutpoints[v] = true;
        }
    }

    static boolean dfs2(int v, int end, List<List<Integer>> g, boolean[] single) {
        visited[v] = true;
        if (v == end) {
            return true;
        }
        List<Integer> next = g.get(v);

        for (int to : next) {
            if (!visited[to]) {
                if (dfs2(to, end, g, single)) {
                    if (cutpoints[v]) {
                        single[v] = true;
                    }
                    return true;
                }
            }
        }
        return false;
    }


    static class Read {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        Read(InputStream in) {
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