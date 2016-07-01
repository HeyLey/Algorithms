package part1.week7;

import java.io.*;

public class E {
    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out, 1 << 16));

        int n = in.nextInt();

        String[] names = new String[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            names[i] = in.nextWord();
        }

        for (int i = 0; i < n; i++) {
            left[i] = (i + n - 1) % n;
            right[i] = (i + 1) % n;
        }

        for (int i = 0; i < n - 3; i++) {
            int child = in.nextInt() - 1;
            int l = left[child];
            int r = right[child];

            out.println(names[l] + " " + names[r]);

            right[l] = r;
            left[r] = l;
        }
        out.close();
    }

    static class MyReader {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        MyReader( InputStream in ) {
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
                _buf.append((char)c);
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