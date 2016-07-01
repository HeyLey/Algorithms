package part2.week18;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class D {

    static Random random = new Random();

    public static class Treap {
        public int key;
        public int value;
        public int y;
        public int maxPrefix;
        public int sum;


        public final Treap left;
        public final Treap right;

        private Treap(int key, int value, int y, Treap left, Treap right) {
            this.key = key;
            this.value = value;
            this.y = y;
            this.left = left;
            this.right = right;

            int currentPrefix = sumOf(left) + value;
            sum = currentPrefix + sumOf(right);
            maxPrefix = Math.max(maxOfPrefix(left), currentPrefix);
            maxPrefix = Math.max(maxPrefix, currentPrefix + maxOfPrefix(right));
        }

        private int maxOfPrefix(Treap t) {
            if (t == null) {
                return Integer.MIN_VALUE / 2;
            } else {
                return t.maxPrefix;
            }
        }

        public static int sumOf(Treap t) {
            if (t == null) {
                return 0;
            } else {
                return t.sum;
            }
        }

        public static Treap merge(Treap l, Treap r) {
            if (l == null) return r;
            if (r == null) return l;

            if (l.y > r.y) {
                Treap newR = merge(l.right, r);
                return new Treap(l.key, l.value, l.y, l.left, newR);
            } else {
                Treap newL = merge(l, r.left);
                return new Treap(r.key, r.value, r.y, newL, r.right);
            }
        }

        public Treap[] split(int v) {
            Treap newTree = null;
            Treap l = null;
            Treap r = null;

            if (key <= v) {
                if (right == null) {
                    r = null;
                } else {
                    Treap[] t1 = right.split(v);
                    newTree = t1[0];
                    r = t1[1];
                }
                l = new Treap(this.key, this.value, y, left, newTree);
            } else {
                if (left == null) {
                    l = null;
                } else {
                    Treap[] t1 = left.split(v);
                    l = t1[0];
                    newTree = t1[1];
                }
                r = new Treap(this.key, this.value, y, newTree, right);
            }
            return new Treap[]{l, r};
        }

        public int findMax() {
            int currentPrefix = sumOf(left) + value;
            if (left != null && left.maxPrefix == maxPrefix) {
                return left.findMax();
            }
            if (right != null && currentPrefix + right.maxPrefix == maxPrefix) {
                return right.findMax();
            }
            return key;
        }
    }


    static Treap insert(Treap root, int key, int value) {
        Treap c = new Treap(key, value, random.nextInt(), null, null);
        if (root == null) {
            return c;
        } else {
            Treap[] t = root.split(key);
            return Treap.merge(Treap.merge(t[0], c), t[1]);
        }
    }

    static Treap remove(Treap root, int x) {
        if (root == null) {
            return null;
        }
        if (x < root.key) {
            Treap newLeft = remove(root.left, x);

            return new Treap(root.key, root.value, root.y, newLeft, root.right);
        } else if (x > root.key) {
            Treap newRight = remove(root.right, x);
            return new Treap(root.key, root.value, root.y, root.left, newRight);
        } else {
            return Treap.merge(root.left, root.right);
        }
    }

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        random.setSeed(1);

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

        Treap treap = null;

        int max = 0;
        int maxX = 0;
        int maxY = 0;

        for (int i = 0; i < 2 * n; i++) {
            Event e = events[i];

            if (e.end == 0) {
                treap = insert(treap, e.y1 * 2, 1);
                treap = insert(treap, e.y2 * 2 + 1, -1);
                if (treap.maxPrefix > max) {
                    max = treap.maxPrefix;
                    maxX = e.x;
                    maxY = treap.findMax() / 2;
                }
            } else {
                treap = remove(treap, e.y1 * 2);
                treap = remove(treap, e.y2 * 2 + 1);
            }
        }
        out.println(max);
        out.println(maxX + " " + maxY);
        out.close();
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
