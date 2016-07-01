package part1.week3;

/*
 Дан массив из N целых чисел. Все числа от −10^9 до 10^9.
 Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения от L до R?”.
 Формат входных данных
 Число N (1 <= N <= 10^5). Далее N целых чисел.
 Затем число запросов K (1 <= K <= 10^5).
 Далее K пар чисел L, R (−10^9 <= L <= R <= 10^9) — собственно запросы.
 Формат выходных данных
 Выведите K чисел — ответы на запросы.
 */
import java.io.*;
import java.util.Arrays;

public class C {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static int rank(int key, int[] array, int type) {
        return rank(key, array, 0, array.length - 1, type);
    }

    public static int rank(int key, int[] array, int low, int high, int type) {
        if (low > high) {
            if (type == 1) {
                return high;
            } else {
                return low;
            }
        }
        int middle = low + (high - low) / 2;
        if (key < array[middle] || (type == 1 && array[middle] == key)) {
            return rank(key, array, low, middle - 1, type);
        }
        if (key > array[middle] || (type == 2 && array[middle] == key)) {
            return rank(key, array, middle + 1, high, type);
        }
        return -1;
    }



    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        Arrays.sort(a);

        int k = nextInt();

        for (int i = 0; i < k; i++) {
            int l = nextInt();
            int r = nextInt();
            int findl = rank(l, a, 1);
            int findr = rank(r, a, 2);
            out.print(findr - findl - 1 + " ");
        }

        out.close();
    }
}
