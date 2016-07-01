package part2.week17;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        MyWriter out = new MyWriter(System.out);

        int n = in.nextInt();

        // x1, y1,
        // x2, y2 — сначала координаты левого нижнего угла прямоугольника, потом правого верхнего
        int x1_r = Integer.MIN_VALUE;
        int x2_r = Integer.MAX_VALUE;
        int y1_r = Integer.MIN_VALUE;
        int y2_r = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            x1_r = Math.max(x1, x1_r);
            y1_r = Math.max(y1, y1_r);

            x2_r = Math.min(x2, x2_r);
            y2_r = Math.min(y2, y2_r);

        }

        if (x1_r > x2_r || y1_r > y2_r) {
            out.println("-1");
        } else {
            out.print(x1_r + " " + y1_r + " " + x2_r + " " + y2_r);
        }

        out.close();
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
2
0 0 2 2
1 1 3 3 */