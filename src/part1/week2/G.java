package part1.week2;

/**
 * Даны отрезки на прямой.
 * Какое максимальное количество отрезков можно выбрать так,
 * чтобы никакие два из них не пересекались?
 * Отрезки считаются открытыми.
 *
 Формат входных данных
 В первой строке входного файла задано целое число n (1 <= n <= 100 000).
 В следующих n строках описаны отрезки;
 i-я из этих строк содержит два целых числа l_i и r_i через пробел —
 координаты начала и конца отрезка (1 <= l_i < r_i <= 10^9).
 Формат выходных данных
 В первой строке выходного файла выведите одно число — максимальное количество вы-
 бранных отрезков.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class G {
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

        final int[] l = new int[n];
        final int[] r = new int[n];
        Integer[] indexes = new Integer[n];

        for (int i = 0; i < n; i++) {
            l[i] = nextInt();
            r[i] = nextInt();
            indexes[i] = i;
        }

        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (r[a] != r[b]) {
                    if (r[a] < r[b]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                if (l[a] != l[b]) {
                    if (l[a] < l[b]) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        });

        int right = Integer.MIN_VALUE;

        int number = 0;

        for (int i = 0; i < n; i++) {
            int index = indexes[i];
            if (l[index] >= right) {
                number++;
                right = r[index];
            }
        }

        out.println(number);
        out.close();
    }
}
