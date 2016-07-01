package part2.week15;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class B {

    public static void main(String[] args) throws IOException {
        RdMy in = new RdMy(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        int m = in.nextInt();

        Object[] trees = new Object[m];

        int size = 1;

        trees[0] = Tree.fromArray(array, 0, array.length - 1);

        for (int i = 0; i < m; i++) {
            if ("create".equals(in.nextWord())) {
                int t_i = in.nextInt();
                int index = in.nextInt();
                int element = in.nextInt();

                Object t = trees[t_i - 1];

                trees[size] = Tree.set(t, index - 1, element);
                size++;
            } else {
                int t_i = in.nextInt();
                int index = in.nextInt();
                out.println(Tree.get(trees[t_i - 1], index - 1));
            }
        }
        out.close();
    }

    public static class Tree {
        public int size;

        public final Object[] data;

        private Tree(Object[] data) {
            this.data = data;
            size = 0;
            for (int i = 0; i < data.length; i++) {
                size = sizeOf(data[i]);
            }
        }

        public static int sizeOf(Object data) {
            if (data instanceof int[]) {
                return ((int[]) data).length;
            }
            return ((Tree) data).size;
        }

        public static Object fromArray(int[] array, int from, int to) {
            if ((to - from) < 8) {
                int[] data = new int[to - from + 1];
                System.arraycopy(array, from, data, 0, data.length);
                return array;
            }

            Object[] data = new Object[4];

            int len = to - from + 1;

            for (int i = 0; i < 4; i++) {
                data[i] = fromArray(array, from + len * i / 4, from + len * (i + 1) / 4 - 1);
            }

            return new Tree(data);
        }

        public static Object set(Object obj, int index, int newElement) {
            if (obj instanceof int[]) {
                int[] result = ((int[]) obj).clone();
                result[index] = newElement;
                return result;
            }

            Tree t = (Tree) obj;

            Object[] newData = t.data.clone();

            for (int i = 0; i < 4; i++) {
                int sizeOf = sizeOf(newData[i]);
                if (index < sizeOf) {
                    newData[i] = set(newData[i], index, newElement);
                    return new Tree(newData);

                }
                index -= sizeOf;
            }

            throw new RuntimeException();
        }

        public static int get(Object obj, int index) {
            if (obj instanceof int[]) {
                return ((int[]) obj)[index];
            }
            Object[] t = ((Tree) obj).data;

            for (int i = 0; i < 4; i++) {
                int sizeOf = sizeOf(t[i]);
                if (index < sizeOf) {
                    return get(t[i], index);
                }
                index -= sizeOf;
            }
            throw new RuntimeException();
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

/*
6
1 2 3 4 5 6
11
create 1 6 10
create 2 5 8
create 1 5 30
get 1 6
get 1 5
get 2 6
get 2 5
get 3 6
get 3 5
get 4 6
get 4 5
*/