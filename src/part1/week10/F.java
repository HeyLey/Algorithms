package part1.week10;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class F {

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();

        int[][] g = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }

        long[][] dist = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == -1) {
                    dist[i][j] = Long.MAX_VALUE / 2;
                } else {
                    dist[i][j] = g[i][j];
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        long diameter = 0;
        long rad = Long.MAX_VALUE / 2;


        for (int i = 0; i < n; i++) {
            long e = 0;

            for (int j = 0; j < n; j++) {
                if (dist[i][j] > diameter) {
                    diameter = dist[i][j];
                }
                if (dist[i][j] > e) {
                    e = dist[i][j];
                }
            }
            if (rad > e) {
                rad = e;
            }
        }

        System.out.println(diameter);
        System.out.println(rad);
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
