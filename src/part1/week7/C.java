package part1.week7;

import java.io.*;

public class C {
    public static void main(String[] args) throws IOException {
        MyRead in = new MyRead(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        Deque numbers = new Deque();

        long c = (1 << 30) - 1;

        for (int i = 0; i < n; i++) {
            int next = in.nextInt();
            numbers.pushBack(next);
        }

        for (int i = 0; i < k; i++) {
            long x = numbers.data[numbers.head];
            long y = numbers.data[numbers.tail];

            if (x < y) {
                numbers.popFront();
                numbers.pushBack((int) ((x + y) & c));
            } else {
                numbers.popBack();
                numbers.pushFront((int) ((y - x + c + 1) & c));
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(numbers.popFront() + " ");
        }
        out.println();
        out.close();

    }

}

class Deque {
    int head = 0;
    int tail = -1;
    int[] data;

    static int SIZE = (1 << 15) - 1;

    Deque() {
        data = new int[1 << 15];
    }

    void pushBack(int value) {   // добавление в конец очереди
        tail = (tail + 1) & SIZE;
        data[tail] = value;
    }

    void pushFront(int value) {  // добавление в начало очереди
        head = (head + data.length - 1) & SIZE;
        data[head] = value;
    }

    int popBack() {             // выборка с конца очереди
        final int value = data[tail];
        tail = (tail + data.length - 1) & SIZE;
        return value;
    }

    int popFront() {            // выборка с начала очереди
        final int value = data[head];
        head = (head + 1) & SIZE;
        return value;
    }
}

class MyRead {
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    MyRead(InputStream in) {
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