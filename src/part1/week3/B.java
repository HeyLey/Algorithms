package part1.week3;

import java.io.*;

/**
 * В этой задаче нужно уметь выяснять, содержится ли число в последовательности.
 Формат входных данных
 В первой строке входного файла заданы через пробел два целых числа n и k
 (1 <= n <= 300 000, 1 <= k <= 300 000).
 Во второй строке задана последовательность из n отсортированных целых чисел a_1, a_2,..., a_n,
 записанных через пробел (1 <= a_i <= 10^9).
 В третьей строке записаны запросы — k целых чисел b_1, b_2, . . ., b_k записанных через пробел,
 в порядке возрастания (1 <= k <= 10^9).
 Формат выходных данных
 В выходной файл выведите k строк. В j-ой строке выведите “YES”,
 если число b_j содержится в последовательности {a_i}, и “NO” в противном случае.
 */
public class B {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static int rank(int key, int[] array) {
        return rank(key, array, 0, array.length - 1);
    }

    public static int rank(int key, int[] array, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = low + (high - low) / 2;
        if (key < array[middle]) {
            return rank(key, array, low, middle - 1);
        } else if (key > array[middle]) {
            return rank(key, array, middle + 1, high);
        }
        else {
            return middle + 1;
        }
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int k = nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        for (int i = 0; i < k; i++) {
            int key = nextInt();
            int result = rank(key, a);
            if (result == -1) {
                out.println("NO");
            } else {
                out.println("YES");
            }

        }

        out.close();
    }
}
