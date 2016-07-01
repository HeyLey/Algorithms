package part2.week17;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class B {
    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        if (n == 0) {
            out.println(0);
            out.close();
            return;
        }

        Event[] events = new Event[n * 2];

        for (int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            events[2 * i] = new Event(x1, y1, y2, 0);
            events[2 * i + 1] = new Event(x2, y1, y2, 1);
        }
        Arrays.sort(events);

        int[] xs = new int[n*2];
        int size = 0;

        long sum = 0;
        int last_x = events[0].x;

        for (int i = 0; i < 2 * n; i++) {
            Event e = events[i];

            if (e.x > last_x) {
                int len = 0;
                int last_y = xs[0] / 2;
                int counter = 0;
                for (int pos : xs) {
                    int y = pos / 2;
                    int end = pos % 2;
                    if (counter > 0) {
                        len += y - last_y;
                    }
                    if (end == 0) {
                        counter++;
                    } else {
                        counter--;
                    }
                    last_y = y;
                }
                sum += ((long) len) * (long)(e.x - last_x);
                last_x = e.x;
            }

            if (e.end == 0) {
                addToSorted(xs, e.y1 * 2, size);
                size++;
                addToSorted(xs, e.y2 * 2 + 1, size);
                size++;
            } else {
                removeFromSorted(xs, e.y1 * 2, size);
                size--;
                removeFromSorted(xs, e.y2 * 2 + 1, size);
                size--;
            }
        }
        out.println(sum);
        out.close();
    }

    private static void removeFromSorted(int[] arr, int v, int size) {
        int i = 0;
        while (i < size && arr[i] != v) {
            i++;
        }
        for (; i < size - 1; i++) {
            arr[i] = arr[i+1];
        }
    }

    private static void addToSorted(int[] arr, int v, int size) {
        int i = size;
        while (i > 0 && arr[i - 1] > v) {
            arr[i] = arr[i - 1];
            i--;
        }
        arr[i] = v;
    }

    static class Event implements Comparable<Event>{
        int x;
        int y1;
        int y2;
        int end;

        public Event(int x, int y1, int y2, int e) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.end = e;
        }


        @Override
        public int compareTo(Event o) {
            if (o.x != x) {
                return x - o.x;
            }
            return end - o.end;
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
