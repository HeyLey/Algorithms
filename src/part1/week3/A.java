package part1.week3;

import java.io.*;

/*
 Даны два списка дат.
 Найти количество дат во втором списке, которые присутствуют в первом.

 На первой строке длина первого списка N (1 <= N <= 15 000).
Следующие N строк содержат целые числа от 1 до 10^9 – даты из первого списка.
Далее длина второго списка M (1 <= M <= 10^6).
Следующие M строк содержат целые числа от 1 до 10^9 – даты из второго списка.
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
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        int m = nextInt();
        int[] keys = new int[m];

        for (int i = 0; i < m; i++) {
            keys[i] = nextInt();
        }

        int all = 0;

        for (int i = 0; i < m; i++) {
            int key = keys[i];
            int result = rank(key, array);
            if (result != -1) {
                all++;
            }
        }
        out.print(all);
        out.close();
    }
}
