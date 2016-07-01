package part2.week15;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A {
    public static void main(String[] args) throws IOException {

        MyReader in = new MyReader(System.in);
        int n = in.nextInt();
        Snowman[] arr = new Snowman[n + 1];
        arr[0] = new Snowman(0, null);
        long sum = 0;

        for (int i = 1; i <= n; i++) {
            int t = in.nextInt();
            int m = in.nextInt();
            if (m == 0) {
                arr[i] = arr[t].snowman;
            } else {
                arr[i] = new Snowman(arr[t].i + m, arr[t]);
            }
            sum += arr[i].i;
        }

        System.out.println(sum);
    }


    static class Snowman {
        int i;
        Snowman snowman;

        Snowman(int i, Snowman snowman) {
            this.i = i;
            this.snowman = snowman;
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
/*
8
0 1
1 5
2 4
3 2
4 3
5 0
6 6
1 0 */