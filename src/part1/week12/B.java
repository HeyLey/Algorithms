package part1.week12;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class B {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        for (int i = 0; i < m; i++) {
            in.nextInt();
            in.nextInt();
        }

        String[] command = new String[k];
        int[] from = new int[k];
        int[] to = new int[k];
        for (int i = 0; i < k; i++) {
            command[i] = in.nextWord();
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }

        boolean[] results = new boolean[k];
        Sets sets = new Sets(n);

        for (int i = k - 1; i >= 0; i--) {
            if ("cut".equals(command[i])) {
                sets.merge(from[i], to[i]);
            } else {
                results[i] = sets.get(from[i]) == sets.get(to[i]);
            }
        }
        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < k; i++) {
            if ("ask".equals(command[i])) {
                if (results[i]) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
        out.close();
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