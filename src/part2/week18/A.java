package part2.week18;

import java.io.*;
import java.util.*;

public class A {

    static Random random = new Random();

    public static class Treap {
        public int element;
        public int y;
        public int size;

        public final Treap left;
        public final Treap right;

        private Treap(int element, int y, Treap left, Treap right) {
            this.element = element;
            this.y = y;
            this.left = left;
            this.right = right;
            recalc();
        }

        public static Treap merge(Treap l, Treap r) {
            if (l == null) return r;
            if (r == null) return l;

            if (l.y > r.y) {
                Treap newR = merge(l.right, r);
                return new Treap(l.element, l.y, l.left, newR);
            } else {
                Treap newL = merge(l, r.left);
                return new Treap(r.element, r.y, newL, r.right);
            }
        }

        public static int sizeOf(Treap treap) {
            return treap == null ? 0 : treap.size;
        }

        public void recalc() {
            size = sizeOf(left) + sizeOf(right) + 1;
        }

        public Treap[] split(int v) {
            Treap newTree = null;
            Treap l = null;
            Treap r = null;

            if (element <= v) {
                if (right == null) {
                    r = null;
                } else {
                    Treap[] t1 = right.split(v);
                    newTree = t1[0];
                    r = t1[1];
                }
                l = new Treap(this.element, y, left, newTree);
            } else {
                if (left == null) {
                    l = null;
                } else {
                    Treap[] t1 = left.split(v);
                    l = t1[0];
                    newTree = t1[1];
                }
                r = new Treap(this.element, y, newTree, right);
            }
            return new Treap[]{l, r};
        }

    }

    static int lessOrEq(Treap t, int v) {
        if (t == null) {
            return 0;
        }
        if (t.element > v) {
            return lessOrEq(t.left, v);
        }
        return Treap.sizeOf(t.left) + 1 + lessOrEq(t.right, v);
    }

    static Treap insert(Treap root, int x) {

        Treap c = new Treap(x, random.nextInt(), null, null);
        if (root == null) {
            return c;
        } else {
            Treap[] t = root.split(x);
            return Treap.merge(Treap.merge(t[0], c), t[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        MyWriter out = new MyWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        Treap treap = null;
        Treap[] treaps = new Treap[n+1];

        treaps[0] = null;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            treap = insert(treap, v);
            treaps[i + 1] = treap;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int k = in.nextInt();
            int l = in.nextInt();

            int c2 = lessOrEq(treaps[y], l) - lessOrEq(treaps[y], k - 1);
            int c1 = lessOrEq(treaps[x - 1], l) - lessOrEq(treaps[x - 1], k - 1);
            out.print((c2 - c1) + "\n");
        }

        out.close();
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
