package part1.week8;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


/*
В подземелье M тоннелей и N перекрестков, каждый тоннель соединяет какие-то два перекрестка.
Мышиный король решил поставить по светофору в каждом тоннеле перед каждым перекрестком.
Напишите программу, которая посчитает,
сколько светофоров должно быть установлено на каждом из перекрестков.
Перекрестки пронумерованы числами от 1 до 0.

Формат входных данных
Во входном файлее записано два числа N и M (0 < N <= 100), 0 <= M <= N(N-1)/2
В следующих M строках записаны по два числа i и j (1 <= i, j <= N), которые означают,
что перекрестки i и j соединены тоннелем.

Формат выходных данных
В выходной файл вывести N чисел: k-е число означает количество светофоров на k-м перекрестке.
 */
public class A {

    public static void main(String[] args) throws IOException {
        Read in = new Read(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] array = new int[n];

        for (int k = 0; k < m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();

            array[i - 1]++;
            array[j - 1]++;

        }

        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + " ");
        }

        out.close();
    }
}


class Read {
    BufferedInputStream in;

    final int bufSize = 1 << 16;
    final byte b[] = new byte[bufSize];

    Read(InputStream in) {
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