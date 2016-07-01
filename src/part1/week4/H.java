package part1.week4;

/**
 * Дано несколько точек на плоскости.
 * Выведите наименьшее расстояние, которое достигается между какими-то двумя из них.
 Формат входных данных
 В первой строке задано число N (2 <= N <= 200 000) — количество точек.
 Следующие N строк содержат координаты точек (целые числа от −10^9 до 10^9).
 Формат выходных данных
 Выведите единственное вещественное число —
 минимальное расстояние между какими-то двумя из этих точек.
 Ответ будет считаться корректным, если абсолютная погрешность ответа не будет превышать 10^(-6)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class H {
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

        int n = nextInt();

        int[][] p = new int[n][2];

        for (int i = 0; i < n; i++) {
            p[i][0] = nextInt();
            p[i][1] = nextInt();
        }

        double min = findMin(p);
        System.out.println(Math.sqrt(min));
    }

    private static double findMin(int[][] p) throws IOException {
        int n = p.length;
        if (n <= 1) {
            return Double.POSITIVE_INFINITY;
        }

        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0] - p2[0];
            }
        });

        int center = n / 2;

        int x0 = p[center][0];

        int[][] tmp = new int[center - 1][];
        System.arraycopy(p, 0, tmp, 0, center - 1);

        double d1 = findMin(tmp);

        tmp = new int[n - center - 1][];
        System.arraycopy(p, center + 1, tmp, 0, n - center - 1);
        double d2 = findMin(tmp);

        double minD = Math.min(d1, d2);

        tmp = new int[n][];
        int tmpSize = 0;

        for (int i = 0; i < n; i++) {
            double dx = p[i][0] - x0;
            if (dx * dx < minD) {
                tmp[tmpSize] = p[i];
                tmpSize++;
            }
        }

        int[][] result = new int[tmpSize][];

        System.arraycopy(tmp, 0, result, 0, tmpSize);

        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[1] - p2[1];
            }
        });


        for (int i = 0; i < tmpSize; i++) {
            for (int j = i + 1; j < tmpSize; j++) {
                double dx = result[i][0] - result[j][0];
                double dy = result[i][1] - result[j][1];
                double newDist = dx * dx + dy * dy;
                if (newDist < minD) {
                    minD = newDist;
                }
                if (dy * dy > minD) {
                    break;
                }
            }
        }

        return minD;
    }

}
