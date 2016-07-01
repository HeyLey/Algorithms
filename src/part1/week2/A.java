package part1.week2;

import java.io.*;
import java.util.Arrays;

/**
 * Знаете ли вы, как непросто организовать заезд в ЛКШ?
 * Например, в 2010 году нужно было заказать автобусы для целых n ЛКШат,
 * мечтающих добраться в “Орлёнок” из Саратова.
 * Директору смены сообщили, что можно заказать некоторые из m автобусов.
 * Он узнал вместимость каждого автобуса и сразу понял, какое минимальное количество автобусов ему
 * нужно заказать, чтобы привезти в лагерь всех ЛКШат.
 * А сможете ли вы так же быстро решить эту задачу?
 * <p/>
 * Формат входных данных
 * В первой строке через пробел записаны целые числа n и m (1 <= n <= 10^6; 1 <= m <= 1000).
 * В следующей строке через пробел записаны m целых чисел в пределах от 1 до 1 000 — вместимости автобусов.
 * <p/>
 * Формат выходных данных
 * В первой строке выведите число k — минимальное количество автобусов,
 * которое придётся заказать директору.
 * В следующей строке выведите через пробел k целых чисел — номера автобусов,
 * которые нужно заказать. Автобусы пронумерованы от 1 до m в том порядке,
 * в которых они перечислены во входных данных.
 * Если возможных решений несколько, выведите любое.
 * Если решения нет, в единственной строке выведите «-1».
 */
public class A {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int m = nextInt();

        int[] array = new int[m];

        for (int i = 0; i < m; i++) {
            array[i] = nextInt() * 2000 + (i + 1);
        }

        Arrays.sort(array);

        int res = 0;
        int[] numbers = new int[m];
        int i = 0;

        for (int k = m - 1; k >= 0; k--) {
            res += array[k] / 2000;
            numbers[i] = array[k] % 2000;
            i++;
            if (res >= n) {
                out.println(i + " ");
                for (int j = 0; j < i; j++) {
                    out.print(numbers[j] + " ");
                }
                break;
            }
        }
        if (res < n) {
            out.println(-1);
        }

        out.close();
    }
}

