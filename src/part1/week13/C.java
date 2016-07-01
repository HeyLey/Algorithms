package part1.week13;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class C {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        SegmentTree tree = new SegmentTree(n);

        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            tree.update(i, i, v);
        }

        for (int j = 0; j < k; j++) {
            String cmd = in.nextWord();
            if ("add".equals(cmd)) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                int x = in.nextInt();
                tree.update(l, r, x);
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                out.println(tree.max(l, r));
            }
        }
        out.close();
    }

    static class SegmentTree {
        private final int n;
        long[] data;
        long[] add;

        SegmentTree(int n) {
            this.n = n;
            add = new long[4 * n];
            data = new long[4 * n];
        }


        long max(int l, int r) {
            return max(1, 0, n - 1, l, r);
        }

        long max(int v, int tl, int tr, int l, int r) {
            if (l > r) {
                return Long.MIN_VALUE / 2;
            } if (l == tl && r == tr) {
                return add[v] + data[v];
            }
            int tm = (tl + tr) / 2;
            long v1 = max(v * 2, tl, tm, l, Math.min(r, tm));
            long v2 = max(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
            return add[v] + Math.max(v1, v2);
        }

        void update(int l, int r, int new_val) {
            update(1, 0, n - 1, l, r, new_val);
        }

        void update(int v, int tl, int tr, int l, int r , int up) {
            if (l > r) {
                return;
            }
            if (l == tl && r == tr) {
                add[v] += up;
            } else {
                int tm = (tl + tr) / 2;

                update(v * 2, tl, tm, l, Math.min(r, tm), up);
                update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, up);

                long v1 = data[v * 2] + add[v * 2];
                long v2 = data[v * 2 + 1] + add[v * 2 + 1];

                data[v] = Math.max(v1, v2);
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
