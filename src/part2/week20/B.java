package part2.week20;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class B {

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            String w = in.nextWord();
            if (Character.isDigit(w.charAt(0))) {
                StringBuilder builder = new StringBuilder();
                root.getK(builder, Integer.valueOf(w));
                System.out.println(builder.substring(0, builder.length() - 1));
            } else {
                w = w + "_";
                root.add(new MyString(w.toCharArray(), 0, w.length()));
            }
        }

    }

    static class MyString {
        int from;
        int to;
        char[] data;

        public MyString(char[] data, int from, int to) {
            this.data = data;
            this.from = from;
            this.to = to;
        }

        public boolean isEmpty() {
            return from == to;
        }

        public boolean startsWith(MyString other) {
            final int len = other.length();
            if (len > length()) {
                return false;
            }
            final char[] otherData = other.data;
            final int otherFrom = other.from;
            for (int i = 0; i < len; i++) {
                if (this.data[this.from + i] != otherData[otherFrom + i]) {
                    return false;
                }
            }
            return true;
        }

        private int length() {
            return to - from;
        }

        public char first() {
            return data[from];
        }

        public MyString substring() {
            return new MyString(data, this.from + 1, this.to);
        }

        @Override
        public String toString() {
            return new String(data, from, to - from);
        }
    }

    static class Node {
        MyString string;
        Node[] next;
        int size;

        public Node() {
        }

        Node getOrPut(char ch) {
            if (next[ch - '_'] == null) {
                next[ch - '_'] = new Node();
            }
            return next[ch - '_'];
        }

        public void add(MyString str) {
            size += 1;
            if (str.isEmpty()) {
                return;
            }
            if (next == null) {
                if (string == null) {
                    string = str;
                } else {
                    if (string.startsWith(str)) {
                        return;
                    }
                    next = new Node['z' - '_' + 1];
                    getOrPut(string.first()).add(string.substring());
                    string = null;
                    getOrPut(str.first()).add(str.substring());
                }
            } else {
                getOrPut(str.first()).add(str.substring());
            }
        }

        public void getK(StringBuilder builder, int k) {
            if (next == null) {
                builder.append(string.toString());
                return;
            }
            for (char ch = '_'; ch <= 'z'; ch++) {
                Node node = next[ch - '_'];

                if (node == null) {
                    continue;
                }
                if (node.size >= k) {
                    builder.append(ch);
                    node.getK(builder, k);
                    return;
                }
                k -= node.size;
            }
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