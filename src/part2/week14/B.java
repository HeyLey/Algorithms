package part2.week14;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class B {

    static Random random = new Random();

    static class Treap {
        int key;
        long prio;
        Treap left;
        Treap right;
        int count;

        Treap(int key) {
            this.key = key;
            prio = random.nextLong();
            count = 1;
        }

        void update() {
            count = 1 + getCount(left) + getCount(right);
        }
    }

    static int getCount(Treap root) {
        return root == null ? 0 : root.count;
    }

    static class TreapPair {
        Treap left;
        Treap right;

        TreapPair(Treap left, Treap right) {
            this.left = left;
            this.right = right;
        }
    }

    static TreapPair split(Treap root, int minRight) {
        if (root == null)
            return new TreapPair(null, null);
        if (root.key >= minRight) {
            TreapPair leftSplit = split(root.left, minRight);
            root.left = leftSplit.right;
            root.update();
            leftSplit.right = root;
            return leftSplit;
        } else {
            TreapPair rightSplit = split(root.right, minRight);
            root.right = rightSplit.left;
            root.update();
            rightSplit.left = root;
            return rightSplit;
        }
    }

    static Treap merge(Treap left, Treap right) {
        if (left == null)
            return right;
        if (right == null)
            return left;
        if (left.prio > right.prio) {
            left.right = merge(left.right, right);
            left.update();
            return left;
        } else {
            right.left = merge(left, right.left);
            right.update();
            return right;
        }
    }

    static Treap insert(Treap root, int x) {
        TreapPair t = split(root, x);
        return merge(merge(t.left, new Treap(x)), t.right);
    }

    static Treap remove(Treap root, int x) {
        if (root == null) {
            return null;
        }
        if (x < root.key) {
            root.left = remove(root.left, x);
            root.update();
            return root;
        } else if (x > root.key) {
            root.right = remove(root.right, x);
            root.update();
            return root;
        } else {
            return merge(root.left, root.right);
        }
    }

    static int kth(Treap root, int k) {
        if (k < getCount(root.left))
            return kth(root.left, k);
        else if (k > getCount(root.left))
            return kth(root.right, k - getCount(root.left) - 1);
        return root.key;
    }

    static void print(Treap root) {
        if (root == null)
            return;
        print(root.left);
        System.out.println(root.key);
        print(root.right);
    }

    public static void main(String[] args) throws IOException {
        MyReader in = new MyReader(System.in);

        int n = in.nextInt();

        Treap treap = null;
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int nextCmd = in.nextInt();
            if (1 == nextCmd) {
                int element = in.nextInt();
                if (treap == null) {
                    treap = new Treap(element);
                    set.add(element);
                } else {
                    treap = insert(treap, element);
                    set.add(element);
                }
            } else if (-1 == nextCmd){
                int element = in.nextInt();
                treap = remove(treap, element);
                set.remove(element);
            } else {
                int kth = getCount(treap) - in.nextInt();
                System.out.println(kth(treap, kth));
            }
            if (set.size() != getCount(treap)) {
                throw new RuntimeException();
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

