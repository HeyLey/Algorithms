package part2.week16;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);
        MyWriter out = new MyWriter(System.out);

        int n = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        int[] h = new int[n];
        int[][] steps = new int[n][];

        for (int i = 0; i < n; i++) {
            preprocess(i, array, h, steps);
        }

        int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int p1 = in.nextInt() - 1;
            int h1 = h[p1];
            int p2 = in.nextInt() - 1;
            int h2 = h[p2];
            while (h1 < h2) {
                int j = 0;
                int[] step = steps[p2];
                while (j + 1 < step.length && h1 <= h[step[j + 1]]) {
                    j++;
                }
                p2 = step[j];
                h2 = h[p2];
            }
            if (p1 == p2) {
                out.println("1");
            } else {
                out.println("0");
            }
        }
        out.close();
    }

    private static int preprocess(int v, int[] array, int[] h, int[][] steps) {
        if (steps[v] != null) {
            return h[v];
        }
        if (array[v] == 0) {
            h[v] = 0;
            steps[v] = new int[0];
            return 0;
        }
        int h1 = preprocess(array[v] - 1, array, h, steps) + 1;
        h[v] = h1;
        int l = log(h1);
        int st[] = new int[l];
        st[0] = array[v] - 1;
        for (int j = 1; j < l; j++) {
            st[j] = steps[st[j - 1]][j - 1];
        }
        steps[v] = st;
        return h1;
    }

    private static int log(int n) {
        int l = 0;
        while (n > 0) {
            n = n / 2;
            l++;
        }
        return l;
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

    static class MyWriter {
        BufferedOutputStream out;

        final int bufSize = 1 << 16;
        int n;
        byte b[] = new byte[bufSize];

        MyWriter( OutputStream out ) {
            this.out = new BufferedOutputStream(out, bufSize);
            this.n = 0;
        }

        byte c[] = new byte[20];
        void print( int x ) throws IOException {
            int cn = 0;
            if (n + 20 >= bufSize)
                flush();
            if (x < 0) {
                b[n++] = (byte)('-');
                x = -x;
            }
            while (cn == 0 || x != 0) {
                c[cn++] = (byte)(x % 10 + '0');
                x /= 10;
            }
            while (cn-- > 0)
                b[n++] = c[cn];
        }

        void print( char x ) throws IOException {
            if (n == bufSize)
                flush();
            b[n++] = (byte)x;
        }

        void print( String s ) throws IOException {
            for (int i = 0; i < s.length(); i++)
                print(s.charAt(i));
        }
        void println( String s ) throws IOException {
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




/*
6
0 1 1 2 3 3
5
4 1
1 4
3 6
2 6
6 5
*/