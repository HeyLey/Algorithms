package part1.week7;

/*
В очереди в магазин стоят люди. Человек i хочет купить товар a_i.
Изначально в магазине ничего нет. Происходят события следующих типов:
1. В момент времени T поступил один экземпляр товара A.
2. В момент времени T в конец очереди встал человек, который хочет купить товар A.
Нужно промоделировать процесс и для каждого человека определить, сколько он будет стоять в очереди.
Замечание: как только первый в очереди может купить то, что хочет, он сразу мгновенно покупает и уходит.
Формат входных данных:
Число событий N, 1 <= N <= 10 000. Далее события в порядке возрастания времени T.
Каждое событие описывается так: Type T A,
где Type — тип события. 1 <= A <= 10 000, 1 <= T <= 60 000
Формат выходных данных:
Для каждого человека (в том порядке, в котором люди вставали в очередь)
выведите, сколько человек простоял в очереди. Если он так и остался стоять, выведите -1.
*/

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        MyRd in = new MyRd(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        Deq items = new Deq();
        Deq times = new Deq();

        int[] available = new int[10000];

        for (int i = 0; i < n; i++) {
            int type = in.nextInt();
            int time = in.nextInt();
            int a = in.nextInt() - 1;

            if (type == 1) {
                available[a]++;
            } else {
                items.pushBack(a);
                times.pushBack(time);
            }

            while (items.tail >= items.head && available[items.front()] > 0) {
                int item = items.popFront();
                available[item]--;
                int startTime = times.popFront();
                final int waitTime = time - startTime;
                out.print(waitTime + " ");

            }
        }

        while (items.tail >= items.head) {
            items.popFront();
            times.popFront();
            out.print("-1 ");
        }

        out.println();
        out.close();
    }
}

class Deq {
    int head = 0;
    int tail = -1;
    int[] data;

    static int SIZE = (1 << 15) - 1;

    Deq() {
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

    int front() {
        return data[head];
    }

    int popFront() {            // выборка с начала очереди
        final int value = data[head];
        head = (head + 1) & SIZE;
        return value;
    }
}

class MyRd {
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    MyRd(InputStream in) {
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


