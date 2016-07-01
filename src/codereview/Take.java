package codereview;

/*
 Дедлайн: 1 дек 2015г 23:59
 5L
 Саша и Петя играют в весёлую игру.
 Петя рисует на листе бумаги таблицу m x m и заполняет её целыми числами.
 После этого Саша ставит свою фишку на клетку (1, 1) и может каждым своим ходом двигать её вправо или вниз. К
 роме того, если Сашина фишка находится на числе большем, чем все числа, которые он брал до этого,
 Саша может сказать «беру», и тогда Петя записывает на бумажку то число, на котором стоит Сашина фишка
 (если Саша не брал ранее ни одного числа, то он может сказать «беру» на любом числе).
 Игра заканчивается, когда Сашина фишка больше не может ходить, и количество очков,
 которые набрал Саша, зависит от того, сколько чисел он взял.
 Помогите Саше «взять» как можно больше чисел!

 Формат входных данных

 Входной файл состоит из одного или нескольких наборов входных данных (не более 5).
 Набор входных данных начинается строкой, в которой записаны числа m и n (1 <= m,n <= 100).
 Далее следуют n строк по m чисел в каждой — таблица, которую нарисовал Петя.
 Все числа не превосходят по модулю 10000. Файл завершается набором с m = n = 0.

 Формат выходных данных

 Для каждого из наборов входных данных выведите в первой строке максимальное количество чисел M,
 которые может взять Саша.
 Во второй строке выведите один из возможных вариантов Сашиных действий —
 строку из символов R, D и T, где R означает ход вправо (x ← x + 1),
 D обозначает ход вниз (y ← y + 1) и T обозначает взятие числа,
 на котором в данный момент стоит фишка.

 Разделяйте вывод для разных наборов входных данных одной пустой строкой.

 Test:
 input:
 2 2
 1 1
 1 1
 2 3
 1 2
 1 1
 1 3
 0 0

 output:
 1
 TRD

 3
 TRTDDT

 Прим.:
 Динамика за O(n^3).
 Состояние динамики: [столбец, строка, сколько чисел уже взяли]
 Функция динамики: минимальное значение последнего взятого числа

 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Take {

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

    public static void main(String[] args) throws IOException {

        MyReader in = new MyReader(System.in);

        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();
        }




    }
}
