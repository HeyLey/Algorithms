package part2.week20;

import java.io.*;

public class A {

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);
        MyWriter out = new MyWriter(System.out);

        Node root = new Node();

        final char[] data = in.nextWord().toCharArray();

        for (int i = 0; i < data.length; i++) {
            root.add(new MyString(data, i, Math.min(i + 30, data.length)));
        }

        int m = in.nextInt();
        for (int j = 0; j < m; j++) {
            String word = in.nextWord();

            if (root.check(new MyString(word.toCharArray(), 0, word.length()))) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
        out.close();
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
    }

    static class Node {
        MyString string;
        Node[] next;

        public Node() {
        }

        Node get(char ch) {
            return next[ch - 'a'];
        }

        Node getOrPut(char ch) {
            if (next[ch - 'a'] == null) {
                next[ch - 'a'] = new Node();
            }
            return next[ch - 'a'];
        }

        public void add(MyString str) {
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
                    next = new Node['z' - 'a' + 1];
                    getOrPut(string.first()).add(string.substring());
                    string = null;
                    getOrPut(str.first()).add(str.substring());
                }
            } else {
                getOrPut(str.first()).add(str.substring());
            }
        }

        public boolean check(MyString word) {
            if (word.isEmpty()) {
                return true;
            }
            if (next == null) {
                if (string != null) {
                    return string.startsWith(word);
                }
                return false;
            } else {
                final Node next = get(word.first());
                if (next == null) {
                    return false;
                } else {
                    return next.check(word.substring());

                }
            }
        }
    }
    static class MyWriter {
        BufferedOutputStream out;

        final int bufSize = 1 << 16;
        int n;
        byte b[] = new byte[bufSize];

        MyWriter(OutputStream out) {
            this.out = new BufferedOutputStream(out, bufSize);
            this.n = 0;
        }

        byte c[] = new byte[20];

        void print(int x) throws IOException {
            int cn = 0;
            if (n + 20 >= bufSize)
                flush();
            if (x < 0) {
                b[n++] = (byte) ('-');
                x = -x;
            }
            while (cn == 0 || x != 0) {
                c[cn++] = (byte) (x % 10 + '0');
                x /= 10;
            }
            while (cn-- > 0)
                b[n++] = c[cn];
        }

        void print(char x) throws IOException {
            if (n == bufSize)
                flush();
            b[n++] = (byte) x;
        }

        void print(String s) throws IOException {
            for (int i = 0; i < s.length(); i++)
                print(s.charAt(i));
        }

        void println(String s) throws IOException {
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