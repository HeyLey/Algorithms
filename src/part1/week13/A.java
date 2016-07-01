package part1.week13;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class A {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        SegmentTree tree = new SegmentTree(n);

        PrintWriter out = new PrintWriter(System.out);

        for (int j = 0; j < k; j++) {
            String cmd = in.nextWord();
            if ("A".equals(cmd)) {
                int i = in.nextInt() - 1;
                int x = in.nextInt();
                tree.update(i, x);
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                out.println(tree.sum(l, r));
            }
        }
        out.close();
    }

    static class SegmentTree {
        private final int n;
        long[] data;

        SegmentTree(int n) {
            this.n = n;
            data = new long[4 * n];
        }


        long sum(int l, int r) {
            return sum(1, 0, n - 1, l, r);
        }

        long sum(int v, int tl, int tr, int l, int r) {
            if (l > r)
                return 0;
            if (l == tl && r == tr)
                return data[v];
            int tm = (tl + tr) / 2;
            return sum(v * 2, tl, tm, l, Math.min(r, tm))
                    + sum(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        }

        void update(int pos, int new_val) {
            update(1, 0, n - 1, pos, new_val);
        }

        void update(int v, int tl, int tr, int pos, int new_val) {
            if (tl == tr)
                data[v] = new_val;
            else {
                int tm = (tl + tr) / 2;
                if (pos <= tm)
                    update(v * 2, tl, tm, pos, new_val);
                else
                    update(v * 2 + 1, tm + 1, tr, pos, new_val);
                data[v] = data[v * 2] + data[v * 2 + 1];
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