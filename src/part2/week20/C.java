package part2.week20;
import java.io.*;
import java.util.Arrays;

public class C {
    static final int ALPHABET_SIZE = 128;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        MyWriter out = new MyWriter(System.out);

        AhoCorasick ahoCorasick = new AhoCorasick(100000);

        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            ahoCorasick.addString(in.readLine());
        }

        String line = null;
        while ((line = in.readLine()) != null) {
            int node = 0;
            for (char ch : line.toCharArray()) {
                node = ahoCorasick.transition(node, ch);
                if (ahoCorasick.getGreen(node) != -1) {
                    out.println(line);
                    break;
                }
            }
        }
        out.close();
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

    static class Node {
        int parent;
        char charFromParent;
        int suffLink = -1;
        int greenLink = -2;

        int[] children = new int[ALPHABET_SIZE];
        int[] transitions = new int[ALPHABET_SIZE];
        boolean leaf;

        {
            Arrays.fill(children, -1);
            Arrays.fill(transitions, -1);
        }
    }


    static class AhoCorasick {

        Node[] nodes;
        int nodeCount;


        public AhoCorasick(int maxNodes) {
            nodes = new Node[maxNodes];
            // create root
            nodes[0] = new Node();
            nodes[0].suffLink = 0;
            nodes[0].parent = -1;
            nodeCount = 1;
        }

        public void addString(String s) {
            int cur = 0;
            for (char ch : s.toCharArray()) {
                int c = ch;
                if (nodes[cur].children[c] == -1) {
                    nodes[nodeCount] = new Node();
                    nodes[nodeCount].parent = cur;
                    nodes[nodeCount].charFromParent = ch;
                    nodes[cur].children[c] = nodeCount++;
                }
                cur = nodes[cur].children[c];
            }
            nodes[cur].leaf = true;
        }

        int getGreen(int nodeIndex) {
            Node node = nodes[nodeIndex];
            if (node.leaf) {
                return nodeIndex;
            }
            if (node.greenLink == -2) {
                if (nodeIndex == 0) {
                    node.greenLink = -1;
                } else {
                    node.greenLink = getGreen(suffLink(nodeIndex));
                }
            }
            return node.greenLink;
        }

        public int suffLink(int nodeIndex) {
            Node node = nodes[nodeIndex];
            if (node.suffLink == -1)
                node.suffLink = node.parent == 0 ? 0 : transition(suffLink(node.parent), node.charFromParent);
            return node.suffLink;
        }

        public int transition(int nodeIndex, char ch) {
            int c = ch;
            Node node = nodes[nodeIndex];
            if (node.transitions[c] == -1)
                node.transitions[c] = node.children[c] != -1 ? node.children[c] : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
            return node.transitions[c];
        }
    }
}

