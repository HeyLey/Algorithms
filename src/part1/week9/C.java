package part1.week9;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class C {
    static int timer = 0;
    static boolean[] visited;
    static int tin[];
    static int fup[];
    static boolean bridges[];

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        PrintWriter out = new PrintWriter(System.out);


        int n = in.nextInt();
        int m = in.nextInt();

        List[] list = new List[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        int[] xor = new int[m];

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;

            xor[k] = i ^ j;
            list[j].add(k);
            list[i].add(k);
        }

        int[][] g = new int[n][];

        for (int k = 0; k < n; k++) {
            List<Integer> l = (List<Integer>) list[k];

            int[] arr = new int[l.size()];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = l.get(j);
            }
            g[k] = arr;
        }

        visited = new boolean[n];
        tin = new int[n];
        fup = new int[n];
        bridges = new boolean[m];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i, -1, g, xor);
            }
        }

        visited = new boolean[n];

        int[] components = new int[n];

        int componentsNumber = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs2(i, g, xor, components, componentsNumber);
                componentsNumber++;
            }
        }

        StringBuilder[] comp = new StringBuilder[componentsNumber];

        for (int i = 0; i < componentsNumber; i++) {
            comp[i] = new StringBuilder();
        }

        for (int i = 0; i < n; i++) {
            comp[components[i]].append(i + 1).append(' ');
        }

        out.println(componentsNumber);

        for (StringBuilder aComp : comp) {
            out.println(aComp);
        }

        out.close();
    }

    static void dfs1(int v, int p, int[][] g, int[] xor) {
        visited[v] = true;
        tin[v] = fup[v] = timer++;
        for (int edge : g[v]) {
            int to = v ^ xor[edge];
            if (to == p) continue;
            if (visited[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs1(to, v, g, xor);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridges[edge] = true;
                }
            }
        }
    }

    static void dfs2(int v, int[][] g, int[] xor, int[] components, int component) {
        visited[v] = true;
        components[v] = component;
        for (int edge : g[v]) {
            if (bridges[edge]) {
                continue;
            }
            int to = v ^ xor[edge];
            if (!visited[to]) {
                dfs2(to, g, xor, components, component);
            }
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