package part1.week2;

/**
 Дано n отрезков на числовой прямой и m точек на этой же прямой.
 Для каждой из данных точек определите, скольким отрезкам она принадлежит.
 Точка x считается принадлежащей отрезку с концами a и b,
 если выполняется двойное неравенство min(a, b) <= x <= max(a, b).
 Формат входных данных
 Первая строка содержит два целых числа n (1 <= n <= 10^5) — число отрезков и
 m (1 <= m <= 10^5) — число точек.
 В следующих n строках записаны по два целых числа
 a_i и b_i — координаты концов соответствующего отрезка.
 В последней строке записаны m целых чисел — координаты точек.
 Все числа во входном файле не превосходят по модулю 10^9.
 Формат выходных данных
 В выходной файл выведите m чисел —
 для каждой точки выведите количество отрезков, в которых она содержится.
 */

import java.io.*;
import java.util.Arrays;

public class F {

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
        Element[] points = new Element[2*n + m];

        int k = 0;

        for (int i = 0; i < n; i++) {
            int a = nextInt();
            int b = nextInt();
            points[k] = new Element();
            points[k].pos = Math.min(a, b);
            points[k].type = 1;
            k++;
            points[k] = new Element();
            points[k].pos = Math.max(a, b);
            points[k].type = 3;
            k++;
        }

        for (int j = 0; j < m; j++) {
            int p = nextInt();
            points[k] = new Element();
            points[k].pos = p;
            points[k].type = 2;
            points[k].index = j;
            k++;
        }

        Arrays.sort(points);

        int num = 0;
        int[] result = new int[m];

        for (int l = 0; l < points.length; l++) {
            if (points[l].type == 1) {
                num++;
            }
            if (points[l].type == 2) {
                result[points[l].index] = num;
            }
            if (points[l].type == 3) {
                num--;
            }
        }

        for (int l = 0; l < m; l++) {
            out.print(result[l] + " ");
        }
        out.println();
        out.close();
    }

    static class Element implements Comparable<Element> {
        int pos;
        int type;
        int index;

        @Override
        public int compareTo(Element e) {
            if (pos != e.pos) {
                return  pos - e.pos;
            }
            return type - e.type;
        }
    }
}
