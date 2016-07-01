package part1.week12;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class C {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);

        int n = in.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        boolean[] inSet = new boolean[n];

        double dist[] = new double[n];
        inSet[0] = true;

        for (int i = 0; i < n; i++) {
            dist[i] = getDist(x, y, 0, i);
        }


        double sum = 0;

        for (int k = 0; k < n - 1; k++) {
            double min = Double.POSITIVE_INFINITY;
            int minV = 0;

            for (int i = 1; i < n; i++) {
                if (inSet[i]) {
                    continue;
                }
                if (dist[i] < min) {
                    min = dist[i];
                    minV = i;
                }
            }

            sum += min;

            for (int i = 1; i < n; i++) {
                if (inSet[i]) {
                    continue;
                }
                dist[i] = Math.min(dist[i], getDist(x, y, minV, i));
            }
            inSet[minV] = true;
        }

        System.out.println(sum);

    }

    private static double getDist(int[] x, int[] y, int i, int j) {
        return Math.sqrt(Math.pow(x[j] - x[i], 2) + Math.pow(y[j] - y[i], 2));
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