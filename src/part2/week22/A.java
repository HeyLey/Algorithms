package part2.week22;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        MyWriter out = new MyWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> g = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            while (true) {
                int v = in.nextInt();
                if (v == 0) {
                    break;
                }
                g.get(i).add(v - 1);
            }
        }

        int[] mt = new int[m];
        for (int i = 0; i < m; i++) {
            mt[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            boolean[] used = new boolean[n];
            try_kuhn(i, g, used, mt);
        }
        int size = 0;

        for (int i = 0; i < m; i++) {
            if (mt[i] != -1) {
                size++;
            }
        }

        out.print(size + "\n");

        for (int i = 0; i < m; i++) {
            if (mt[i] != -1) {

                out.println((mt[i] + 1) + " " + (i + 1));
            }
        }
        out.close();
    }

    static boolean try_kuhn(int v, List<List<Integer>> g, boolean[] used, int[] mt) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        List<Integer> next = g.get(v);
        for (int to : next) {
            if (mt[to] == -1 || try_kuhn(mt[to], g, used, mt)) {
                mt[to] = v;
                return true;
            }
        }
        return false;
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
